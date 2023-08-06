package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.repository.conditions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.predicate.PredicateField;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.fi.FunctionTTTR;

public enum CdtsPersonJson
{
    ONE_BY_ID("id",
            (cb, val, valToCompare) -> cb.equal(val, valToCompare)),
    MANY_CONTAINS_IN_NAME("fullName",
            (cb, val, valToCompare) -> cb.like(val, "%"+valToCompare+"%")),
    ONE_BY_NAME("fullName",
            (cb, val, valToCompare) -> cb.equal(val, valToCompare));

    private String fieldName;
    private FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer;

    public <E extends IPerson> PredicateField<E> apply(Object valToCompare)
    {
        return new PredicateField<>(fieldName, (cb, val) -> consumer.apply(cb, val, valToCompare));
    }

    CdtsPersonJson(String fieldName, FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer)
    {
        this.fieldName = fieldName;
        this.consumer = consumer;
    }
}
