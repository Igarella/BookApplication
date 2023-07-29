package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.installer;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.installer.IInstaller;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.ABaseEntity;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Book;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.DataStorageSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;

import java.io.Serializable;
import java.util.function.Function;

@Component
public class InstallerSpring extends IInstaller
{
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public IDataStorage<Person, Integer> repositoryPerson()
    {
        return createDataStorage(Person.class, Person::getId);
    }

    @Bean
    public IDataStorage<Book, Integer> repositoryBook()
    {
        return createDataStorage(Book.class, Book::getId);
    }

    @PostConstruct
    public void install()
    {
        System.out.println("Installed");
    }

    private <E extends ABaseEntity, K extends Serializable> IDataStorage<E, K>
    createDataStorage(Class<E> clazz, Function<E, K> keyFunction)
    {
        DataStorageSpring<E, K> ds = new DataStorageSpring<>();
        ds.init(clazz, keyFunction, entityManagerFactory);
        return ds;
    }
}
