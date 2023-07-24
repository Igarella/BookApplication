package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.predicate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PredicateTransformer
{

    private final CriteriaBuilder criteriaBuilder;

    public PredicateTransformer(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public jakarta.persistence.criteria.Predicate transform(java.util.function.Predicate<?> javaUtilPredicate,
                                                            Root<?> root) {
        return transformPredicate(javaUtilPredicate, root);
    }

    private jakarta.persistence.criteria.Predicate transformPredicate(java.util.function.Predicate<?> javaUtilPredicate,
                                                                      Root<?> root) {
        if (javaUtilPredicate == null) {
            return null;
        }

        if (javaUtilPredicate instanceof PredicateField<?>) {
            PredicateField customPredicate = (PredicateField) javaUtilPredicate;

            String fieldName = customPredicate.getFieldName();
            Object value = customPredicate.getValue();

            return (Predicate) customPredicate.getFunctionCriteria().apply(criteriaBuilder, root.get(fieldName));
            // Example of creating an equality predicate for the field
            //return criteriaBuilder.equal(root.get(fieldName), value);
        }

        return null;
    }
}