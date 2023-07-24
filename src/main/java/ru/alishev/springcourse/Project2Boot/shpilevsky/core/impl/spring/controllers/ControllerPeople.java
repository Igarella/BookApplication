package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Book;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.services.ServiceBookSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.services.ServicePersonSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsPerson;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.util.PersonValidator;

import java.util.List;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class ControllerPeople {

    private final ServicePersonSpring servicePersonSpring;
    private final ServiceBookSpring serviceBookSpring;
    private PersonValidator personValidator;

    @Autowired
    public ControllerPeople(ServicePersonSpring servicePersonSpring,
                            ServiceBookSpring serviceBookSpring,
                            PersonValidator personValidator) {
        this.servicePersonSpring = servicePersonSpring;
        this.serviceBookSpring = serviceBookSpring;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        System.out.println(model.addAttribute("people", servicePersonSpring.findAll()));

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("book") Book book) {
        model.addAttribute("person", servicePersonSpring.findOne(CdtsPerson.ONE_BY_ID.apply(id)));

        List<IBook> ownerBooks = serviceBookSpring.find(CdtsBook.MANY_BY_OWNER.apply(id));
        ownerBooks.removeIf(IBook::isOutOfDate);

        if (ownerBooks.isEmpty()) {
            model.addAttribute("emptys", "Человек пока не взял ни одной книги");
        } else {
            model.addAttribute("books", ownerBooks);
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

        servicePersonSpring.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", servicePersonSpring.findOne(CdtsPerson.ONE_BY_ID.apply(id)));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        servicePersonSpring.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        servicePersonSpring.delete(CdtsPerson.ONE_BY_ID.apply(id));
        return "redirect:/people";
    }
}
