package hu.application.sportmates.model;

public class Comment {
    private int id; // comment id
    private String message;
    private int eventId;
    private String userId;

    public Comment(int id, String message, int eventId, String userId) {
        this.id = id;
        this.message = message;
        this.eventId = eventId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getEventId() {
        return eventId;
    }

    public String getUserId() {
        return userId;
    }
}
