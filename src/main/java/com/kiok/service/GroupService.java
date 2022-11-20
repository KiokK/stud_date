package com.kiok.service;

import com.kiok.Models.Group;
import com.kiok.Panels.newPanel.NewLessonsPanel;
import com.kiok.dao.GroupRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class GroupService {
    @Autowired
    private GroupRepos groupRepos;

    public Group findByGroupNumber(String groupNumber) {
        return groupRepos.findByGroupNumber(groupNumber);
    }

    public Group save(Group group) {
        return groupRepos.save(group);
    }

    public List<Group> findAll(){
        List<Group> ans = (List<Group>) groupRepos.findAll();
        if (ans == null)
            return new ArrayList<Group>();
        return ans ;
    }
    public ArrayList<String> findAllNumbers(){
        List<Group> all = findAll();
        ArrayList<String> allNumbers = new ArrayList<>(all.size());
        for (Group el : all) {
            allNumbers.add(el.getGroupNumber());
        }
        return allNumbers;
    }
}
