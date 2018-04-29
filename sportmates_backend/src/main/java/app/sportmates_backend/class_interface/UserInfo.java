package app.sportmates_backend.class_interface;

import java.sql.Date;
import java.util.Map;

import app.sportmates_backend.model.User;

public class UserInfo {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String city;
    private Date birthDate;
    private boolean isMale;
    private Map<Long, String> eventData;

    public UserInfo(User user) {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public boolean isMale() {
        return isMale;
    }

    public Map<Long, String> getEventData() {
        return eventData;
    }
}