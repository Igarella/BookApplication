package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models.ABaseEntity;

import java.io.Serializable;

public class RepositoryJson<E extends ABaseEntity, ID extends Serializable>
        extends SimpleJpaRepository<E, ID>
        implements IRepositoryJson<E, ID> {

    public RepositoryJson(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {

    }


}
