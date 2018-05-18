package app.sportmates_backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Ez az osztály implementálja a kommentet.
 * @author szendrei
 * @author polozgai
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment implements Serializable {

    private static final long serialVersionUID = 7563227654242012109L;

    @GenericGenerator(
        name = "commentSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "COMMENT_SEQUENCE"),
                @Parameter(name = "initial_value", value = "100"),
                @Parameter(name = "increment_size", value = "0")
        }
    )

    // START OF DEFAULT COLUMN(S)

    @Id
    @GeneratedValue(generator = "commentSequenceGenerator")
    private long id;
    
    @Column(columnDefinition = "VARCHAR2(500) DEFAULT NULL")
    private String message;

    // END OF DEFAULT COLUMN(S)

    // START OF RELATION DEFINITON(S)

    @JsonManagedReference(value = "comment-event")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event eventId;

    @JsonManagedReference(value = "comment-user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    // END OF RELATION DEFINITON(S)

    // GETTER(S)/SETTER(S)

    /**
     * Visszaadja a komment azonosítóját.
     * @return Komment azonosítója.
     */
    public long getId() {
        return id;
    }

    /**
     * Visszaadja a komment szövegét.
     * @return Komment szövege.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Visszaadja az esemény azonosítóját.
     * @return Esemény azonosítója.
     */
    public long getEventId() {
        return eventId.getId();
    }

    /**
     * Visszaadja a felhasználó azonosítóját.
     * @return Felhasználó azonosítója.
     */
    public String getUserId() {
        return userId.getFirstName() + " " + userId.getLastName() + " (" +  userId.getUsername() + ")";
    }

    /**
     * Beállítja az esemény azonosítóját.
     * @param eventId Esemény azonosítója.
     */
    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    /**
     * Beállítja a felhasználó azonosítóját.
     * @param userId Felhasználó azonosítója.
     */
    public void setUserId(User userId) {
        this.userId = userId;
    }

    /**
     * Beállítja az üzenetet.
     * @param message Üzenet.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}