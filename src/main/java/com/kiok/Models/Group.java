package com.kiok.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс сущность "Группа" связан с таблицей БД
 * @author Кихтенко О.Ю. 10702120
 */
@Entity
@Table(name = "group_stud")
public class Group {
    /** Уникальный идентификатор объекта */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    /** Номер группы */
    private String groupNumber;

    /** Список студентов данной группы */
    @OneToMany( cascade={CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Student> studentList;

    /** Список уроков данной группы */
    @OneToMany( cascade={CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<Lesson> lessons;

    /**
     * Конструктор создания объекта
     * @param groupNumber номер группы
     */
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
        studentList = studentList.stream().sorted((o1, o2)->o1.getSurname().
                compareTo(o2.getSurname())).
                collect(Collectors.toList());
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

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
}
