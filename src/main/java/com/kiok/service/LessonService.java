package com.kiok.service;

import com.kiok.Models.Group;
import com.kiok.Models.Lesson;
import com.kiok.dao.LessonRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class LessonService {
    @Autowired
    private LessonRepos lessonRepos;

    public Lesson create(Lesson lesson){
        if (lesson == null) return null;
        return lessonRepos.save(lesson);
    }

    public List<Lesson> findAllByGroup(Group group){
        return lessonRepos.findByGroup(group);
    }

    public Lesson save(Lesson lesson){
        return lessonRepos.save(lesson);
    }

    public Lesson findById(Long id){
        Optional<Lesson> lesson = lessonRepos.findById(id);
        if (lesson == null)
            return null;
        else
            return lesson.get();
    }

    public void delete(Lesson lesson) {
        lessonRepos.delete(lesson);
    }
}
