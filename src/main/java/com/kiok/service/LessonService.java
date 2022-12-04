package com.kiok.service;

import com.kiok.Models.Lesson;
import com.kiok.dao.LessonRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Бизнес-логика работы БД с уроком {@link Lesson}
 * @author Кихтенко О.Ю. 10702120
 */
@Component
public class LessonService {
    /** Для работы с таблицей БД уроков */
    @Autowired
    private LessonRepos lessonRepos;

    /**
     * Метод для сохранения урок в БД
     * @param lesson которого сохраняем
     * @return сохраненный объект или null, если не сохранилось
     */
    public Lesson save(Lesson lesson){
        return lessonRepos.save(lesson);
    }

    /**
     * Метод удаляет урок из БД
     * @param lesson урок, который хотим удалить
     */
    public void delete(Lesson lesson) {
        lessonRepos.delete(lesson);
    }
}
