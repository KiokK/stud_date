package com.kiok.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    private String lessonName;
    private String teacherName;
    private String typeOnSession; //зачет экзамен диф
    private String amountOfHours;
    private String typeOfLesson;

    @ManyToOne ( cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn (name="group_to_lesson_id")
    private Group group;

    public Lesson() {
    }

    public Lesson(String lessonName, String teacherName, String typeOnSession, String amountOfHours, String typeOfLesson, Group group) {
        this.lessonName = lessonName;
        this.teacherName = teacherName;
        this.typeOnSession = typeOnSession;
        this.amountOfHours = amountOfHours;
        this.typeOfLesson = typeOfLesson;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id) && Objects.equals(lessonName, lesson.lessonName) && Objects.equals(teacherName, lesson.teacherName) && Objects.equals(typeOnSession, lesson.typeOnSession) && Objects.equals(amountOfHours, lesson.amountOfHours) && Objects.equals(typeOfLesson, lesson.typeOfLesson) && Objects.equals(group, lesson.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonName, teacherName, typeOnSession, amountOfHours, typeOfLesson, group);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTypeOnSession() {
        return typeOnSession;
    }

    public void setTypeOnSession(String typeOnSession) {
        this.typeOnSession = typeOnSession;
    }

    public String getAmountOfHours() {
        return amountOfHours;
    }

    public void setAmountOfHours(String amountOfHours) {
        this.amountOfHours = amountOfHours;
    }

    public String getTypeOfLesson() {
        return typeOfLesson;
    }

    public void setTypeOfLesson(String typeOfLesson) {
        this.typeOfLesson = typeOfLesson;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
