package hu.application.sportmates.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Az alkalmazásban szereplő felhasználók reprezentálására szolgáló osztály.
 * Megvalósítja a Parcelable interfacet.
 * Erre azért volt szükség, mert az Activity-k között többször átküldésre kerül a bejelentkezett
 * felhasználó, hogy a rá vonatkozó adatokat tudjuk lekérdezni az adatbázisból a szükséges helyen.
 */
public class User implements Parcelable{

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String city;
    private String birthDate;
    private boolean isMale;
    private ArrayList<Integer> eventIDs = new ArrayList<>();

    public User() {}

    public User(int id, String firstName, String lastName, String username, String email,
                String phoneNumber, String city, String birthDate, boolean isMale, ArrayList<Integer> eventIDs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.birthDate = birthDate;
        this.isMale = isMale;
        this.eventIDs = eventIDs;
    }

    private User(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        username = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        city = in.readString();
        birthDate = in.readString();
        isMale = in.readByte() == 1;
        eventIDs = in.readArrayList(Integer.class.getClassLoader());
    }
    public int getId(){return  id;}

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

    public ArrayList<Integer> getEventIDs() {
        return eventIDs;
    }

    @Override
    public String toString() {
        return "User: " +
                ", id='" + id + '\'' +
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(phoneNumber);
        parcel.writeString(city);
        parcel.writeString(birthDate);
        parcel.writeByte( (byte) (isMale ? 1 : 0) );
        parcel.writeList(eventIDs);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>(){
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
