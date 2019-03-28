package ua.expo.model.entity;

public class Exposition {
    private int id;
    private int price;
    private int ticketsCount;
    private String theme;
    private String shortDescription;
    private String fullDescription;

    private ExhibitionHall hall;

    public Exposition() {
    }

    public Exposition(int id, int price, int ticketsCount,
                      String theme, String shortDescription,
                      String fullDescription, ExhibitionHall hall) {
        this.id = id;
        this.price = price;
        this.ticketsCount = ticketsCount;
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

    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
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

    //todo hashcode and equals
}
