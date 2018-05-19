package app.sportmates_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Ez az osztály implementálja a felhasználót.
 * @author szendrei
 * @author polozgai
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"comments", "events", "hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    @GenericGenerator(
        name = "userSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "USER_SEQUENCE"),
                @Parameter(name = "initial_value", value = "100"),
                @Parameter(name = "increment_size", value = "0")
        }
    )

    // START OF DEFAULT COLUMN(S)

    @Id
    @GeneratedValue(generator = "userSequenceGenerator")
    private long id;

    //@Pattern(regexp="^[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ ]{2,15}$")
	@Pattern(regexp="^[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ ]*")
    @Column(columnDefinition = "VARCHAR2(15) NOT NULL")
    private String firstName;

    //@Pattern(regexp="^[a-zA-Z ]{2,15}$")
	//@Pattern(regexp="^[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ ]{2,15}$")
	@Pattern(regexp="^[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ ]*")
    @Column(columnDefinition = "VARCHAR2(15) NOT NULL")
    private String lastName;

    @Column(columnDefinition = "VARCHAR2(200) NOT NULL")
    private String pictureUrl;

    @Pattern(regexp="^[a-z0-9_-]{3,15}$")
    @Column(columnDefinition = "VARCHAR2(15)", unique = true)
    private String username;

    @JsonIgnore
    //@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,25}$")
    @Column(columnDefinition = "VARCHAR2(60) NOT NULL")
    private String password;

    @Pattern(regexp="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$")
    @Column(columnDefinition = "VARCHAR2(50)", unique = true)
    private String email;

    @Pattern(regexp="^\\+?[0-9]{7,14}$")
    @Column(columnDefinition = "VARCHAR2(14) NOT NULL")
    private String phoneNumber;    
	
	@Pattern(regexp="^[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ ]*")
    @Column(columnDefinition = "VARCHAR2(30) NOT NULL")
    private String city;

    @NotNull
    private Date birthDate;
    
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean isMale;

    // END OF DEFAULT COLUMN(S)

    // START OF RELATION DEFINITON(S)

    @JsonBackReference(value = "comment-user")
    @OneToMany(
        mappedBy = "userId",
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "users")
    private Set<Event> events = new HashSet<>();

    // END OF RELATION DEFINITON(S)

    // GETTER(S)/SETTER(S)

    /**
     * Visszaadja a felhasználó azonosítóját.
     * @return Felhasználó azonosítója.
     */
    public long getId() {
        return id;
    }

    /**
     * Visszaadja a felhasználó keresztnevét.
     * @return Felhasználó keresztneve.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Visszaadja a felhasználó vezetéknevét.
     * @return Felhasználó vezetékneve.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Visszaadja a felhasználó fotójának URL-ét.
     * @return Felhasználó fotó URL-je.
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * Visszaadja a felhasználó felhasználónevét.
     * @return Felhasználó felhasználóneve.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Visszaadja a felhasználó jelszavát.
     * @return Felhasználó jelszava.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Visszaadja a felhasználó e-mail-ét.
     * @return Felhasználó e-mail-je.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Visszaadja a felhasználó telefonszámát.
     * @return Felhasználó telefonszáma.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Visszaadja a felhasználó születésnapját.
     * @return Felhasználó születésnapja
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Visszaadja a felhasználó városát.
     * @return Felhasználó városa.
     */
    public String getCity() {
        return city;
    }

    /**
     * Visszaadja a felhasználó nemét.
     * @return Felhasználó neme.
     */
    public boolean isMale() {
        return isMale;
    }

    /**
     * Visszaadja a felhasználó kommentjeit.
     * @return Felhasználó kommentjei.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Visszaadja a felhasználó eseményének azonosítóját, és nevét.
     * @return Felhasználó esemény azonosítója, neve.
     */
    public Map<Long, String> getEventInfos() {
        Map<Long, String> eventData = new HashMap<>();

        events.forEach(event -> eventData.put(event.getId(), event.getName()));
        return eventData;
    }

    /**
     * Visszaadja a felhasználó eseményeit.
     * @return Felhasználó eseményei.
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Beállítja a felhasználó keresztnevét.
     * @param firstName Felhasználó keresztneve.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Beállítja a felhasználó vezetéknevét.
     * @param lastName Felhasználó vezetékneve.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Beállítja a felhasználó képének URL-ét.
     * @param pictureUrl Felhasználó képének URL-je.
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * Beállítja a felhasználó felhasználónevét.
     * @param username Felhasználó felhasználóneve.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Beállítja a felhasználó jelszavát.
     * @param password Felhasználó jelszava.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Beállítja a felhasználó e-mail-ét.
     * @param email Felhasználó e-mail-e. 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Beállítja a felhasználó telefonszámát.
     * @param phoneNumber Felhasználó telefonszáma.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Beállítja a felhasználó születésnapját.
     * @param birthDate Felhasználó születésnapja.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Beállítja a felhasználó városát.
     * @param city Felhasználó városa.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Beállítja a felhasználó nemét.
     * @param isMale Felhasználó neme.
     */
    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }
}