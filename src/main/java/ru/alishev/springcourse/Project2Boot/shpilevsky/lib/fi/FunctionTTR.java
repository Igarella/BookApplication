package ru.alishev.springcourse.Project2Boot.shpilevsky.lib.fi;

@FunctionalInterface
public interface FunctionTTR<T, T2, R>
{
    R apply(T t, T2 t2);
}
