package com.kiok.dao;

import com.kiok.Models.Group;
import com.kiok.Models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Класс взаимодействия с БД таблицы студентов,
 * наследуется от CrudRepository
 * @author Кихтенко О.Ю. 10702120
 */
@Component
@Repository
public interface StudentRepos extends CrudRepository<Student, Long> {

}
