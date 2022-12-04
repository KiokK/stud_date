package com.kiok.dao;

import com.kiok.Models.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Класс взаимодействия с БД таблицы урока,
 * наследуется от CrudRepository
 * @author Кихтенко О.Ю. 10702120
 */
@Component
@Repository
public interface LessonRepos extends CrudRepository<Lesson,Long> {
    Optional<Lesson> findById (Long id);
}
