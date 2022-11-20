package com.kiok.service;

import com.kiok.Models.Admin;
import com.kiok.Models.Student;
import com.kiok.dao.StudentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Бизнес-логика работы с пользователями
 */
@Service
@Component
public class StudentService {
    @Autowired
    private StudentRepos studentRepos;

    public Student save(Student student) {
        if (student == null)
            return null;
        return studentRepos.save(student);
    }

}