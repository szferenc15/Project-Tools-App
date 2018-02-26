package app.sport_mates.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SportCategory {
    // START OF DEFAULT COLUMN(S)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    @Column(columnDefinition = "VARCHAR2(30) DEFAULT NULL", unique = true)
    private String category;

    // END OF DEFAULT COLUMN(S)

    // GETTER(S)/SETTER(S)

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}