package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.repository;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models.ABaseEntity;

import java.io.Serializable;

public interface IRepositoryJson <E extends ABaseEntity, ID extends Serializable> extends
        JpaSpecificationExecutor<E>, Jackson2ObjectMapperBuilderCustomizer {


}
