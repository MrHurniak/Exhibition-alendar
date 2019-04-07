package ua.training.model.entity;

import java.io.Serializable;
import java.util.Objects;

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
    public Ticket(User user, Exposition exposition){
        this.user = user;
        this.exposition = exposition;
        this.id = 0;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(user, ticket.user) &&
                Objects.equals(exposition, ticket.exposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, exposition);
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
