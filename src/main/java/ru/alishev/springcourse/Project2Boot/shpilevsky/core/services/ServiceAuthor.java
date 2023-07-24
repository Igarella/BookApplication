package ru.alishev.springcourse.Project2Boot.shpilevsky.core.services;

import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Author;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.repository.conditions.CdtsAuthor;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.installer.IInstaller;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IAuthor;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;

import java.util.List;
import java.util.function.Predicate;

public class ServiceAuthor
{
    protected final IDataStorage<IAuthor, Integer> repositoryAuthor = IInstaller.get().repositoryAuthor();

    public List<IAuthor> findAll() {
        List<IAuthor> authors = repositoryAuthor.getAll();
        return authors;
    }

    public void createAuthor(IAuthor author) {
        repositoryAuthor.add(author);
    }

// было findOneById(int id) стало findOne(Predicate)
    public IAuthor findOne(Predicate<IAuthor> condition) {
        return repositoryAuthor.findFirst(condition);
    }



}
