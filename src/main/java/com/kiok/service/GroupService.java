package com.kiok.service;

import com.kiok.Models.Group;
import com.kiok.Models.Lesson;
import com.kiok.Models.Student;
import com.kiok.dao.GroupRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public Group deleteLesson(Lesson lesson, Group group) {
        Set<Lesson> ll = group.getLessons();
        ll.remove(lesson);
        group.setLessons(ll);
//        groupRepos.delete(group);
        return save(group);
    }

    public Group addStudent(Group group, Student student) {
        List<Student> st = group.getStudentList();
        st.add(student);
        group.setStudentList(st);
        return save(group);
    }
    public Group deleteStudent(Group group, Student student) {
        List<Student> st = group.getStudentList();
        st.remove(student);
        group.setStudentList(st);
        return save(group);
    }
}
