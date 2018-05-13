package hu.application.sportmates.model;

/**
 * Az alkalmazásban szereplő események reprezentálására szolgáló osztály.
 */
public class Event {
    private int id;
    private String name;
    private String country;
    private String city;
    private String locale;
    private int price;
    private String dateOfEvent;
    private String start;
    private String finish;
    private int headCount;
    private String audience;
    private String description;
    private String organizer;

    public Event(int id, String name, String country, String city, String locale, int price, String dateOfEvent, String start, String finish, int headCount, String audience, String description, String organizer) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.locale = locale;
        this.price = price;
        this.dateOfEvent = dateOfEvent;
        this.start = start;
        this.finish = finish;
        this.headCount = headCount;
        this.audience = audience;
        this.description = description;
        this.organizer = organizer;
    }

    public int getId() {
        return id;
    }

    public String getAudience() {
        return audience;
    }

    public String getDateOfEvent() {
        return dateOfEvent;
    }

    public String getDescription() {
        return description;
    }

    public String getFinish() {
        return finish;
    }

    public int getHeadCount() {
        return headCount;
    }

    public String getLocale() {
        return locale;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getStart() {
        return start;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Event: " + name;
    }
}
