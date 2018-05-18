package app.sportmates_backend.class_interface;
/**
 * Ez az osztály az új kommentet reprezentálja.
 * @author szendrei
 * @author polozgai
 *
 */
public class NewComment {
    private String message;
    private long eventId;
    private long userId;
    /**
     * Visszadja az üzenetet.
     * @return Komment
     */
    public String getMessage() {
        return message;
    }
    /**
     * Visszaadja az esemény azonosítóját.
     * @return Esemény azonosítója.
     */
    public long getEventId() {
        return eventId;
    }
    /**
     * Visszaadja a felhasználó azonosítóját.
     * @return Felhasználó azonosítója.
     */
    public long getUserId() {
        return userId;
    }
}