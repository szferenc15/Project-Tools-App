package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import hu.application.sportmates.R;
import hu.application.sportmates.model.User;

public class ProfileActivity extends AppCompatActivity {

    private User requestedUser;
    private TextView    nameTextView, userNameTextView, emailTextView, birthdateTextView,
                        cityTextView, genderTextView, phoneTextView;
    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent user_data = getIntent();
        loggedInUser = (User)user_data.getParcelableExtra("data_of_user");
        Toast.makeText(ProfileActivity.this, loggedInUser.getUsername(), Toast.LENGTH_LONG).show();

        // Find views by id
        nameTextView = findViewById(R.id.tvName);
        userNameTextView = findViewById(R.id.tvUsername);
        emailTextView = findViewById(R.id.tvEmail);
        birthdateTextView = findViewById(R.id.tvBirthdate);
        cityTextView = findViewById(R.id.tvCity);
        genderTextView = findViewById(R.id.tvGender);
        phoneTextView = findViewById(R.id.tvPhoneNumber);

        //new GetRequestBasedOnUser().execute("http://10.0.3.2:5000/user/all");

        nameTextView.setText(loggedInUser.getFirstName() + loggedInUser.getLastName());
        userNameTextView.setText(loggedInUser.getUsername());
        emailTextView.setText(loggedInUser.getEmail());
        birthdateTextView.setText(loggedInUser.getBirthDate());
        cityTextView.setText(loggedInUser.getCity());
        genderTextView.setText(loggedInUser.isMale() ? "Férfi" : "Nő");
        phoneTextView.setText(loggedInUser.getPhoneNumber());
    }
/*
    /// BACKGROUND THREADS
    public class GetRequestBasedOnUser extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection conn = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urls[0]);
                conn = (HttpURLConnection)url.openConnection();
                conn.connect();

                InputStream stream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line;
                while(  (line = reader.readLine()) != null ) {
                    buffer.append(line);
                }

                String JSONResponse = buffer.toString();
                //Log.e("RESPONSE",JSONResponse);

                JSONObject root = new JSONObject(JSONResponse);
                JSONArray data = root.getJSONArray("data");
                JSONObject jsonUser;
                for(int i = 0; i < data.length(); i++) {
                    jsonUser = data.getJSONObject(i);
                    if(jsonUser.getString("email").equals("feri@gmail.com")) {
                        requestedUser = new User(
                                jsonUser.getString("firstName"),
                                jsonUser.getString("lastName"),
                                jsonUser.getString("username"),
                                jsonUser.getString("email"),
                                jsonUser.getString("phoneNumber"),
                                jsonUser.getString("city"),
                                jsonUser.getString("birthDate"),
                                jsonUser.getBoolean("male"));
                        break;
                    }
                }

                return JSONResponse;

            }
            catch (JSONException ex) { ex.printStackTrace();}
            catch (IOException ex) { ex.printStackTrace(); }
            finally {
                if(conn != null) {
                    conn.disconnect();
                }
                try {
                    if(reader != null) { reader.close(); }
                }
                catch (IOException ex) { ex.printStackTrace();}
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Log.e("RESPONSE: ", requestedUser.toString());
            nameTextView.setText(getResources().getString(R.string.profile_name) + ": " + requestedUser.getLastName() + " " + requestedUser.getFirstName());
            userNameTextView.setText(getResources().getString(R.string.profile_username) + ": "+ requestedUser.getUsername());
            emailTextView.setText(getResources().getString(R.string.profile_email) + ": "+ requestedUser.getEmail());
            birthdateTextView.setText(getResources().getString(R.string.profile_birthdate) + ": "+ requestedUser.getBirthDate());
            cityTextView.setText(getResources().getString(R.string.profile_city) + ": "+ requestedUser.getCity());
            genderTextView.setText(getResources().getString(R.string.profile_gender) + ": "+ (requestedUser.isMale() ? "Férfi" : "Nő"));
            phoneTextView.setText(getResources().getString(R.string.profile_phone_number) + ": " + requestedUser.getPhoneNumber());
        }
    }
*/

}
