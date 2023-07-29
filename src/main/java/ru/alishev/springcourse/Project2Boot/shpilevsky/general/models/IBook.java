package ru.alishev.springcourse.Project2Boot.shpilevsky.general.models;

import java.util.Date;
import java.util.List;

public interface IBook {

    IPerson getOwner();

    void setOwner(IPerson owner);

    int getId();

    void setId(int id);

    String getTitle();

    void setTitle(String title);

    String getAuthors();
    List<String> getAuthorsAsList();

    void setAuthors(String authors);

    int getYear();

    void setYear(int year);
    Date getAssignedAt();
    void setAssignedAt(Date date);

    void setOutOfDate(boolean isOutOfDate);
    boolean isOutOfDate();
}
