package ru.alishev.springcourse.Project2Boot.shpilevsky.general.models;

public interface IPerson
{
    int getId();

    void setId(int id);

    String getFullName();

    void setFullName(String fullName);

    int getBirthDate();

    void setBirthDate(int birthDate);
}
