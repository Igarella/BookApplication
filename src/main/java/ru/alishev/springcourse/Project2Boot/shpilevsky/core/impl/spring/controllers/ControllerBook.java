package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.controllers;

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
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Book;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.services.ServiceBookSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.services.ServicePersonSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;

@Controller
@RequestMapping("/books")
public class ControllerBook {

    private final ServiceBookSpring serviceBookSpring;
    private final ServicePersonSpring servicePersonSpring;

    @Autowired
    public ControllerBook(ServiceBookSpring serviceBookSpring, ServicePersonSpring servicePersonSpring) {
        this.serviceBookSpring = serviceBookSpring;
        this.servicePersonSpring = servicePersonSpring;
    }

    @GetMapping()
    public String index(Model model, HttpServletRequest request) {
        String page = request.getParameter("page");
        String booksPerPage = request.getParameter("books_per_page");
        boolean sortByYear = Boolean.parseBoolean(request.getParameter("sort_by_year"));
        if (page == null || booksPerPage == null) {
            //if (sortByYear) {
                model.addAttribute("books",
                        serviceBookSpring.findAll(CdtsBook.ALL_YEAR.apply(), Sort.Direction.ASC));
                return "books/index";
            //}
            //model.addAttribute("books", serviceBook.findAll());
        } else {
            Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(booksPerPage));
            model.addAttribute("books", serviceBookSpring.findAll());
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        IBook book = serviceBookSpring.findFirst(CdtsBook.ONE_BY_ID.apply(id));
        model.addAttribute("book", book);
        if (book != null)
        {
            Person owner = (Person) book.getOwner();
            if (owner != null)
                model.addAttribute("owner", owner);
            else
                model.addAttribute("people", servicePersonSpring.findAll());
        }

        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", serviceBookSpring.find(CdtsBook.ONE_BY_ID.apply(id)));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        serviceBookSpring.update(id, book);
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

        serviceBookSpring.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson)
    {
        IBook book = serviceBookSpring.findFirst(CdtsBook.ONE_BY_ID.apply(id));
        if (book != null)
            serviceBookSpring.assign(book, selectedPerson);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        serviceBookSpring.delete(CdtsBook.ONE_BY_ID.apply(id));
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id)
    {
        IBook book = serviceBookSpring.findFirst(CdtsBook.ONE_BY_ID.apply(id));
        if (book != null)
            serviceBookSpring.release(book);
        return "redirect:/books/" + id;
    }

    @GetMapping("/pagingAndSortingBooks/{pageNumber}/{pageSize}")
    public String bookPagination(@PathVariable Integer pageNumber, @PathVariable Integer pageSize, Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "year");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        model.addAttribute("books", serviceBookSpring.findAll());
        return "books/index";
    }

    @GetMapping("/search")
    public String searchBook(@ModelAttribute("book") Book book) {
        return "books/search";
    }

    @PostMapping("/search")
    public String searchBook(Model model, @ModelAttribute("book") Book book) {
//        if (book.getAuthor() != null) {
//            IBook searchBook = serviceBookSpring.findFirst(CdtsBook.ONE_BY_AUTHOR.apply(book.getAuthor()));
//            if (searchBook != null) {
//                if (searchBook.getOwner() != null) {
//                    model.addAttribute("owner", searchBook);
//                } else {
//                    model.addAttribute("freeBook", searchBook);
//                }
//            } else {
//                model.addAttribute("emptyBook", book);
//            }
//            return "books/search";
//        }
        IBook searchBook = serviceBookSpring.findFirst(CdtsBook.ONE_BY_TITLE.apply(book.getTitle()));
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
