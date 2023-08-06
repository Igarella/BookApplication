package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Book extends ABaseEntity implements IBook
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 2, max = 30, message = "name should be between 2 and 20 characters")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "автор не может быть пустым")
//    @Size(min = 2, max = 20, message = "имя автора должно быть между 2 и 10 символов")
    @Column(name = "authors")
    private String authors;
    @Min(value = 1500, message = "year should be higher than 1500")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedAt;

    @Transient
    private boolean isOutOfDate;


    public Book(@JsonProperty("arg1")String title, @JsonProperty("arg2")String authors, @JsonProperty("arg3")int year) {
        this.title = title;
        this.authors = authors;
        this.year = year;
    }
    @JsonCreator
    public Book() {
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(IPerson owner) {
        this.owner = (Person) owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    @Override
    public List<String> getAuthorsAsList()
    {
        if (this.authors != null) {
            return new ArrayList<>(List.of(this.authors.split(",")));
        } else {
            return new ArrayList<>();
        }
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", year=" + year +
//                ", owner=" + owner +
                ", assignedAt=" + assignedAt +
                ", isOutOfDAte=" + isOutOfDate +
                '}';
    }
}
