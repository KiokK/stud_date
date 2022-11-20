package com.kiok.Models;

import javax.persistence.*;

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

    @ManyToOne ( cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn (name="group_to_lesson_id")
    private Group group;

}
