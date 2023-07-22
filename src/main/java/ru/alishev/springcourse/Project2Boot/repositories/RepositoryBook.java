package ru.alishev.springcourse.Project2Boot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.Project2Boot.models.Book;

import java.util.List;

public interface RepositoryBook{// extends JpaRepository<Book, Integer> {


    List<Book> findBooksByOwnerId(int id);

    Page<Book> findAll (Pageable pageable);

    List<Book> findAll (Sort sort);

    Book findByTitleStartingWith (String title);

//    Page<Book> findAll(Pageable pageable, Sort sort);

}

//"select Person. * from Book inner join Person on Book.person_id = Person.id where Book.id=:id")