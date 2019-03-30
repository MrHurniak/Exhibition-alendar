package ua.expo.model.entity;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int id;
    private User user;
    private Exposition exposition;

    public Ticket() {
    }

    public Ticket(int id, User user, Exposition exposition) {
        this.id = id;
        this.user = user;
        this.exposition = exposition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", exposition=" + exposition +
                '}';
    }
}
