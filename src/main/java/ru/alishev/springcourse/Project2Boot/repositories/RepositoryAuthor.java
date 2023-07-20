package ru.alishev.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alishev.springcourse.Project2Boot.models.Author;

public interface RepositoryAuthor extends JpaRepository<Author, Integer> {

}
