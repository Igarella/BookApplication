package ru.alishev.springcourse.Project2Boot;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.alishev.springcourse.Project2Boot.models.Book;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.DataStorageSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;

@SpringBootApplication
@EnableTransactionManagement
public class Project2BootApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
				SpringApplication.run(Project2BootApplication.class, args);
	}

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	//private IDataStorage<Person, Integer> repositoryPerson;

	@Bean
	public IDataStorage<Person, Integer> repositoryPerson()
	{
		DataStorageSpring<Person, Integer> ds = new DataStorageSpring<>();
		ds.init(Person.class, Person::getId, entityManagerFactory);
		return ds;
	}

	@Bean
	public IDataStorage<Book, Integer> repositoryBook()
	{
		DataStorageSpring<Book, Integer> ds = new DataStorageSpring<>();
		ds.init(Book.class, Book::getId, entityManagerFactory);
		return ds;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
