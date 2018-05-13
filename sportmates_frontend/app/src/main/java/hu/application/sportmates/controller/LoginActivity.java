package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import hu.application.sportmates.R;
import hu.application.sportmates.model.User;

/**
 * LoginActivity: A megjelenítésért az activity_login.xml felel.
 * Ezt a képernyőt látja először a felhasználó amikor elindítja az alkalmazást.
 */
public class LoginActivity extends AppCompatActivity {

    private User requestedUser;
    private Button btnLogin, btnRegistration;
    private EditText edtUserName, edtPassword;

    /**
     * setContentView által beállítjuk a megjelenítést az activity_login.xml-re.
     * Létrehozzuk és összekapcsoljuk a kódban és az xml állományban található elemeket.
     * Eseménykezelőket hozzárendelése a gombokhoz.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        btnLoginClickListener();
        btnRegistrationClickListener();
    }

    /**
     * activity_login.xml-en található elemek (gombok, beviteli mezők) hozzákapcsolása
     * Button és EditText példányokhoz. Így lehetséges összekapcsolni a megjelenítést és a vezérlést.
     * Lehetőségünk lesz az adott gombhoz/beviteli mezőhöz java kódot kapcsolni.
     */
    private void initViews() {
        btnLogin = findViewById(R.id.btnLogin);
        edtUserName = findViewById(R.id.edtUserName);
        edtUserName.requestFocus();
        edtPassword = findViewById(R.id.edtPassword);
        btnRegistration = findViewById(R.id.btnRegistration);
    }

    /**
     * Ha a felhasználó a bejelentkezés gombra kattint akkor lefut a klikk esemény.
     * Az eljárás előállít egy JSON objektumot amelyet elküld a szervernek.
     * Amennyiben a szerver válasza 200 (responseCode) akkor a belépés sikeres volt.
     * Ekkor a program átírányítja a felhasználót az alkalmazás főoldalára.
     * A bejelentkezett felhasználó adatait Intent segítségével továbbítjuk,
     * amelyet Activity-k közti adattovábbításra lehet használni.
     * Ha a responseCode nem 200, akkor sikertelen bejelentkezés történt.
     */
    private void btnLoginClickListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("identifier",edtUserName.getText().toString());
                    postData.put("password",edtPassword.getText().toString());

                    //UserLoginTask send = new UserLoginTask();

                    //send.doInBackground("http://10.0.3.2:5000/user/login", postData.toString());
                    String result = new UserLoginTask().execute("http://10.0.3.2:5000/user/login", postData.toString()).get();

                    if(result.equals("200")){
                        //Log.e("user: ",requestedUser.toString());
                        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                        loginIntent.putExtra("data_of_user", requestedUser);
                        startActivity(loginIntent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Hibás bejelentkezés! Próbáld újra!", Toast.LENGTH_SHORT).show();
                    }
                    //Log.e("Vege","Vege a loginactivitynek");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Ha a felhasználó a regisztráció gombra kattint akkor lefut a klikk esemény.
     * Ekkor az alkalmazás átirányítja a felhasználót
     * egy regisztrációs űrlapra, amelyet ha megfelelően kitöltött,
     * akkor a kezdőoldalon adatai megfelelő megadásával be tud lépni az alkalmazásba.
     */
    private void btnRegistrationClickListener() {
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent registrationIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registrationIntent);
            }
        });
    }

    /**
     * UI Thread-től elkülönülten vizsgáljuk (a Background Thread-en)
     * hogy van-e a megadott név és jelszó párnak megfelelő felhasználó az adatbázisban
     * A megadott adatok alapján létrejön egy JSON objektum amely elküldésre kerül a szervernek.
     * A szerver visszatér egy responseCode-al a kérés eredményéről.
     */
    public class UserLoginTask extends AsyncTask<String,String,String> {

        int responseCode = 0;
        @Override
        protected String doInBackground(String... params) {
            try {

                String url = params[0];
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = params[1];

                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                //System.out.println(response.toString());
                if(responseCode == 200) {

                    JSONObject root = new JSONObject(response.toString());
                    JSONObject jsonUser = root.getJSONObject("data");
                    JSONObject jsonEvents = jsonUser.getJSONObject("eventData");

                    // Log.e("LoginActivity", jsonEvents.toString());

                    ArrayList<Integer> eventIDs = new ArrayList<>();

                    for(int i = 0; i < jsonEvents.length(); i++) {
                        //Log.e("JSON EVENT ID ", jsonEvents.names().get(i).toString());
                        eventIDs.add(Integer.parseInt(jsonEvents.names().get(i).toString()));
                        //Log.e("EVENT ID ", eventIDs.get(i).toString());
                    }

                    requestedUser = new User(
                            jsonUser.getInt("id"),
                            jsonUser.getString("firstName"),
                            jsonUser.getString("lastName"),
                            jsonUser.getString("username"),
                            jsonUser.getString("email"),
                            jsonUser.getString("phoneNumber"),
                            jsonUser.getString("city"),
                            jsonUser.getString("birthDate"),
                            jsonUser.getBoolean("male"),
                            eventIDs);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

            return String.valueOf(responseCode);
        }
    }
}
