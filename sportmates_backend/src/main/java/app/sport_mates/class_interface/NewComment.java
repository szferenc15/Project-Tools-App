package app.sportmates_backend.class_interface;

public class NewComment {
    private String message;
    private long eventId;
    private long userId;

    public String getMessage() {
        return message;
    }

    public long getEventId() {
        return eventId;
    }

    public long getUserId() {
        return userId;
    }
}