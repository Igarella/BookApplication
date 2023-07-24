package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.ABaseEntity;

import java.io.Serializable;

@NoRepositoryBean
@Transactional
public class RepositorySpring<E extends ABaseEntity, ID extends Serializable>
        extends SimpleJpaRepository<E, ID>
        implements IRepositorySpring<E, ID>
{
    public RepositorySpring(JpaEntityInformation<E, ?> entityInformation,
                            EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
