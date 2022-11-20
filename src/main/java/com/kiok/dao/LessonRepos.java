package com.kiok.dao;

import com.kiok.Models.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface LessonRepos extends CrudRepository<Lesson,Long> {
    Lesson findByLessonName(String lessonName);
}
