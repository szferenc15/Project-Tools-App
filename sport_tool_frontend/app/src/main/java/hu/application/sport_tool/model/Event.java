package hu.application.sport_tool.model;

public class Event {
    private int id;
    private String audience;
    private String dateOfEvent;
    private String description;
    private String finish;
    private int headcount;
    private String locale;
    private String name;
    private int price;
    private String start;
    private String organizer;

    public Event(int id, String audience, String dateOfEvent, String description, String finish, int headcount, String locale, String name, int price, String start, String organizer) {
        this.id = id;
        this.audience = audience;
        this.dateOfEvent = dateOfEvent;
        this.description = description;
        this.finish = finish;
        this.headcount = headcount;
        this.locale = locale;
        this.name = name;
        this.price = price;
        this.start = start;
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
        return headcount;
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
}
