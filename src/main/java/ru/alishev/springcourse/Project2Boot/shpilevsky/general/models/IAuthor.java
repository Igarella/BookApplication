package ru.alishev.springcourse.Project2Boot.shpilevsky.general.models;

import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Book;

import java.util.List;

public interface IAuthor {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    List<Book> getBook();

    void setBook(List<Book> book);
}
