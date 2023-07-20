package ru.alishev.springcourse.Project2Boot.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.Project2Boot.dto.AuthorDTO;
import ru.alishev.springcourse.Project2Boot.models.Author;
import ru.alishev.springcourse.Project2Boot.services.ServiceAuthor;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class ControllerAuthor {


    private final ServiceAuthor serviceAuthor;

    private final ModelMapper modelMapper;

    private Author convertFromDTO(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }
    @GetMapping()
    public List<Author> getAuthors() {
        return serviceAuthor.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity createAuthor(AuthorDTO authorDTO) {
        Author author = convertFromDTO(authorDTO);
        serviceAuthor.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
