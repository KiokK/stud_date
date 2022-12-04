package com.kiok.Models;

import javax.persistence.*;
/**
 * Класс сущность "Урок" связан с таблицей БД
 * @author Кихтенко О.Ю. 10702120
 */
@Entity
public class Lesson {
    /** Уникальный идентификатор объекта */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    /** Название предмета */
    private String lessonName;
    /** Имя преподавателя */
    private String teacherName;

    /** Тип экзаменации */
    private String typeOnSession; //зачет экзамен диф

    /** Количество часов на изучение */
    private String amountOfHours;

    /** Тип проведения урока (лекция, семминар, практика, лабораторная)*/
    private String typeOfLesson;
    /** Группа, у которой этот предмет проводится */
    @ManyToOne ( cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn (name="group_to_lesson_id")
    private Group group;

    public Lesson() {
    }

    /**
     * Конструктор создания объекта
     * @param lessonName Название предмета
     * @param teacherName Имя преподавателя
     * @param typeOnSession Тип экзаменации
     * @param amountOfHours Количество часов на изучение
     * @param typeOfLesson Тип проведения урока (ЛК., СЕМ., ПР., ЛР.)
     * @param group Группа, у которой этот предмет проводится
     */
    public Lesson(String lessonName, String teacherName, String typeOnSession, String amountOfHours, String typeOfLesson, Group group) {
        this.lessonName = lessonName;
        this.teacherName = teacherName;
        this.typeOnSession = typeOnSession;
        this.amountOfHours = amountOfHours;
        this.typeOfLesson = typeOfLesson;
        this.group = group;
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
