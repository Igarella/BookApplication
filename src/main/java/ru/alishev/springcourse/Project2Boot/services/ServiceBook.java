package ru.alishev.springcourse.Project2Boot.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.Project2Boot.models.Book;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.conditions.CdtsBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.PredicateField;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.SortType;


import java.util.*;

@Service
@Transactional(readOnly = true)
public class ServiceBook {
    private final IDataStorage<Book, Integer> repositoryBook;
    @PersistenceContext
    private EntityManager entityManager;

    public void detachObject(Object o) {
        entityManager.detach(o);
    }

    public static void main(String[] args) {



    }
    @Autowired
    public ServiceBook(IDataStorage<Book, Integer> repositoryBook) {
        this.repositoryBook = repositoryBook;
    }

    public List<Book> findAll() {
        return repositoryBook.getAll();
    }
    public Book findOne(int id) {
        return repositoryBook.findFirst(CdtsBook.ONE_BY_ID.apply(id));
    }

    @Transactional
    public void save(Book book) {
        repositoryBook.add(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        repositoryBook.add(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        repositoryBook.delete(CdtsBook.ONE_BY_ID.apply(id));
    }

    public Person getBookOwnerByBookId(int id) {
        Book book = findOne(id);
        Person owner = book.getOwner();
        return owner;
    }

    public List<Book> getBooksByOwnerId(int id) {
        List<Book> ownerBooks = repositoryBook.find(CdtsBook.MANY_BY_OWNER.apply(id));
        for (Book ownerBook : ownerBooks) {
            isOutOfDate(ownerBook);
        }
        return ownerBooks;
    }


    @Transactional
    public void release(int id) {
        Book book = findOne(id);
       book.getOwner().getBooks().remove(book);
        book.setOwner(null);
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        Book book = findOne(id);
        book.setOwner(selectedPerson);
        book.setAssignedAt(new Date());
    }

//    public Page<Book> getBookPagination(Integer pageNumber, Integer pageSize) {
//        Sort sort = Sort.by(Sort.Direction.ASC, "year");
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
//        return bookRepository.findAll(pageable);
//    }

    // todo в dSSpring
    /*public Page<Book> findAll(Pageable pageable) {
//        Pageable pageable1 = PageRequest.of(pageable, Sort.by(Sort.Direction.ASC,"year"));
//        Sort sort = Sort.by(Sort.Direction.ASC, "year");
        return repositoryBook.findAll(pageable);
    }*/

    public List<Book> findAll(PredicateField<Book> condition, Sort.Direction sortDirection)
    {
        List<Book> list = repositoryBook.find(condition,
                sortDirection == Sort.Direction.ASC ? SortType.ASC : SortType.DESC);
        return list;
    }

    public Book searchBook(String title) {
       return repositoryBook.findFirst(CdtsBook.MANY_STARTING_TITLE_WITH.apply(title));
    }


    public Book isOutOfDate(Book book) {
        if (book.getAssignedAt() == null) {
            book.setOutOfDAte(false);
            return book;
        }
        long nowDate = new Date().getTime();
        long assignedAt = book.getAssignedAt().getTime();
        if (nowDate - assignedAt > 864000000) {
            book.setOutOfDAte(true);
        } else {
            book.setOutOfDAte(false);
        }
        return book;
    }
}
