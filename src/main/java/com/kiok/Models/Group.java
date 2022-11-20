package com.kiok.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "group_stud")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    private String groupNumber;

//    @OneToOne
//    private Timetable timetable = null;

    //одной группе множество студентов
    @OneToMany( cascade=CascadeType.MERGE, fetch = FetchType.LAZY)//каждый раз при получении группы получаем и информацию о студенте
    private List<Student> studentList;

    @OneToMany( cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Lesson> lessons;

    public Group(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Group() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
