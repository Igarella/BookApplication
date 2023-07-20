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
import ru.alishev.springcourse.Project2Boot.repositories.RepositoryBook;
import ru.alishev.springcourse.Project2Boot.repositories.RepositoryPeople;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServiceBook {
    private final RepositoryBook repositoryBook;
    @PersistenceContext
    private EntityManager entityManager;

    public void detachObject(Object o) {
        entityManager.detach(o);
    }

    public static void main(String[] args) {



    }
    @Autowired
    public ServiceBook(RepositoryBook repositoryBook, RepositoryPeople repositoryPeople) {
        this.repositoryBook = repositoryBook;
//        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return repositoryBook.findAll();
    }
    public Book findOne(int id) {
        Optional<Book> foundBook = repositoryBook.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        repositoryBook.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        repositoryBook.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        repositoryBook.deleteById(id);
    }

    public Person getBookOwnerByBookId(int id) {
        Book book = findOne(id);
        Person owner = book.getOwner();
        return owner;
    }

    public List<Book> getBooksByOwnerId(int id) {
        List<Book> ownerBooks = repositoryBook.findBooksByOwnerId(id);
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

    public Page<Book> findAll(Pageable pageable) {
//        Pageable pageable1 = PageRequest.of(pageable, Sort.by(Sort.Direction.ASC,"year"));
//        Sort sort = Sort.by(Sort.Direction.ASC, "year");
        return repositoryBook.findAll(pageable);
    }

    public List<Book> findAll(Sort sort) {
//        Pageable pageable1 = PageRequest.of(pageable, Sort.by(Sort.Direction.ASC,"year"));
//        Sort sort = Sort.by(Sort.Direction.ASC, "year");
        return repositoryBook.findAll(sort);
    }

    public Book searchBook(String title) {
       return repositoryBook.findByTitleStartingWith(title);
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
