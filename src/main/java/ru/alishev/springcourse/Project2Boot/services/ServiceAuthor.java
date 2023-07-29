//package ru.alishev.springcourse.Project2Boot.services;
//
//import org.springframework.stereotype.Service;
//import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Author;
//import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Book;
//import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsAuthor;
//import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;
//
//import java.util.List;
//
//@Service
//public class ServiceAuthor {
//
//    private final IDataStorage<Author, Integer> repositoryAuthor;
//
//    public ServiceAuthor(IDataStorage<Author, Integer> repositoryAuthor) {
//        this.repositoryAuthor = repositoryAuthor;
//    }
//
//    public List<Author> findAll() {
//        List<Author> authors = repositoryAuthor.getAll();
//        return authors;
//    }
//
//    public void createAuthor(Author author) {
//        repositoryAuthor.add(author);
//    }
//
//    public Author findOneById(int id) {
//        return repositoryAuthor.findFirst(CdtsAuthor.ONE_BY_ID.apply(id));
//    }
//}
