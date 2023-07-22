package ru.alishev.springcourse.Project2Boot.shpilevsky.core.services;

import ru.alishev.springcourse.Project2Boot.models.Book;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;

import java.util.List;

public abstract class AServicePeople
{
    protected IDataStorage<Person, Integer> repositoryPerson;
    protected IDataStorage<Book, Integer> repositoryBook;

    public AServicePeople(IDataStorage<Book, Integer> repositoryBook, IDataStorage<Person, Integer> repositoryPerson)
    {
        this.repositoryPerson = repositoryPerson;
        this.repositoryBook = repositoryBook;
    }

    public abstract List<Person> findAll();
    public abstract Person findOne(int id);
    public abstract void save(Person person);
    public abstract void update(int id, Person updatedPerson);
    public abstract void delete(int id);
}
