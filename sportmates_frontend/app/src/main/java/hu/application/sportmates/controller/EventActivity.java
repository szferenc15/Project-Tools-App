package hu.application.sportmates.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hu.application.sportmates.R;
import hu.application.sportmates.model.User;

public class EventActivity extends AppCompatActivity {


    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent user_data = getIntent();
        loggedInUser = user_data.getParcelableExtra("data_of_user");



    }


}
