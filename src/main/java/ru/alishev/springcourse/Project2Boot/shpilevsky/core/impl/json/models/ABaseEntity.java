package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;

public abstract class ABaseEntity implements Serializable
{

    public ABaseEntity(){}
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
