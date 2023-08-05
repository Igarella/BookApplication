package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Book;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.fi.FunctionTTTR;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.predicate.PredicateField;

public enum CdtsBook {
    ONE_BY_ID("id", CriteriaBuilder::equal),
    MANY_BY_OWNER("owner", CriteriaBuilder::equal),
    ALL_YEAR("year", (cb, val, valToCompare) -> cb.isNotNull(val)),
    ONE_BY_TITLE("title",
            (cb, val, valToCompare) -> cb.equal(val, valToCompare)),
    ONE_BY_AUTHOR("authors",
            (cb, val, valToCompare) -> cb.like(val, "%"+valToCompare+"%")),
    MANY_STARTING_TITLE_WITH("title",
                        (cb, val, valToCompare) -> cb.like(val, valToCompare+"%"));


    private String fieldName;
    private FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer;

    public <E extends IBook> PredicateField<E> apply()
    {
        return new PredicateField<>(fieldName, (cb, val) -> consumer.apply(cb, val, null));
    }
    public <E extends IBook> PredicateField<E> apply(Object valToCompare)
    {
        return new PredicateField<>(fieldName, (cb, val) -> consumer.apply(cb, val, valToCompare));
    }

    CdtsBook(String fieldName, FunctionTTTR<CriteriaBuilder, Expression, Object, Predicate> consumer)
    {
        this.fieldName = fieldName;
        this.consumer = consumer;
    }
}

