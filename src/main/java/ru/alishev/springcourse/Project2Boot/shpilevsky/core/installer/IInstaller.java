package ru.alishev.springcourse.Project2Boot.shpilevsky.core.installer;

import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;


public abstract class IInstaller
{
    public abstract <E extends IPerson> IDataStorage<E, Integer> repositoryPerson();
    public abstract <E extends IBook> IDataStorage<E, Integer> repositoryBook();
//    public abstract <E extends
    public abstract void install();

    private static IInstaller INSTALLER = null;

    public IInstaller()
    {
        INSTALLER = this;
    }

    public static IInstaller get()
    {
        return INSTALLER;
    }
}
