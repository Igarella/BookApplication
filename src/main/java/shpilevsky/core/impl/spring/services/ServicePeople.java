package shpilevsky.core.impl.spring.services;

import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project2Boot.models.Book;
import ru.alishev.springcourse.Project2Boot.models.Person;
import shpilevsky.core.services.AServicePeople;
import shpilevsky.lib.IDataStorage;
import java.util.List;
import java.util.Optional;

public class ServicePeople extends AServicePeople
{
    public ServicePeople(IDataStorage<Book, Integer> repositoryBook,
                         IDataStorage<Person, Integer> repositoryPerson)
    {
        super(repositoryBook, repositoryPerson);
    }

    @Override
    public List<Person> findAll()
    {
        return null;
    }

    @Override
    public Person findOne(int id)
    {
        Optional<Person> foundPerson = repositoryPerson.findFirst(person -> person.getId() == id);
        return foundPerson.orElse(null);
    }

    @Override
    public void save(Person person)
    {
        repositoryPerson.add(person);
    }

    @Transactional
    @Override
    public void update(int id, Person updatedPerson)
    {

    }

    @Transactional
    @Override
    public void delete(int id)
    {
        repositoryPerson.delete(person -> person.getId() == id);
    }
}
