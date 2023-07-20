package ru.alishev.springcourse.Project2Boot.dto;

import lombok.Data;
import ru.alishev.springcourse.Project2Boot.models.Book;
@Data
public class AuthorDTO {
    private String name;
    private Book book;
}
