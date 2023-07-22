package ru.alishev.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.PredicateField;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ServicePeople {
    private final IDataStorage<Person, Integer> repositoryPerson;

    @Autowired
    public ServicePeople(IDataStorage<Person, Integer> repositoryPerson)
    {
        this.repositoryPerson = repositoryPerson;
    }

    public List<Person> find(PredicateField<Person> condition)
    {
        List<Person> list = repositoryPerson.find(condition);
        return list;
    }

    public List<Person> findAll()
    {
        return repositoryPerson.getAll();
        //return find(CdtsPerson.MANY_CONTAINS_IN_NAME.apply("Ал"));
        /*return find(new PredicateField<>("birthDate",
                ((cb, val) -> cb.greaterThan(val, 1944))
        ));*/
    }

    public Person findOne(PredicateField<Person> condition)
    {
        Person foundPerson = repositoryPerson.findFirst(condition);
        return foundPerson;
    }

    @Transactional
    public void save(Person person)
    {
        repositoryPerson.add(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        repositoryPerson.add(updatedPerson);
        //repositoryPeople.save(updatedPerson);
    }

    @Transactional
    public <T> void delete(PredicateField<Person> condition) {
        repositoryPerson.delete(condition);
    }

}
