package hu.application.sportmates.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import hu.application.sportmates.R;
import hu.application.sportmates.model.User;

/**
 * ProfileActivity: A megjelenítésért az activity_profile.xml felel.
 * A felhasználó adatai és profilképe jelenik meg ebben az activity-ben.
 */
public class ProfileActivity extends AppCompatActivity {

    private TextView    nameTextView, userNameTextView, emailTextView, birthdateTextView,
                        localeTextView, phoneTextView;
    private ImageView profilePicture;
    private User loggedInUser;

    /**
     * MainActivity-től kapott Intent fogadása, ez alapján a felhasználó adatainak
     * megjelenítése az adatlapon.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent user_data = getIntent();
        loggedInUser = user_data.getParcelableExtra("data_of_user");
        //Toast.makeText(ProfileActivity.this, loggedInUser.getUsername(), Toast.LENGTH_LONG).show();

        initViews();
        setValuesForViews();

    }

    /**
     * activity_profile.xml-ben található elemek (szövegmezők) hozzákapcsolása
     * TextView példányokhoz. Így lehetséges összekapcsolni a megjelenítést és a vezérlést.
     * Lehetőségünk lesz az adott szövegmezőhöz java kódot kapcsolni.
     */
    private void initViews() {
        nameTextView = findViewById(R.id.tvName);
        userNameTextView = findViewById(R.id.tvUsername);
        emailTextView = findViewById(R.id.tvEmail);
        birthdateTextView = findViewById(R.id.tvBirthDate);
        localeTextView = findViewById(R.id.tvLocale);
        phoneTextView = findViewById(R.id.tvPhoneNumber);
    }

    /**
     * A felhasználó adatainak hozzárendelése a szövegmezőkhöz.
     */
    private void setValuesForViews() {
        nameTextView.setText(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        userNameTextView.setText( getString(R.string.profile_username) + ": " + loggedInUser.getUsername());
        emailTextView.setText(getString(R.string.profile_email) + ": " + loggedInUser.getEmail());
        birthdateTextView.setText(getString(R.string.profile_birthdate) + ": " + loggedInUser.getBirthDate());
        localeTextView.setText(getString(R.string.profile_locale) + ": " + loggedInUser.getCity());
        phoneTextView.setText(getString(R.string.profile_phone_number) + ": " + loggedInUser.getPhoneNumber());
        profilePicture = findViewById(R.id.imgProfilePicture);
        profilePicture.setBackgroundResource( loggedInUser.isMale() ? R.drawable.user_man_1 : R.drawable.user_woman_1 );
    }

}
