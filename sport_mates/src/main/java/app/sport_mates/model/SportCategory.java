package app.sport_mates.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SportCategory {

    @GenericGenerator(
        name = "sportCategorySequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SPORT_CATEGORY_SEQUENCE"),
                @Parameter(name = "initial_value", value = "100"),
                @Parameter(name = "increment_size", value = "1")
        }
    )

    // START OF DEFAULT COLUMN(S)

    @Id
    @GeneratedValue(generator = "sportCategorySequenceGenerator")
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