package shpilevsky.core.impl.spring.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Component
@Transactional
public class RepositorySpring<E extends BaseEntity, ID extends Serializable>
        extends SimpleJpaRepository<E, ID>
        implements IRepositorySpring<E, ID>
{
    public RepositorySpring(JpaEntityInformation<E, ?> entityInformation,
                            EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
