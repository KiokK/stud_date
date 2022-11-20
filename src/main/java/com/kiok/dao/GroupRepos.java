package com.kiok.dao;

import com.kiok.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface GroupRepos extends CrudRepository<Group, Long> {
    Group findByGroupNumber(String groupNumber);
}
