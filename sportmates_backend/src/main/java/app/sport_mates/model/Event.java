package app.sportmates_backend.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
import javax.persistence.CascadeType;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import app.sportmates_backend.class_interface.UserInfo;
import app.sportmates_backend.model.SportCategory;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "organizer", referencedColumnName = "username")
    private User organizer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "category", referencedColumnName = "category")
    private SportCategory category;
    
    @JsonBackReference(value = "comment-event")
    @OneToMany(
        mappedBy = "eventId",
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "event_user",
        joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    // END OF RELATION DEFINITON(S)

    // GETTER(S)/SETTER(S)

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getLocale() {
        return locale;
    }

    public short getPrice() {
        return price;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public Time getStart() {
        return start;
    }

    public Time getFinish() {
        return finish;
    }

    public short getHeadcount() {
        return headcount;
    }

    public String getAudience() {
        return audience;
    }

    public String getDescription() {
        return description;
    }

    public String getOrganizer() {
        return organizer.getUsername();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Set<UserInfo> getUsers() {
        Set<UserInfo> userInfos = new HashSet<>();
        users.forEach(user -> userInfos.add(new UserInfo(user)));
        return userInfos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setPrice(short price) {
        this.price = price;
    }
    
    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public void setFinish(Time finish) {
        this.finish = finish;
    }

    public void setHeadcount(short headcount) {
        this.headcount = headcount;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public void setCategory(SportCategory category) {
        this.category = category;
    }
}