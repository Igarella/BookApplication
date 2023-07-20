package ru.alishev.springcourse.Project2Boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.Project2Boot.models.Person;
import ru.alishev.springcourse.Project2Boot.repositories.RepositoryPeople;
@Component
public class PersonValidator implements Validator {

    private RepositoryPeople repositoryPeople;

    @Autowired
    public PersonValidator(RepositoryPeople repositoryPeople) {
        this.repositoryPeople = repositoryPeople;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (repositoryPeople.findFirstByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "This fullName is already taken");
        }
    }
}
