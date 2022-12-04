package com.kiok.Models;

import java.sql.Date;

import javax.persistence.*;
/**
 * Класс сущность "Студент" связан с таблицей БД
 * @author Кихтенко О.Ю. 10702120
 */
@Entity
public class Student {
    /** Уникальный идентификатор объекта */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /** Имя студента */
    private String name;
    /** Фамилия студента */
    private String surname;
    /** Дата рождения студента */
    private Date dateOfBirth;
    /** № зачетной книжки студента */
    private String creditBook;
    /** № телефона студента */
    private String phoneNumber;
    /** Адресс студента */
    private String address;
    /** Указатель на старосту */
    private boolean studentLeader;

    /** Группа студента */
    @ManyToOne ( cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn (name="group_id")
    private Group group;

    /** Пустой коструктор создания объекта */
    public Student() {
    }

    /**
     * Коструктор создания объекта
     * @param name Имя студента
     * @param surname Фамилия студента
     * @param dateOfBirth Дата рождения студента
     * @param creditBook № зачетной книжки студента
     * @param phoneNumber № телефона студента
     * @param address Адресс студента
     * @param studentLeader true - если студент староста, иначе - false
     * @param group Группа студента
     */
    public Student(String name, String surname, Date dateOfBirth, String creditBook, String phoneNumber, String address, boolean studentLeader, Group group) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.creditBook = creditBook;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.studentLeader = studentLeader;
        this.group = group;
    }

    /**
     * Метод вызывается от объекта класса и копирует в него все поля
     * переданного объекта
     * @param otherStudent из которого копмруем данные
     */
    public void copy(Student otherStudent) {
        this.name = otherStudent.getName();
        this.surname = otherStudent.getSurname();
        this.dateOfBirth = otherStudent.getDateOfBirth();
        this.creditBook = otherStudent.getCreditBook();
        this.phoneNumber = otherStudent.getPhoneNumber();
        this.address = otherStudent.getAddress();
        this.studentLeader = otherStudent.getStudentLeader();
        this.group = otherStudent.getGroup();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCreditBook() {
        return creditBook;
    }

    public void setCreditBook(String creditBook) {
        this.creditBook = creditBook;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStudentLeader() {
        return studentLeader;
    }

    public void setStudentLeader(boolean studentLeader) {
        this.studentLeader = studentLeader;
    }
    public boolean getStudentLeader() {
        return this.studentLeader;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
