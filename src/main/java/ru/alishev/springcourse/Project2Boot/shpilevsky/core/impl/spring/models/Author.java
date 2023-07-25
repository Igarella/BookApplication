package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IAuthor;

import java.util.List;

@Entity
@Table(name = "author")
@Data
public class Author extends ABaseEntity implements IAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany()
    //@JoinColumn(name = "book_id", referencedColumnName = "id")
    private List<Book> book;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", book=" + book +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public List<Book> getBook() {
        return null;
    }

    @Override
    public void setBook(List<Book> book) {

    }
}
