package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Book")
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
    @Size(min = 2, max = 20, message = "имя автора должно быть между 2 и 10 символов")
    @Column(name = "author")
    private String author;
    @Min(value = 1500, message = "year should be higher than 1500")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedAt;

    @ManyToMany(mappedBy = "book")
    private List<Author> authors;

    @Transient
    private boolean isOutOfDate;


    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
                ", author='" + author + '\'' +
                ", year=" + year +
//                ", owner=" + owner +
                ", assignedAt=" + assignedAt +
                ", isOutOfDAte=" + isOutOfDate +
                '}';
    }
}
