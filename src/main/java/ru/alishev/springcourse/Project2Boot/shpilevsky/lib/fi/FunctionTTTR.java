package ru.alishev.springcourse.Project2Boot.shpilevsky.lib.fi;

@FunctionalInterface
public interface FunctionTTTR<T, T2, T3, R>
{
    R apply(T t1, T2 t2, T3 t3);
}
