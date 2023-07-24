package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Entity
@Table(name = "Person")
public class Person extends ABaseEntity implements IPerson
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String fullName;

    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "year")
    private int birthDate;

    public Person() {

    }

    public Person(String fullName, int birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
//                ", books=" + books +
                '}';
    }
}
