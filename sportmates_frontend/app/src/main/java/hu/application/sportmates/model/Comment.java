package hu.application.sportmates.model;

/**
 * Felhasználó által küldött hozzászólás reprezentálására szolgáló osztály.
 */

public class Comment {
    private int id; // comment id
    private String message;
    private int eventId;
    private String userId;

    public Comment(String message, int eventId, String userId) {
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
