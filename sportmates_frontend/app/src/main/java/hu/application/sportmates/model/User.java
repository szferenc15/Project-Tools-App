package hu.application.sportmates.model;
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String city;
    private String birthDate;
    private boolean isMale;

    public User() {
    }

    // TODO: Builder pattern
    public User(int id, String firstName, String lastName, String username, String password, String email, String phoneNumber, String city, String birthDate, boolean isMale) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.birthDate = birthDate;
        this.isMale = isMale;
    }

    public int getId() {
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
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", isMale=" + isMale +
                '}';
    }
}
