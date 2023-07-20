package shpilevsky.core.impl.spring.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shpilevsky.lib.IDataStorage;
import shpilevsky.lib.PredicateTransformer;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class DataStorageSpring<E extends BaseEntity, K extends Serializable> implements IDataStorage<E, K>
{
    private IRepositorySpring<E, K> repositorySpring;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    private EntityManagerFactory entityManagerFactory;
    private PredicateTransformer predicateTransformer;
    private Class<E> entityClass;
    private Function<E, K> keyFunction;

    public DataStorageSpring(Class<E> entityClass, Function<E, K> keyFunction,
                             EntityManagerFactory entityManagerFactory)
    {
        this.entityClass = entityClass;
        this.keyFunction = keyFunction;
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
        repositorySpring = new RepositorySpring<>(
                JpaEntityInformationSupport.getEntityInformation(entityClass, entityManager) ,entityManager);

        predicateTransformer = new PredicateTransformer(criteriaBuilder);
        //entityManager.close();
        //entityManagerFactory.close();
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
    public K getKey(E entity) {
        return keyFunction.apply(entity);
    }

    @Transactional
    @Override
    public void add(E entity)
    {
        repositorySpring.save(entity);
        entityManager.flush();
        //recreateEntityManager();
    }

    @Transactional
    @Override
    public void add(List<E> entities) {
        repositorySpring.saveAll(entities);
        entityManager.flush();
    }

    @Transactional
    @Override
    public void delete(E entity) {
        repositorySpring.delete(entity);
    }

    @Transactional
    @Override
    public void delete(List<E> entities) {
        repositorySpring.deleteAll(entities);
    }



    @Transactional
    @Override
    public void delete(Predicate<E> condition)
    {
        repositorySpring.delete(getSpecification(condition));
    }

    @Override
    public Optional<E> findFirst(Predicate<E> condition)
    {
        return repositorySpring.findOne(getSpecification(condition));
    }

    @Override
    public List<E> find(Predicate<E> condition)
    {
        return repositorySpring.findAll(getSpecification(condition));
    }

    @Transactional
    @Override
    public List<E> getAll()
    {
        List<E> list = repositorySpring.findAll();
        //recreateEntityManager();
        return list;
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        repositorySpring.deleteAll();
    }

    ///
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
