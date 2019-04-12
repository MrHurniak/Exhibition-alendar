package ua.training.model.exceptions;

public class NotUniqLoginException extends RuntimeException {
    private String login;
    private String name;
    private String surname;
    private String email;

    public NotUniqLoginException(String login, String name, String surname, String email){
        super("Login \"" + login + "\" already exist!");
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
