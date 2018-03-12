package app.sport_mates.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @GenericGenerator(
        name = "userSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "USER_SEQUENCE"),
                @Parameter(name = "initial_value", value = "100"),
                @Parameter(name = "increment_size", value = "1")
        }
    )

    // START OF DEFAULT COLUMN(S)

    @Id
    @GeneratedValue(generator = "userSequenceGenerator")
    private long id;

    @Pattern(regexp="^[a-zA-Z]{2,15}$")
    @Column(columnDefinition = "VARCHAR2(15) NOT NULL")
    private String firstName;

    @Pattern(regexp="^[a-zA-Z]{2,15}$")
    @Column(columnDefinition = "VARCHAR2(15) NOT NULL")
    private String lastName;

    @Pattern(regexp="^[a-z0-9_-]{3,15}$")
    @Column(columnDefinition = "VARCHAR2(15)", unique = true)
    private String username;

    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,25}$")
    @Column(columnDefinition = "VARCHAR2(25) NOT NULL")
    private String password;

    @Pattern(regexp="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$")
    @Column(columnDefinition = "VARCHAR2(50)", unique = true)
    private String email;

    @Pattern(regexp="^\\+?[0-9]{7,14}$")
    @Column(columnDefinition = "VARCHAR2(14) NOT NULL")
    private String phoneNumber;
    
    @Column(columnDefinition = "VARCHAR2(30) NOT NULL")
    private String city;

    @NotNull
    private Date birthDate;
    
    @Column(columnDefinition = "BOOLEAN NOT NULL")
    private boolean isMale;

    // END OF DEFAULT COLUMN(S)

    // START OF RELATION DEFINITON(S)

    @JsonManagedReference()
    @OneToMany(
        mappedBy = "userId",
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    @JsonManagedReference()
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_event",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events = new HashSet<>();

    // END OF RELATION DEFINITON(S)

    // GETTER(S)/SETTER(S)

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getCity() {
        return city;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }
}