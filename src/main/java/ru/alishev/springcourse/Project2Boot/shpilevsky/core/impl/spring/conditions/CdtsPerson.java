package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.conditions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.FunctionTTTR;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.PredicateField;

public enum CdtsPerson
{
    ONE_BY_ID("id",
            (cb, val, valToCompare) -> cb.equal(val, valToCompare)),
    MANY_CONTAINS_IN_NAME("fullName",
            (cb, val, valToCompare) -> cb.like(val, "%"+valToCompare+"%")),
    ONE_BY_NAME("fullName",
            (cb, val, valToCompare) -> cb.equal(val, valToCompare));

    private String fieldName;
    private FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer;

    public PredicateField<Person> apply(Object valToCompare)
    {
        return new PredicateField<>(fieldName, (cb, val) -> consumer.apply(cb, val, valToCompare));
    }

    CdtsPerson(String fieldName, FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer)
    {
        this.fieldName = fieldName;
        this.consumer = consumer;
    }
}
