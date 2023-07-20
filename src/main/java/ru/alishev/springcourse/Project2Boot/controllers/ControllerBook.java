package ru.alishev.springcourse.Project2Boot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.Project2Boot.models.Book;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.services.ServiceBook;
import ru.alishev.springcourse.Project2Boot.services.ServicePeople;

@Controller
@RequestMapping("/books")
public class ControllerBook {

    private final ServiceBook serviceBook;
    private final ServicePeople servicePeople;

    @Autowired
    public ControllerBook(ServiceBook serviceBook, ServicePeople servicePeople) {
        this.serviceBook = serviceBook;
        this.servicePeople = servicePeople;
    }

    @GetMapping()
    public String index(Model model, HttpServletRequest request) {
        String page = request.getParameter("page");
        String booksPerPage = request.getParameter("books_per_page");
        boolean sortByYear = Boolean.parseBoolean(request.getParameter("sort_by_year"));
        if (page == null || booksPerPage == null) {
            if (sortByYear) {
                Sort sort = Sort.by(Sort.Direction.ASC, "year");
                model.addAttribute("books", serviceBook.findAll(sort));
                return "books/index";
            }
            model.addAttribute("books", serviceBook.findAll());
        } else {
            Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(booksPerPage));
            model.addAttribute("books", serviceBook.findAll(pageable));
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", serviceBook.findOne(id));
        Person owner = serviceBook.getBookOwnerByBookId(id);
        if (owner != null) {
            model.addAttribute("owner", owner);
        } else {
            model.addAttribute("people", servicePeople.findAll());
        }
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", serviceBook.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        serviceBook.update(id, book);
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

        serviceBook.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        serviceBook.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        serviceBook.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        serviceBook.release(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/pagingAndSortingBooks/{pageNumber}/{pageSize}")
    public String bookPagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "year");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        model.addAttribute("books", serviceBook.findAll(pageable));
        return "books/index";
    }

    @GetMapping("/search")
    public String searchBook(@ModelAttribute("book") Book book) {
        return "books/search";
    }

    @PostMapping("/search")
    public String searchBook(Model model, @ModelAttribute("book") Book book) {
        Book searchBook = serviceBook.searchBook(book.getTitle());
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
