package com.kiok.service;

import com.kiok.Models.Admin;
import com.kiok.dao.AdminRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Бизнес-логика работы БД с администратором {@link Admin}
 * @author Кихтенко О.Ю. 10702120
 */
@Component
public class AdminService {
    /** Для работы с таблицей БД администратора */
    @Autowired
    private AdminRepos adminRepos;

    /**
     * Метод проверки логина и пароля в БД
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return объект из БД или null, если не нашли
     */
    public Admin verifyLogin(String login, String password) {
        return adminRepos.findByLoginAndPassword(login, password);
    }
}
