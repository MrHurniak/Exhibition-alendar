package ua.training.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class ExhibitionHall implements Serializable {
    private int id;
    private String name;
    private String information;

    public ExhibitionHall(){
    }

    public ExhibitionHall(int id, String name, String information){
        this.id = id;
        this.name = name;
        this.information = information;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public static class Builder{
        private int id;
        private String name;
        private String information;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setInformation(String information) {
            this.information = information;
            return this;
        }

        public ExhibitionHall build(){
            return new ExhibitionHall(id, name, information);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExhibitionHall hall = (ExhibitionHall) o;
        return id == hall.id &&
                Objects.equals(name, hall.name) &&
                Objects.equals(information, hall.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, information);
    }

    @Override
    public String toString() {
        return "ExhibitionHall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                '}';
    }

}
