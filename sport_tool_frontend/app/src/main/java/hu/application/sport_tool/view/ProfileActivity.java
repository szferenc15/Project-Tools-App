package hu.application.sport_tool.view;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import hu.application.sport_tool.R;
import hu.application.sport_tool.model.User;

public class ProfileActivity extends AppCompatActivity {

    private User requestedUser;
    private TextView    nameTextView, userNameTextView, birthdateTextView,
                        cityTextView, genderTextView, phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Find views by id
        nameTextView = findViewById(R.id.tvName);
        userNameTextView = findViewById(R.id.tvUsername);
        birthdateTextView = findViewById(R.id.tvBirthdate);
        cityTextView = findViewById(R.id.tvCity);
        genderTextView = findViewById(R.id.tvGender);
        phoneTextView = findViewById(R.id.tvPhoneNumber);

        new GetRequestBasedOnUser().execute("http://10.0.2.2:5000/user/all");
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
            nameTextView.setText("FERIKE: " + requestedUser.getLastName() + " " + requestedUser.getFirstName());
            userNameTextView.setText("Username: " + requestedUser.getUsername());
            birthdateTextView.setText("Birthdate: " + requestedUser.getBirthDate());
            cityTextView.setText("City: " + requestedUser.getCity());
            genderTextView.setText("Gender: " + (requestedUser.isMale() ? "Male" : "Female"));
            phoneTextView.setText("Phone number: " + requestedUser.getPhoneNumber());
        }
    }


}
