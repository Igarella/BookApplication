package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models;

import java.io.Serializable;

public abstract class ABaseEntity implements Serializable
{
    private Boolean deleted;

    public Boolean getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }
}
