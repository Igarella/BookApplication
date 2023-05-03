package ru.alishev.springcourse.Project2Boot.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.Project2Boot.dto.AuthorDTO;
import ru.alishev.springcourse.Project2Boot.models.Author;
import ru.alishev.springcourse.Project2Boot.repositories.AuthorRepository;
import ru.alishev.springcourse.Project2Boot.services.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {


    private final AuthorService authorService;

    private final ModelMapper modelMapper;

    private Author convertFromDTO(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }
    @GetMapping()
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity createAuthor(AuthorDTO authorDTO) {
        Author author = convertFromDTO(authorDTO);
        authorService.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
