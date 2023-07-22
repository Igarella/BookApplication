package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.conditions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import ru.alishev.springcourse.Project2Boot.models.Book;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.FunctionTTTR;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.PredicateField;

public enum CdtsBook {
    ONE_BY_ID("id", (cb, val, valToCompare) -> cb.equal(val, valToCompare)),
    MANY_BY_OWNER("owner", (cb, val, valToCompare) -> cb.equal(val, valToCompare)),
    ALL_YEAR("year", (cb, val, valToCompare) -> cb.isNotNull(val)),

    MANY_STARTING_TITLE_WITH("title",
                        (cb, val, valToCompare) -> cb.like(val, valToCompare+"%"));


    private String fieldName;
    private FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer;

    public PredicateField<Book> apply()
    {
        return new PredicateField<>(fieldName, (cb, val) -> consumer.apply(cb, val, null));
    }
    public PredicateField<Book> apply(Object valToCompare)
    {
        return new PredicateField<>(fieldName, (cb, val) -> consumer.apply(cb, val, valToCompare));
    }

    CdtsBook(String fieldName, FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer)
    {
        this.fieldName = fieldName;
        this.consumer = consumer;
    }
}

