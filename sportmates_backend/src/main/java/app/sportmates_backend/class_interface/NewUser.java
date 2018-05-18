package app.sportmates_backend.class_interface;

import java.sql.Date;
/**
 * Ez az osztály egy új felhasználót reprezentál.
 * @author szendrei
 * @author polozgai
 *
 */
public class NewUser {
    private String firstName;
    private String lastName;
    private String pictureUrl;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String city;
    private Date birthDate;
    private boolean isMale;

    /**
     * Visszadja az új felhasználó keresztnevét.
     * @return Új felhasználó keresztneve.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Visszadja az új felhasználó vezetéknevét.
     * @return Új felhasználó vezetékneve.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Visszadja az új felhasználó képének URL-ét.
     * @return Új felhasználó képének URL-je.
     */
    public String getPictureUrl() {
        return pictureUrl;
    }
    
    /**
     * Visszadja az új felhasználó felhazsnálónevét.
     * @return Új felhasználó felhazsnálóneve.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Visszadja az új felhasználó jelszavát.
     * @return Új felhasználó jalszava.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Visszadja az új felhasználó email-ét.
     * @return Új felhasználó email-ja.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Visszadja az új felhasználó telefonszámát.
     * @return Új felhasználó telefonszáma.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * Visszadja az új felhasználó városát.
     * @return Új felhasználó városa.
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Visszadja az új felhasználó születésnapját.
     * @return Új felhasználó születésnapja.
     */
    public Date getBirthDate() {
        return birthDate;
    }
    
    /**
     * Visszadja az új felhasználó nemét.
     * @return Új felhasználó neme.
     */
    public boolean isMale() {
        return isMale;
    }
}