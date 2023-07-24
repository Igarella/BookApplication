package ru.alishev.springcourse.Project2Boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsPerson;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Person;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;

@Component
public class PersonValidator implements Validator {

    private IDataStorage<Person, Integer> repositoryPerson;

    @Autowired
    public PersonValidator(IDataStorage<Person, Integer> repositoryPerson) {
        this.repositoryPerson = repositoryPerson;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (repositoryPerson.find(CdtsPerson.ONE_BY_NAME.apply(person.getFullName())) != null) {
            errors.rejectValue("fullName", "", "This fullName is already taken");
        }
    }
}
