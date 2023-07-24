package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Author;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IAuthor;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.fi.FunctionTTTR;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.predicate.PredicateField;

public enum CdtsAuthor {
    ONE_BY_ID("id", (cb, val, valToCompare) -> cb.equal(val, valToCompare));
    private String fieldName;
    private FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer;

    public <E extends IAuthor> PredicateField<E> apply()
    {
        return new PredicateField<>(fieldName, (cb, val) -> consumer.apply(cb, val, null));
    }
    public <E extends IAuthor> PredicateField<E> apply(Object valToCompare)
    {
        return new PredicateField<>(fieldName, (cb, val) -> consumer.apply(cb, val, valToCompare));
    }

    CdtsAuthor(String fieldName, FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer)
    {
        this.fieldName = fieldName;
        this.consumer = consumer;
    }
}
