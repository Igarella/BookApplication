package ru.alishev.springcourse.Project2Boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.Project2Boot.models.Author;
import ru.alishev.springcourse.Project2Boot.repositories.RepositoryAuthor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceAuthor {

    private final RepositoryAuthor repositoryAuthor;

    public List<Author> findAll() {
        return repositoryAuthor.findAll();
    }

    public void createAuthor(Author author) {
        repositoryAuthor.save(author);
    }
}
