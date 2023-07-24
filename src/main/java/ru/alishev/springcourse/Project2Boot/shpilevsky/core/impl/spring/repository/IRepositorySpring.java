package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.ABaseEntity;
import java.io.Serializable;

@NoRepositoryBean
@Transactional
public interface IRepositorySpring<E extends ABaseEntity, ID extends Serializable> extends JpaRepository<E, ID>,
        JpaSpecificationExecutor<E> {
}
