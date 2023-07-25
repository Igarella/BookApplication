package ru.alishev.springcourse.Project2Boot.shpilevsky.general.models;

import java.util.Date;

public interface IBook {

    IPerson getOwner();

    void setOwner(IPerson owner);

    int getId();

    void setId(int id);

    String getTitle();

    void setTitle(String title);

    String getAuthor();

    void setAuthor(String author);

    int getYear();

    void setYear(int year);
    Date getAssignedAt();
    void setAssignedAt(Date date);

    void setOutOfDate(boolean isOutOfDate);
    boolean isOutOfDate();
}
