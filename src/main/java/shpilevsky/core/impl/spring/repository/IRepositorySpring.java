package shpilevsky.core.impl.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@NoRepositoryBean
@Component
@Transactional
public interface IRepositorySpring<E extends BaseEntity, ID extends Serializable> extends JpaRepository<E, ID>,
        JpaSpecificationExecutor<E> {
}