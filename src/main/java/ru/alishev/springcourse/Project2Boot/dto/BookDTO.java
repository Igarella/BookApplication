package ru.alishev.springcourse.Project2Boot.dto;

import jakarta.persistence.*;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Person;

import java.util.Date;
import java.util.List;

public class BookDTO {
    private String title;
    private String authors;
    private int year;
    private Person owner;
    private Date assignedAt;

    @Transient
    private boolean isOutOfDAte;
}
