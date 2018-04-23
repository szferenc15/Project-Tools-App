package hu.application.sportmates.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import hu.application.sportmates.R;
import hu.application.sportmates.model.User;

public class ProfileActivity extends AppCompatActivity {

    private TextView    nameTextView, userNameTextView, emailTextView, birthdateTextView,
                        localeTextView, phoneTextView;
    private ImageView profilePicture;
    private User loggedInUser;
    //private String male;
    //private String female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //female = getString(R.string.regist_female);
        //male = getString(R.string.regist_male);

        Intent user_data = getIntent();
        loggedInUser = user_data.getParcelableExtra("data_of_user");
        Toast.makeText(ProfileActivity.this, loggedInUser.getUsername(), Toast.LENGTH_LONG).show();

        nameTextView = findViewById(R.id.tvName);
        userNameTextView = findViewById(R.id.tvUsername);
        emailTextView = findViewById(R.id.tvEmail);
        birthdateTextView = findViewById(R.id.tvBirthDate);
        localeTextView = findViewById(R.id.tvLocale);
        phoneTextView = findViewById(R.id.tvPhoneNumber);


        nameTextView.setText(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        userNameTextView.setText( getString(R.string.profile_username) + ": " + loggedInUser.getUsername());
        emailTextView.setText(getString(R.string.profile_email) + ": " + loggedInUser.getEmail());
        birthdateTextView.setText(getString(R.string.profile_birthdate) + ": " + loggedInUser.getBirthDate());
        localeTextView.setText(getString(R.string.profile_locale) + ": " + loggedInUser.getCity());
        //genderTextView.setText(loggedInUser.isMale() ? male : female );
        phoneTextView.setText(getString(R.string.profile_phone_number) + ": " + loggedInUser.getPhoneNumber());
        profilePicture = findViewById(R.id.imgProfilePicture);
        profilePicture.setBackgroundResource( loggedInUser.isMale() ? R.drawable.user_man_1 : R.drawable.user_woman_1 );

    }

}
