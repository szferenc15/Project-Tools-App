package hu.application.sportmates.controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Find views by id
        nameTextView = findViewById(R.id.tvName);
        userNameTextView = findViewById(R.id.tvUsername);
        emailTextView = findViewById(R.id.tvEmail);
        birthdateTextView = findViewById(R.id.tvBirthdate);
        cityTextView = findViewById(R.id.tvCity);
        genderTextView = findViewById(R.id.tvGender);
        phoneTextView = findViewById(R.id.tvPhoneNumber);

        new GetRequestBasedOnUser().execute("http://10.0.3.2:5000/user/all");
    }

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
                                jsonUser.getInt("id"),
                                jsonUser.getString("firstName"),
                                jsonUser.getString("lastName"),
                                jsonUser.getString("username"),
                                jsonUser.getString("password"),
                                jsonUser.getString("email"),
                                jsonUser.getString("phoneNumber"),
                                jsonUser.getString("city"),
                                jsonUser.getString("birthDate"),
                                jsonUser.getBoolean("male"));
                        break;
                    }
                }
                // Log.e("RESPONSE: ", requestedUser.toString());

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
            nameTextView.setText(getResources().getString(R.string.profile_name) + ": " + requestedUser.getLastName() + " " + requestedUser.getFirstName());
            userNameTextView.setText(getResources().getString(R.string.profile_username) + ": "+ requestedUser.getUsername());
            emailTextView.setText(getResources().getString(R.string.profile_email) + ": "+ requestedUser.getEmail());
            birthdateTextView.setText(getResources().getString(R.string.profile_birthdate) + ": "+ requestedUser.getBirthDate());
            cityTextView.setText(getResources().getString(R.string.profile_city) + ": "+ requestedUser.getCity());
            genderTextView.setText(getResources().getString(R.string.profile_gender) + ": "+ (requestedUser.isMale() ? "Férfi" : "Nő"));
            phoneTextView.setText(getResources().getString(R.string.profile_phone_number) + ": " + requestedUser.getPhoneNumber());
        }
    }


}
