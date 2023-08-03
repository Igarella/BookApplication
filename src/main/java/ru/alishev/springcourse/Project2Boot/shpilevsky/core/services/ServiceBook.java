package ru.alishev.springcourse.Project2Boot.shpilevsky.core.services;

import org.springframework.data.domain.Sort;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.installer.IInstaller;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.SortType;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class ServiceBook
{
    protected final IDataStorage<IBook, Integer> repositoryBook = IInstaller.get().repositoryBook();

    public void addAuthor(int id, String authorName) {
        IBook book = findFirst(CdtsBook.ONE_BY_ID.apply(id));
        book.setAuthors(book.getAuthors() == null || book.getAuthors().isEmpty() ? book.getAuthors() + authorName : book.getAuthors() + ", " + authorName);
        save(book);
    }

    public void deleteAuthor(int id, String authorName) {
        IBook iBook = findFirst(CdtsBook.ONE_BY_ID.apply(id));
        String authors = iBook.getAuthors();

        if (authors.contains(authorName)) {
            String result;

            if (authors.contains(", " + authorName)) {
                result = authors.replace(", " + authorName, "");
            } else {
                result = authors.replace(authorName + ",", "");
            }

            iBook.setAuthors(result);
            update(iBook.getId(), iBook);
            System.out.println("everything is ok");
        }

    }

    public void delete(Predicate<IBook> condition)
    {
        repositoryBook.delete(condition);
    }

    public IBook findFirst(Predicate<IBook> condition)
    {
        return updateBook(repositoryBook.findFirst(condition));
    }

    public List<IBook> findAll()
    {
        return updateInList(repositoryBook.getAll());
    }

    public List<IBook> findAll(Predicate<IBook> condition, Sort.Direction sortDirection)
    {
        return updateInList(repositoryBook.find(condition,
                sortDirection == Sort.Direction.ASC ? SortType.ASC : SortType.DESC));
    }

    public List<IBook> find(Predicate<IBook> condition)
    {
        return updateInList(repositoryBook.find(condition));
    }

    public IPerson getBookOwner(IBook book)
    {
        IPerson owner = book.getOwner();
        return owner;
    }

    public void save(IBook book) {
        repositoryBook.add(book);
    }

    public void update(int id, IBook updatedBook)
    {
        updatedBook.setId(id);
        repositoryBook.add(updatedBook);
    }

    public void release(IBook book)
    {
        book.setOwner(null);
        save(book);
    }

    public void assign(IBook book, IPerson taker)
    {
        book.setOwner(taker);
        book.setAssignedAt(new Date());
    }

    private IBook updateBook(IBook book)
    {
        updateOutOfDate(book);
        return book;
    }

    private List<IBook> updateInList(List<IBook> books)
    {
        books.forEach(this::updateBook);
        return books;
    }

    public void updateOutOfDate(IBook book)
    {
        if (book.getAssignedAt() == null)
            book.setOutOfDate(false);
        else
        {
            long nowDate = new Date().getTime();
            long assignedAt = book.getAssignedAt().getTime();
            book.setOutOfDate(nowDate - assignedAt > 864000000);
        }
    }
}
