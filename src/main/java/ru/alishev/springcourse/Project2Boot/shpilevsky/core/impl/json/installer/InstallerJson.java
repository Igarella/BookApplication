package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.json.installer;

import ru.alishev.springcourse.Project2Boot.shpilevsky.core.installer.IInstaller;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IBook;
import ru.alishev.springcourse.Project2Boot.shpilevsky.general.models.IPerson;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;

public class InstallerJson extends IInstaller {
    @Override
    public <E extends IPerson> IDataStorage<E, Integer> repositoryPerson() {
        return null;
    }

    @Override
    public <E extends IBook> IDataStorage<E, Integer> repositoryBook() {
        return null;
    }

    @Override
    public void install() {

    }
}
