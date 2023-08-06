package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.repository.predicate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.fi.FunctionTTR;

public class PredicateFieldJson<E> implements java.util.function.Predicate<E>
{
    private String fieldName;
    private Object value;
    private FunctionTTR<CriteriaBuilder, Expression, jakarta.persistence.criteria.Predicate> functionCriteria;

    public FunctionTTR<CriteriaBuilder, Expression, jakarta.persistence.criteria.Predicate> getFunctionCriteria() {
        return functionCriteria;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    public PredicateFieldJson(String fieldName,
                              FunctionTTR<CriteriaBuilder, Expression,
                                  jakarta.persistence.criteria.Predicate> functionCriteria)
    {
        this.fieldName = fieldName;
        this.value = null;
        this.functionCriteria = functionCriteria;
    }

    /**
    <h2>Не использовать</h2>
     */
    @Override
    public boolean test(E t)
    {
        // не нужен. Так как мы используем критерии
        /*try
        {
            Field field = t.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object fieldValue = field.get(t);
            return Objects.equals(fieldValue, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }*/
        return false;
    }
}
