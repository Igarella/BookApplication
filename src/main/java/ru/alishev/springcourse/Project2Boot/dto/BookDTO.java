package ru.alishev.springcourse.Project2Boot.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.alishev.springcourse.Project2Boot.models.Author;
import ru.alishev.springcourse.Project2Boot.models.Person;

import java.util.Date;
import java.util.List;

public class BookDTO {
    private String title;
    private String author;
    private int year;
    private Person owner;
    private Date assignedAt;

    private List<Author> authors;

    @Transient
    private boolean isOutOfDAte;
}
