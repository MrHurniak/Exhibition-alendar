package ua.expo.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    //todo make role within enum
    private int role;
    private String email;
    private String login;
    private String password;

    public User() {
    }

    public User(int id, String name, String surname, int role, String email, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//    private enum ROLE{
//        ADMIN,
//        USER
//    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
