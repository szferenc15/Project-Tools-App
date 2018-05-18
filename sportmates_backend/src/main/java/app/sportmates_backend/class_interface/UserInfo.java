package app.sportmates_backend.class_interface;

import java.sql.Date;
import java.util.Map;

import app.sportmates_backend.model.User;
/**
 * Ez az osztály a felhasználó adatait reprezentálja.
 * @author szendrei
 * @author polozgai
 *
 */
public class UserInfo {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String city;
    private Date birthDate;
    private boolean isMale;
    private Map<Long, String> eventData;

    /**
     * Konstruktor.
     * @param user {@link User} objektum.
     */
    public UserInfo(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.city = user.getCity();
        this.birthDate = user.getBirthDate();
        this.isMale = user.isMale();
        this.eventData = user.getEventInfos();
    }
    
    /**
     * Visszadja a felhasználó azonosítóját.
     * @return felhasználó azonosítója.
     */
    public long getId()
    {
        return id;
    }
    
    /**
     * Visszadja a felhasználó keresztnevét.
     * @return felhasználó keresztneve.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Visszadja a felhasználó vezetéknevét.
     * @return felhasználó vezetékneve.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Visszadja a felhasználó felhasználónevét.
     * @return felhasználó felhasználóneve.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Visszadja a felhasználó email-ét.
     * @return felhasználó email-je.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Visszadja a felhasználó telefonszáma.
     * @return felhasználó telefonszáma.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * Visszadja a felhasználó városát.
     * @return felhasználó városa.
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Visszadja a felhasználó születésnapját.
     * @return felhasználó születésnapja.
     */
    public Date getBirthDate() {
        return birthDate;
    }
    
    /**
     * Visszadja a felhasználó nemét.
     * @return felhasználó neme.
     */
    public boolean isMale() {
        return isMale;
    }
    
    /**
     * Visszadja a felhasználó esemény adatait.
     * @return felhasználó esemény adata.
     */
    public Map<Long, String> getEventData() {
        return eventData;
    }
}