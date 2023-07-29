package ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.installer.InstallerSpring;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.services.ServiceBook;

@Service
public class ServiceBookSpring extends ServiceBook
{
    public ServiceBookSpring(@Autowired InstallerSpring installerSpring){}
//    public Page<Book> getBookPagination(Integer pageNumber, Integer pageSize) {
//        Sort sort = Sort.by(Sort.Direction.ASC, "year");
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
//        return bookRepository.findAll(pageable);
//    }

    // todo в dSSpring
    /*public Page<Book> findAll(Pageable pageable) {
//        Pageable pageable1 = PageRequest.of(pageable, Sort.by(Sort.Direction.ASC,"year"));
//        Sort sort = Sort.by(Sort.Direction.ASC, "year");
        return repositoryBook.findAll(pageable);
    }*/
}
