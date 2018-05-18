package app.sportmates_backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Ez az osztály implementálja a sport kategóriát.
 * @author szendrei
 * @author polozgai
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SportCategory implements Serializable {

    private static final long serialVersionUID = 7563205295221742267L;

    @GenericGenerator(
        name = "sportCategorySequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SPORT_CATEGORY_SEQUENCE"),
                @Parameter(name = "initial_value", value = "100"),
                @Parameter(name = "increment_size", value = "0")
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

    /**
     * Visszaadja a sport kategória azonosítóját.
     * @return Sport kategória azonosítója.
     */
    public long getId() {
        return id;
    }

    /**
     * Visszaadja a sport kategória nevét.
     * @return Sport kategória.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Beállítja a sport kategória nevét.
     * @param category Sport kategória neve. 
     */
    public void setCategory(String category) {
        this.category = category;
    }
}