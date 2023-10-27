package ru.job4j.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int inn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Person> person;

    public static Employee of(String name, String surname, int inn) {
        Employee employee = new Employee();
        employee.name = name;
        employee.surname = surname;
        employee.inn = inn;
//        employee.created = Calendar.getInstance();
//        employee.created.setTimeInMillis(System.currentTimeMillis());
        return employee;
    }

    public void addPerson(Person p) {
        person.add(p);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Collection<Person> getPerson() {
        return person;
    }

    public void setPerson(Collection<Person> person) {
        this.person = person;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }
}
