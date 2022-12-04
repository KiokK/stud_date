package com.kiok.service;

import com.kiok.Models.Student;
import com.kiok.dao.StudentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Бизнес-логика работы БД с пользователями {@link Student}
 * @author Кихтенко О.Ю. 10702120
 */
@Component
public class StudentService {
    /** Для работы с таблицей БД студентов */
    @Autowired
    private StudentRepos studentRepos;

    /**
     * Метод для сохранения пользователя в БД
     * @param student которого сохраняем
     * @return сохраненный объект или null, если не сохранилось
     */
    public Student save(Student student) {
        if (student == null)
            return null;
        return studentRepos.save(student);
    }

}