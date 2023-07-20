package shpilevsky.lib;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IDataStorage<E, K>
{
    K getKey(E entity);
    void add(E entity);
    void add(List<E> entities);
    void delete(E entity);
    void delete(List<E> entities);
    void delete(Predicate<E> condition);
    Optional<E> findFirst(Predicate<E> condition);
    List<E> find(Predicate<E> condition);
    List<E> getAll();
    void deleteAll();

}
