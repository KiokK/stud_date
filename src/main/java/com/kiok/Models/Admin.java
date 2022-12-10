package com.kiok.Models;

import javax.persistence.*;
/**
 * Класс сущность "Администратор" связан с таблицей БД
 * @author Кихтенко О.Ю. 10702120
 */
@Entity
public class Admin {
    /** Уникальный идентификатор объекта */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    /** Логин администратора */
    private String login;
    /** Пароль администратора */
    private String password;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public Admin() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
