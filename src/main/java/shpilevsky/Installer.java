package shpilevsky;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.Project2Boot.models.Person;
import shpilevsky.core.impl.spring.repository.DataStorageSpring;
import shpilevsky.lib.IDataStorage;

import java.util.List;

@Component
public class Installer implements InitializingBean
{
    @PersistenceContext
    private EntityManagerFactory entityManagerFactory;
    private IDataStorage<Person, Integer> repositoryPerson;

    public Installer()
    {
        this.repositoryPerson = new DataStorageSpring<>(Person.class, Person::getId, entityManagerFactory);
        // Конструктор не вызывает инициализацию репозитория
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        // Инициализация репозитория в методе с аннотацией @PostConstruct
        //this.repositoryPerson = new DataStorageSpring<>(Person.class, Person::getId, entityManagerFactory);
        List<Person> all = repositoryPerson.getAll();
        Person vasya = new Person("vasya", 24);
        vasya.setId(98);
        repositoryPerson.add(vasya);
        all = repositoryPerson.getAll();
        //List<Person> filtered = repositoryPerson.find(person -> person.getBirthDate() > 1944);
        System.out.println();
    }
}
