package ru.alishev.springcourse.Project2Boot.shpilevsky.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.installer.IInstaller;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IAuthor;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;

import java.util.List;
import java.util.function.Predicate;

public class ServiceAuthor
{
    protected IDataStorage<IAuthor, Integer> repositoryAuthor = IInstaller.get().repositoryAuthor();

    public ServiceAuthor() {
    }

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
