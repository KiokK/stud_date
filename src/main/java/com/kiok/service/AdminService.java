package com.kiok.service;

import com.kiok.Models.Admin;
import com.kiok.dao.AdminRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Бизнес-логика работы с пользователями
 */
@Service
@Component
public class AdminService {
    @Autowired
    private AdminRepos adminRepos;

    public Admin verifyLogin(String login, String password) {

        Admin admin = adminRepos.findByLoginAndPassword(login, password);

        return admin;

    }
}
