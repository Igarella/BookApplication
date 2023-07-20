package ru.alishev.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.repositories.RepositoryBook;
import ru.alishev.springcourse.Project2Boot.repositories.RepositoryPeople;
import shpilevsky.core.impl.spring.repository.IRepositorySpring;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServicePeople {
    private final RepositoryPeople repositoryPeople;
    private final RepositoryBook repositoryBook;

    @Autowired
    public ServicePeople(RepositoryBook repositoryBook, RepositoryPeople repositoryPeople)
    {
        this.repositoryPeople = repositoryPeople;
        this.repositoryBook = repositoryBook;
    }

    public List<Person> findAll()
    {
        return repositoryPeople.findAll();
    }
    public Person findOne(int id) {
        Optional<Person> foundPerson = repositoryPeople.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        repositoryPeople.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        repositoryPeople.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        repositoryPeople.deleteById(id);
    }

}
