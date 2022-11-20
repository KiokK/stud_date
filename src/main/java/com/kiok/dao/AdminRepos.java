package com.kiok.dao;

import com.kiok.Models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Repository
public interface AdminRepos extends CrudRepository<Admin, Long> {
    Admin findByLoginAndPassword(String login, String password);
}
