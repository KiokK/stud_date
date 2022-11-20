package com.kiok.dao;

import com.kiok.Models.Group;
import com.kiok.Models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface StudentRepos extends CrudRepository<Student, Long> {
    Student findBySurname(String username);
    List<Student> findByGroup(Group group);
}
