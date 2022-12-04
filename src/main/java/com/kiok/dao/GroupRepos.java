package com.kiok.dao;

import com.kiok.Models.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
/**
 * Класс взаимодействия с БД таблицы группы,
 * наследуется от CrudRepository
 * @author Кихтенко О.Ю. 10702120
 */
@Component
@Repository
public interface GroupRepos extends CrudRepository<Group, Long> {
    Group findByGroupNumber(String groupNumber);
}
