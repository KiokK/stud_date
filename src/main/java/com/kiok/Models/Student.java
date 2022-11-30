package com.kiok.Models;

import java.sql.Date;
import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentLeader == student.studentLeader &&
                id == student.id &&
                name.equals(student.name) &&
                surname.equals(student.surname) &&
                creditBook.equals(student.creditBook) &&
                phoneNumber.equals(student.phoneNumber) &&
                address.equals(student.address) &&
                group.getGroupNumber().equals(student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, dateOfBirth, creditBook, phoneNumber, address, studentLeader, group);
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
