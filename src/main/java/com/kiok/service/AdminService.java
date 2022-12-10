package com.kiok.service;

import com.kiok.Models.Admin;
import com.kiok.dao.AdminRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * Проверяет таблицу БД на пустоту
     * @return true, если таблица пустая, иначе - false
     */
    public boolean isEmpty(){
        if (adminRepos.findAll() == null || adminRepos.findAll().size() == 0)
            return true;
        return false;
    }

    /**
     * Сохраняет нового админа в БД
     * @param admin которого сохраняем
     * @return результат сохранения из бд
     */
    public Admin save(Admin admin) {
        return adminRepos.save(admin);
    }
}
