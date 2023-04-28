package ru.alishev.springcourse.Project2Boot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.Project2Boot.models.Book;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.services.BookService;
import ru.alishev.springcourse.Project2Boot.services.PeopleService;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model, HttpServletRequest request) {
        String page = request.getParameter("page");
        String booksPerPage = request.getParameter("books_per_page");
        boolean sortByYear = Boolean.parseBoolean(request.getParameter("sort_by_year"));
        if (page == null || booksPerPage == null) {
            if (sortByYear) {
                Sort sort = Sort.by(Sort.Direction.ASC, "year");
                model.addAttribute("books", bookService.findAll(sort));
                return "books/index";
            }
            model.addAttribute("books", bookService.findAll());
        } else {
            Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(booksPerPage));
            model.addAttribute("books", bookService.findAll(pageable));
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(id));
        Person owner = bookService.getBookOwnerByBookId(id);
        if (owner != null) {
            model.addAttribute("owner", owner);
        } else {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookService.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookService.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/pagingAndSortingBooks/{pageNumber}/{pageSize}")
    public String bookPagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "year");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        model.addAttribute("books", bookService.findAll(pageable));
        return "books/index";
    }

    @GetMapping("/search")
    public String searchBook(@ModelAttribute("book") Book book) {
        return "books/search";
    }

    @PostMapping("/search")
    public String searchBook(Model model, @ModelAttribute("book") Book book) {
        Book searchBook = bookService.searchBook(book.getTitle());
        if (searchBook != null) {
            if (searchBook.getOwner() != null) {
                model.addAttribute("owner", searchBook);
            } else {
                model.addAttribute("freeBook", searchBook);
            }
        } else {
            model.addAttribute("emptyBook", book);
        }
        return "books/search";
    }

//    @GetMapping("/assigned")
//    public String assigned(Model model) {
//        Book bookOutOfDate = bookService.isOutOfDate();
//        model.addAttribute("outOfDate", bookOutOfDate);
//        return "books/assigned";
//    }
}
