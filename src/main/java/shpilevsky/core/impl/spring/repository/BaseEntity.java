package shpilevsky.core.impl.spring.repository;

import jakarta.persistence.Transient;

public abstract class BaseEntity
{
    @Transient
    private Boolean deleted;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
