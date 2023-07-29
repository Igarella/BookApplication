package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.ABaseEntity;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.predicate.PredicateField;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.predicate.PredicateTransformer;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.SortType;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class DataStorageSpring<E extends ABaseEntity, K extends Serializable> implements IDataStorage<E, K>
{
    private IRepositorySpring<E, K> repositorySpring;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    private EntityManagerFactory entityManagerFactory;
    private PredicateTransformer predicateTransformer;
    private Class<E> entityClass;
    private Function<E, K> keyFunction;
    private Session session;


    public void init(Class<E> entityClass, Function<E, K> keyFunction,
                     EntityManagerFactory entityManagerFactory)
    {
        this.entityClass = entityClass;
        this.keyFunction = keyFunction;
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.setFlushMode(FlushModeType.AUTO);
        criteriaBuilder = entityManager.getCriteriaBuilder();
        repositorySpring = new RepositorySpring<>(
                JpaEntityInformationSupport.getEntityInformation(entityClass, entityManager),
                entityManager);

        predicateTransformer = new PredicateTransformer(criteriaBuilder);
        session = entityManager.unwrap(Session.class);
    }

    public DataStorageSpring()
    {
    }

    private void recreateEntityManager()
    {
        entityManager.close();
        entityManager = entityManagerFactory.createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
        repositorySpring = new RepositorySpring<>(
                JpaEntityInformationSupport.getEntityInformation(entityClass, entityManager) ,entityManager);

        predicateTransformer = new PredicateTransformer(criteriaBuilder);
    }

    @Override
    public K getKey(E entity)
    {
        return keyFunction.apply(entity);
    }

    @Transactional
    public void add(E entity)
    {
        transactionSaveOrUpdate(entity);
    }

    @Transactional
    public void add(List<E> entities) {
        transactionSaveOrUpdate((E[])entities.toArray());
    }

    @Transactional
    public void delete(E entity) {
        transactionDelete(entity);
    }

    @Transactional
    public void delete(List<E> entities) {
        transactionDelete((E[])entities.toArray());
    }

    @Transactional
    public void delete(Predicate<E> condition)
    {
        deleteByCriteria(condition);
    }

    @Transactional
    public List<E> find(Predicate<E> condition)
    {
        return find(condition, SortType.ASC);
    }

    @Transactional
    public List<E> find(Predicate<E> condition, SortType sortType)
    {
        return selectByCriteria(condition, sortType);
    }

    @Transactional
    public List<E> getAll()
    {
        return repositorySpring.findAll();
    }

    @Transactional
    public void deleteAll()
    {
        repositorySpring.deleteAll();
    }

    @Transactional
    public E findFirst(Predicate<E> condition)
    {
        List<E> result = selectByCriteria(condition, SortType.ASC);
        return !result.isEmpty() ? result.get(0) : null;
    }

    ///
    private void transactionSaveOrUpdate(E... entity)
    {
        Transaction transaction = session.beginTransaction();
        for (E e : entity)
        {
            E exist = entityManager.find(entityClass, getKey(e));
            if (exist != null)
                session.merge(e);
            else
                session.saveOrUpdate(e);
        }
        transaction.commit();
    }

    private void transactionDelete(E... entity)
    {
        Transaction transaction = session.beginTransaction();
        for (E e : entity)
            session.delete(e);
        transaction.commit();
    }

    private void deleteByCriteria(Predicate<E> condition)
    {
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<E> criteriaDelete = criteriaBuilder.createCriteriaDelete(entityClass);
        Root<E> root = criteriaDelete.from(entityClass);

        jakarta.persistence.criteria.Predicate predicate = predicateTransformer.transform(condition, root);
        criteriaDelete.where(predicate);

        entityManager.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    private List<E> selectByCriteria(Predicate<E> condition, SortType sortType)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<E> root = criteriaQuery.from(entityClass);

        jakarta.persistence.criteria.Predicate predicate = predicateTransformer.transform(condition, root);
        criteriaQuery.where(predicate);
        criteriaQuery.orderBy(
                sortType == SortType.ASC ?
                        criteriaBuilder.asc(root.get(((PredicateField) condition).getFieldName())) :
                        criteriaBuilder.desc(root.get(((PredicateField) condition).getFieldName())));

        TypedQuery<E> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    private Root<E> getRoot()
    {
        CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        Root<E> root = query.from(entityClass);
        return root;
    }

    private Specification<E> getSpecification(Predicate<E> condition)
    {
        jakarta.persistence.criteria.Predicate predicate = predicateTransformer.transform(condition, getRoot());
        Specification<E> specification = (root, query, criteriaBuilder) -> predicate;
        return specification;
    }
}
