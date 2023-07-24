package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.Project2Boot.dto.AuthorDTO;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Author;
import ru.alishev.springcourse.Project2Boot.services.ServiceAuthor;

import java.util.List;

@RestController
@RequestMapping("/author")
public class ControllerAuthor {


    private final ServiceAuthor serviceAuthor;

    private final ModelMapper modelMapper;

    public ControllerAuthor(ServiceAuthor serviceAuthor, ModelMapper modelMapper) {
        this.serviceAuthor = serviceAuthor;
        this.modelMapper = modelMapper;
    }


    @GetMapping()
    public List<Author> getAuthors() {
        return serviceAuthor.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = convertFromDTO(authorDTO);
        serviceAuthor.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Author findAuthorById(@PathVariable("id") int id) {
        return serviceAuthor.findOneById(id);
    }

    private Author convertFromDTO(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }
}
