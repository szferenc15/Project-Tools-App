package app.sport_mates.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    // START OF DEFAULT COLUMN(S)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    @Column(columnDefinition = "VARCHAR2(500) DEFAULT NULL")
    private String message;

    // END OF DEFAULT COLUMN(S)

    // START OF RELATION DEFINITON(S)

    @JsonBackReference()
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event eventId;

    @JsonBackReference()
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    // END OF RELATION DEFINITON(S)

    // GETTER(S)/SETTER(S)

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Event getEventId() {
        return eventId;
    }

    public User getUserId() {
        return userId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}