package app.sportmates_backend.class_interface;
/**
 * Ez az osztály egy új szervezőt reprezentál.
 * @author szendrei
 * @author polozgai
 */
public class NewEventUser {
    private long eventId;
    private long userId;
    
    /**
     * Visszadja az esemény azonosítóját.
     * @return Esemény azonosítója.
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * Visszadja az esemény szervezőjének az azonosítóját.
     * @return Esemény szervező azonosítója.
     */
    public long getUserId() {
        return userId;
    }
}