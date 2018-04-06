package app.sportmates_backend.class_interface;

import java.sql.Date;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPictureUrl() {
        return pictureUrl;
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

    public String getCity() {
        return city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public boolean isMale() {
        return isMale;
    }
}