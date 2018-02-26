package app.sport_mates.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

import com.fasterxml.jackson.annotation.JsonBackReference;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EventSportCategory {
    // START OF DEFAULT COLUMN(S)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    // END OF DEFAULT COLUMN(S)

    // START OF RELATION DEFINITON(S)

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "category", referencedColumnName = "category")
    private SportCategory category;

    @JsonBackReference()
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event eventId;

    // END OF RELATION DEFINITON(S)

    // GETTER(S)/SETTER(S)

    public long getId() {
        return id;
    }

    public Event getEventId() {
        return eventId;
    }

    public SportCategory getCategory() {
        return category;
    }
}