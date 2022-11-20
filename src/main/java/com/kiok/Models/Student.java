package com.kiok.Models;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String creditBook;
    private String phoneNumber;
    private String address;
    private boolean studentLeader;

    @ManyToOne ( cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn (name="group_id")
    private Group group;

//    @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<SkippingLesson> skippingLessonList = new ArrayList<>();


    public Student() {
    }

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
