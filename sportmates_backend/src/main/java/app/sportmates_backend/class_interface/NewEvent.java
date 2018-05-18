package app.sportmates_backend.class_interface;

import java.sql.Date;
import java.sql.Time;
/**
 * Ez az osztály egy új eseményt reprezentál.
 * @author szendrei
 * @author polozgai
 *
 */
public class NewEvent {
    private String name;
    private String country;
    private String city;
    private String locale;
    private short price;
    private Date dateOfEvent;
    private Time start;
    private Time finish;
    private short headcount;
    private String audience;
    private String description;
    private String organizer;
    private String category;
    
    /**
     * Visszadja az esemény
     * @return Esemény
     */
    public String getName() {
        return name;
    }
    
    /**
     * Visszadja az esemény országát.
     * @return Esemény országa.
     */    
    public String getCountry() {
        return country;
    }
    
    /**
     * Visszadja az esemény városát.
     * @return Esemény városa.
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Visszadja az esemény helyszínét.
     * @return Esemény helyszíne.
     */
    public String getLocale() {
        return locale;
    }
    
    /**
     * Visszadja az esemény árát.
     * @return Esemény ára.
     */
    public short getPrice() {
        return price;
    }
    
    /**
     * Visszadja az esemény dátumát.
     * @return Esemény dátuma.
     */
    public Date getDateOfEvent() {
        return dateOfEvent;
    }
    
    /**
     * Visszadja az esemény kezdetét.
     * @return Esemény kezdete.
     */
    public Time getStart() {
        return start;
    }
    
    /**
     * Visszadja az esemény végét.
     * @return Esemény vége.
     */
    public Time getFinish() {
        return finish;
    }
    
    /**
     * Visszadja az eseményben résztvevők létszámát.
     * @return Eseményben résztvevők létszáma.
     */
    public short getHeadcount() {
        return headcount;
    }
    
    /**
     * Visszadja az esemény célközönségét.
     * @return Esemény célközönsége.
     */
    public String getAudience() {
        return audience;
    }
    
    /**
     * Visszadja az esemény leírását.
     * @return Esemény leírása.
     */    
    public String getDescription() {
        return description;
    }
    
    /**
     * Visszadja az esemény szervezőjét.
     * @return Esemény szervezője.
     */
    public String getOrganizer() {
        return organizer;
    }
    
    /**
     * Visszadja az esemény kategóriáját.
     * @return Esemény kategóriája.
     */
    public String getCategory() {
        return category;
    }
}