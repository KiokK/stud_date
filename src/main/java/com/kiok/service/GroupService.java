package com.kiok.service;

import com.kiok.Models.Group;
import com.kiok.Models.Lesson;
import com.kiok.Models.Student;
import com.kiok.dao.GroupRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Бизнес-логика работы БД с группой студентов {@link Group}
 * @author Кихтенко О.Ю. 10702120
 */
@Component
public class GroupService {
    /** Для работы с таблицей БД группы */
    @Autowired
    private GroupRepos groupRepos;

    /**
     * Метод приска группы по номеру
     * @param groupNumber номер группы, которую ищем
     * @return объект группы или null, если не нашли
     */
    public Group findByGroupNumber(String groupNumber) {
        return groupRepos.findByGroupNumber(groupNumber);
    }

    /**
     * Метод сохраняет группу в БД
     * @param group группа, котрую сохраняем
     * @return сохраненный объект
     */
    public Group save(Group group) {
        return groupRepos.save(group);
    }

    /**
     * Метод поиска всех групп в БД
     * @return все сохраненные группы из БД или пустой ArrayList
     */
    public List<Group> findAll(){
        List<Group> ans = (List<Group>) groupRepos.findAll();
        if (ans == null)
            return new ArrayList<Group>();
        return ans ;
    }
    /**
     * Метод поиска всех номеров групп в БД
     * @return все номера группы из БД или пустой ArrayList
     */
    public ArrayList<String> findAllNumbers(){
        List<Group> all = findAll();
        if (all == null) return new ArrayList<>();
        ArrayList<String> allNumbers = new ArrayList<>(all.size());
        for (Group el : all) {
            allNumbers.add(el.getGroupNumber());
        }
        return allNumbers;
    }

    /**
     * Метод удаляет урок у группы
     * @param lesson урок, который удаляем
     * @param group группа, из которой удаляем урок
     * @return группа с удаленным значением
     */
    public Group deleteLesson(Lesson lesson, Group group) {
        Set<Lesson> ll = group.getLessons();
        ll.remove(lesson);
        group.setLessons(ll);
        return save(group);
    }
    /**
     * Метод добавляет студента группе
     * @param student студент, которого добавляем
     * @param group группа, которой добавляем
     * @return группа с добавленным значением
     */
    public Group addStudent(Group group, Student student) {
        List<Student> st = group.getStudentList();
        st.add(student);
        group.setStudentList(st);
        return save(group);
    }
    /**
     * Метод удаляет студента у группы
     * @param student студент, которого удаляем
     * @param group группа, из которой удаляем студента
     * @return группа с удаленным значением
     */
    public Group deleteStudent(Group group, Student student) {
        List<Student> st = group.getStudentList();
        st.remove(student);
        group.setStudentList(st);
        return save(group);
    }
}
