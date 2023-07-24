package ru.alishev.springcourse.Project2Boot.shpilevsky.general.models;

import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Author;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsAuthor;

import java.util.List;

public interface IAuthor {

    public List<Author> findAll();

    public void createAuthor(Author author);

    public Author findOneById(int id);

}
