package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.Project2Boot.dto.AuthorDTO;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Author;
import ru.alishev.springcourse.Project2Boot.services.ServiceAuthor;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsAuthor;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.services.ServiceAuthorSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IAuthor;

import java.util.List;

@RestController
@RequestMapping("/author")
public class ControllerAuthor {


    private final ServiceAuthorSpring serviceAuthorSpring;

    private final ModelMapper modelMapper;

    public ControllerAuthor(ServiceAuthorSpring serviceAuthorSpring, ModelMapper modelMapper) {
        this.serviceAuthorSpring = serviceAuthorSpring;
        this.modelMapper = modelMapper;
    }


    @GetMapping()
    public List<IAuthor> getAuthors() {
        return serviceAuthorSpring.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity createAuthor(@RequestBody AuthorDTO authorDTO) {
        IAuthor author = convertFromDTO(authorDTO);
        serviceAuthorSpring.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public IAuthor findAuthorById(@PathVariable("id") int id) {
        return serviceAuthorSpring.findOne(CdtsAuthor.ONE_BY_ID.apply(id));
    }

    private Author convertFromDTO(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }
}
