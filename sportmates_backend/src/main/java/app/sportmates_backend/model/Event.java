package app.sportmates_backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import app.sportmates_backend.class_interface.UserInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Ez az osztály implementálja az eseményt.
 * @author szendrei
 * @author polozgai
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event implements Serializable {

    private static final long serialVersionUID = 7526905295627426147L;

    @GenericGenerator(
        name = "eventSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "EVENT_SEQUENCE"),
                @Parameter(name = "initial_value", value = "100"),
                @Parameter(name = "increment_size", value = "0")
        }
    )

    // START OF DEFAULT COLUMN(S)

    @Id
    @GeneratedValue(generator = "eventSequenceGenerator")
    private long id;

    @Column(columnDefinition = "VARCHAR2(50) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR2(50) NOT NULL")
    private String country;

    @Column(columnDefinition = "VARCHAR2(50) NOT NULL")
    private String city;

    @Column(columnDefinition = "VARCHAR2(100) NOT NULL")
    private String locale;

    @Min(0)
    private short price;

    @Future()
    private Date dateOfEvent;

    @NotNull()
    private Time start;

    @NotNull()
    private Time finish;

    @Min(2)
    private short headcount;

    @Column(columnDefinition = "VARCHAR2(6) NOT NULL")
    private String audience;

    @Column(columnDefinition = "VARCHAR2(500) DEFAULT NULL")
    private String description;

    // END OF DEFAULT COLUMN(S)

    // START OF RELATION DEFINITON(S)

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "organizer", referencedColumnName = "username")
    private User organizer;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "category", referencedColumnName = "category")
    private SportCategory category;
    
    @JsonBackReference(value = "comment-event")
    @OneToMany(
        mappedBy = "eventId",
        cascade = CascadeType.REMOVE, 
        orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "event_user",
        joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    // END OF RELATION DEFINITON(S)

    // GETTER(S)/SETTER(S)

    /**
     * Visszadja az esemény azonosítóját.
     * @return Esemény azonosítója.
     */
    public long getId() {
        return id;
    }

    /**
     * Visszaadja az esemény nevét.
     * @return Esemény neve.
     */
    public String getName() {
        return name;
    }

    /**
     * Visszaadja az esemény országát.
     * @return Esemény országa.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Visszaadja az esemény városát.
     * @return Esemény városa.
     */
    public String getCity() {
        return city;
    }

    /**
     * Visszaadja az esemény helyszínét.
     * @return Esemény helyszíne.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Visszaadja az esemény költségét.
     * @return Esemény költsége.
     */
    public short getPrice() {
        return price;
    }

    /**
     * Visszaadja az esemény dátumát.
     * @return Esemény dátuma.
     */
    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    /**
     * Visszaadja az esemény kezdetét.
     * @return Esemény kezdete.
     */
    public Time getStart() {
        return start;
    }

    /**
     * Visszaadja az esemény végét.
     * @return Esemény vége.
     */
    public Time getFinish() {
        return finish;
    }

    /**
     * Visszaadja az eseményben résztvevők számát.
     * @return Eseményben résztevevők száma.
     */
    public short getHeadcount() {
        return headcount;
    }

    /**
     * Visszaadja az esemény közönségét.
     * @return Esemény közönsége.
     */
    public String getAudience() {
        return audience;
    }

    /**
     * Visszaadja az esemény leírását.
     * @return Esemény leírása.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Visszaadja az esemény szervezőjét.
     * @return Esemény szervezője.
     */
    public String getOrganizer() {
        return organizer.getUsername();
    }

    /**
     * Visszaadja az esemény kategóriáját.
     * @return Esemény kateróriája.
     */
    public String getCategory() {
        return category.getCategory();
    }

    /**
     * Visszaadja az esemény kommentjeit.
     * @return Esemény kommentjei.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Visszaadja az eseményhez tartozó felhasználók adatait.
     * @return Eseményhez tartozó felhasználók adatai. 
     */
    public Set<UserInfo> getUserInfos() {
        Set<UserInfo> userInfos = new HashSet<>();
        users.forEach(user -> userInfos.add(new UserInfo(user)));
        return userInfos;
    }

    /**
     * Visszaadja az eseményhez tartozó felhasználókat. 
     * @return Eseményhez tartozó felhasználók. 
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Beállítja az esemény nevét.
     * @param name Esemény neve.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Beállítja az esemény országát.
     * @param country Esemény országa.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Beállítja az esemény városát. 
     * @param city Esemény városa.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Beállítja az esemény helyszínét.
     * @param locale Esemény helyszíne.
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Beállítja az esemény költségét. 
     * @param price Esemény költsége.
     */
    public void setPrice(short price) {
        this.price = price;
    }

    /**
     * Beállítja az esemény dátumát. 
     * @param dateOfEvent Esemény dátuma.
     */   
    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    /**
     * Beállítja az esemény kezdetét.
     * @param start Esemény kezdete. 
     */
    public void setStart(Time start) {
        this.start = start;
    }

    /**
     * Beállítja az esemény végét. 
     * @param finish Esemény vége.
     */
    public void setFinish(Time finish) {
        this.finish = finish;
    }

    /**
     * Beállítja az esemény létszámát. 
     * @param headcount Esemény létszáma.
     */
    public void setHeadcount(short headcount) {
        this.headcount = headcount;
    }

    /**
     * Beállítja az esemény közönségét. 
     * @param audience Esemény közönsége.
     */
    public void setAudience(String audience) {
        this.audience = audience;
    }

    /**
     * Beállítja az esemény leírását. 
     * @param description Esemény leírása.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Beállítja az esemény szervezőjét.
     * @param organizer Esemény szervezője.
     */
    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    /**
     * Beállítja az eseményhez tartozó sportot.  
     * @param category Eseményhez tartozó sport.
     */
    public void setCategory(SportCategory category) {
        this.category = category;
    }
}