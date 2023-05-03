package ru.alishev.springcourse.Project2Boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.Project2Boot.models.Author;
import ru.alishev.springcourse.Project2Boot.repositories.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }
}
