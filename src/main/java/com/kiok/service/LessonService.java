package com.kiok.service;

import com.kiok.Models.Lesson;
import com.kiok.dao.LessonRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LessonService {
    @Autowired
    private LessonRepos lessonRepos;

    public Lesson create(Lesson lesson){
        if (lesson == null) return null;
        return lessonRepos.save(lesson);
    }
}
