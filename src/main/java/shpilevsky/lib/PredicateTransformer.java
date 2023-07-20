package shpilevsky.lib;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;

public class PredicateTransformer {
    private final CriteriaBuilder criteriaBuilder;

    public PredicateTransformer(CriteriaBuilder criteriaBuilder)
    {
        this.criteriaBuilder = criteriaBuilder;
    }

    public jakarta.persistence.criteria.Predicate transform(java.util.function.Predicate<?> javaUtilPredicate,
                                                            Root<?> root)
    {
        return transformPredicate(javaUtilPredicate, root);
    }

    private jakarta.persistence.criteria.Predicate transformPredicate(java.util.function.Predicate<?> javaUtilPredicate,
                                                                      Root<?> root)
    {
        if (javaUtilPredicate == null)
        {
            return null;
        }

        if (javaUtilPredicate instanceof java.util.function.Predicate)
        {
            return criteriaBuilder.isTrue(root.<Boolean>get("id"));
        }

        return null;
    }
}
