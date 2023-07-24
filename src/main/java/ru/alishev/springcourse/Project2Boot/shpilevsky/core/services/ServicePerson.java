package ru.alishev.springcourse.Project2Boot.shpilevsky.core.services;

import ru.alishev.springcourse.Project2Boot.shpilevsky.core.installer.IInstaller;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.predicate.PredicateField;

import java.util.List;
import java.util.function.Predicate;

public class ServicePerson
{
    protected IDataStorage<IPerson, Integer> repositoryPerson = IInstaller.get().repositoryPerson();

    public List<IPerson> find(PredicateField<IPerson> condition)
    {
        return repositoryPerson.find(condition);
    }

    public List<IPerson> findAll()
    {
        return repositoryPerson.getAll();
    }

    public IPerson findOne(Predicate<IPerson> condition)
    {
        return repositoryPerson.findFirst(condition);
    }

    public void save(IPerson person)
    {
        repositoryPerson.add(person);
    }

    public void update(int id, IPerson updatedPerson)
    {
        updatedPerson.setId(id);
        repositoryPerson.add(updatedPerson);
    }

    public void delete(Predicate<IPerson> condition)
    {
        repositoryPerson.delete(condition);
    }
}
