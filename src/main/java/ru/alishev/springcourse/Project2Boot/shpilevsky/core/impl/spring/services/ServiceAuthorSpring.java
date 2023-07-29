package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.installer.InstallerSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.models.Author;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.services.ServiceAuthor;
import ru.alishev.springcourse.Project2Boot.shpilevsky.lib.IDataStorage;

@Service
public class ServiceAuthorSpring extends ServiceAuthor
{
    public ServiceAuthorSpring(@Autowired InstallerSpring installerSpring) {}
}
