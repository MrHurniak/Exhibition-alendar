package ua.expo.model.entity;

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


    @Override
    public String toString() {
        return "ExhibitionHall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                '}';
    }

}
