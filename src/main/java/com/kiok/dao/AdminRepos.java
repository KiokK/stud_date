package com.kiok.dao;

import com.kiok.Models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
/**
 * Класс взаимодействия с БД таблицы администратора,
 * наследуется от CrudRepository
 * @author Кихтенко О.Ю. 10702120
 */
@Component
@Repository
public interface AdminRepos extends CrudRepository<Admin, Long> {
    Admin findByLoginAndPassword(String login, String password);
}
