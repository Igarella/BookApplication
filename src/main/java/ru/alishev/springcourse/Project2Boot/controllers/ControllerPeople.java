package ru.alishev.springcourse.Project2Boot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.Project2Boot.models.Book;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.services.ServiceBook;
import ru.alishev.springcourse.Project2Boot.services.ServicePeople;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.conditions.CdtsPerson;
import ru.alishev.springcourse.Project2Boot.util.PersonValidator;

import java.util.List;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class ControllerPeople {

    private final ServicePeople servicePeople;
    private final ServiceBook serviceBook;
    private PersonValidator personValidator;

    @Autowired
    public ControllerPeople(ServicePeople servicePeople, ServiceBook serviceBook, PersonValidator personValidator) {
        this.servicePeople = servicePeople;
        this.serviceBook = serviceBook;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        System.out.println(model.addAttribute("people", servicePeople.findAll()));

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("book") Book book) {
        model.addAttribute("person", servicePeople.findOne(CdtsPerson.ONE_BY_ID.apply(id)));
        List<Book> bookList = serviceBook.getBooksByOwnerId(id);
        if (bookList.isEmpty()) {
            model.addAttribute("emptys", "Человек пока не взял ни одной книги");
        } else {
            model.addAttribute("books", bookList);

        }
        return "people/show";
    }

    // TODO: 28.03.2023 сделать два ключа одинаковыми 
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person")  @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        servicePeople.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", servicePeople.findOne(CdtsPerson.ONE_BY_ID.apply(id)));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        servicePeople.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        servicePeople.delete(CdtsPerson.ONE_BY_ID.apply(id));
        return "redirect:/people";
    }
}
