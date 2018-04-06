package app.sportmates_backend.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import app.sportmates_backend.model.User;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event eventId;

    @JsonManagedReference(value = "comment-user")
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

    public long getEventId() {
        return eventId.getId();
    }

    public String getUserId() {
        return userId.getFirstName() + " " + userId.getLastName() + " (" +  userId.getUsername() + ")";
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