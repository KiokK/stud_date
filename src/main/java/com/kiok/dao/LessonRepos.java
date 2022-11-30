package com.kiok.dao;

import com.kiok.Models.Group;
import com.kiok.Models.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public interface LessonRepos extends CrudRepository<Lesson,Long> {
    Lesson findByLessonName(String lessonName);
    List<Lesson> findByGroup(Group group);

    Optional<Lesson> findById (Long id);
}
