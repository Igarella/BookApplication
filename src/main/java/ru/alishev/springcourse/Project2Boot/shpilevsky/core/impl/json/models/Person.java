package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.ABaseEntity;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;

/**
 * @author Neil Alishev
 */

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

    @JsonCreator
    public Person() {

    }

    @JsonCreator
    public Person(@JsonProperty("arg1")String fullName, @JsonProperty("arg2")int birthDate) {
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
