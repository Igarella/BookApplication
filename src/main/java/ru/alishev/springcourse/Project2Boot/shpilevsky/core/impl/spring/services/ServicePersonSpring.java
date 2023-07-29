package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.installer.InstallerSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.services.ServicePerson;

@Service
public class ServicePersonSpring extends ServicePerson
{
    public ServicePersonSpring(@Autowired InstallerSpring installerSpring){}
}
