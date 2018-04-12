package hu.application.sportmates.model;
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String city;
    private String birthDate;
    private boolean isMale;

    public User() {

    }

    // TODO: Builder pattern
    public User(String firstName, String lastName, String username, String email, String phoneNumber, String city, String birthDate, boolean isMale) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.birthDate = birthDate;
        this.isMale = isMale;
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

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isMale() {
        return isMale;
    }

    @Override
    public String toString() {
        return "User: " +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", isMale=" + isMale +
                '}';
    }
}
