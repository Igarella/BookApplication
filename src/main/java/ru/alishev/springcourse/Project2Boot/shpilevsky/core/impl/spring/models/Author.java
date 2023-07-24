package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
public class Author extends ABaseEntity {
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
}
