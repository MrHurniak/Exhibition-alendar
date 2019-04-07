package ua.training.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Exposition implements Serializable {
    private int id;
    private int price;
    private String theme;
    private String shortDescription;
    private String fullDescription;
    private ExhibitionHall hall;

    public Exposition() {
    }

    public Exposition(int id, int price,
                      String theme, String shortDescription,
                      String fullDescription, ExhibitionHall hall) {
        this.id = id;
        this.price = price;
        this.theme = theme;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.hall = hall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public ExhibitionHall getHall() {
        return hall;
    }

    public void setHall(ExhibitionHall hall) {
        this.hall = hall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exposition that = (Exposition) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(theme, that.theme) &&
                Objects.equals(shortDescription, that.shortDescription) &&
                Objects.equals(fullDescription, that.fullDescription) &&
                Objects.equals(hall, that.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, theme, shortDescription, fullDescription, hall);
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", price=" + price +
                ", theme='" + theme + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", hall=" + hall +
                '}';
    }

}
