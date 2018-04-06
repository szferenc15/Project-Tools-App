package app.sportmates_backend.class_interface;

public class NewComment {
    private String message;
    private Long eventId;
    private Long userId;

    public String getMessage() {
        return message;
    }

    public Long getEventId() {
        return eventId;
    }

    public Long getUserId() {
        return userId;
    }
}