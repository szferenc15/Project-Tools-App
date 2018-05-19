package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import hu.application.sportmates.R;

/**
 * RegistrationActivity: A megjelenítésért az activity_registration.xml felel.
 * Ha a felhasználó még nem regisztrált, akkor a regisztráció gomb megnyomása után
 * ezt megteheti.
 * Itt egy űrlapot kell kitölteni, az adatok megfelelő megadásával a felhasználó
 * bekerül az adatbázisba, így visszatérve a főoldalra be tud jelentkezni.
 */
public class RegistrationActivity extends AppCompatActivity {

    private EditText edtLastName, edtFirstName, edtUserName, edtPassword, edtEmail, edtBirthDate,
            edtCity, edtPhone;

    private Button btnRegistration;
    private RadioButton rbMale, rbFemale;
    private boolean isMale;


    /**
     * setContentView által beállítjuk a megjelenítést az activity_registration.xml-re
     * Létrehozzuk és összekapcsoljuk a kódban és az xml állományban található elemeket.
     * Eseménykezelőt rendelünk a regisztráció gombhoz.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        btnRegisterOnClickListener();
        radioButtonClickListener();
    }

    private void radioButtonClickListener() {
        rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbMale.isChecked()) {
                    isMale = true;
                    Toast.makeText(getApplicationContext(), "Male", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbFemale.isChecked()) {
                    isMale = false;
                    Toast.makeText(getApplicationContext(), "Female", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * activity_registration.xml-en található elemek (gomb, beviteli mezők) hozzákapcsolása
     * Button és EditText példányokhoz. Így lehetséges összekapcsolni a megjelenítést és a vezérlést.
     * Lehetőségünk lesz az adott gombhoz/beviteli mezőhöz java kódot kapcsolni.
     */
    private void initViews() {
        edtLastName = findViewById(R.id.edtLastName);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtBirthDate = findViewById(R.id.edtBirthDate);
        edtCity = findViewById(R.id.edtCity);
        edtPhone = findViewById(R.id.edtPhoneNumber);
        btnRegistration = findViewById(R.id.btnRegistration);
        rbMale = findViewById(R.id.radioButton_male);
        rbFemale = findViewById(R.id.radioButton_female);
    }

    /**
     * Eseménykezelő a regisztráció gombhoz. A megadott adatok alapján létrejön egy JSON objektum
     * és ha az megfelelő, akkor a szerver létrehozza az új felhasználót, különben jelezzük,
     * hogy a megadott adatok nem megfelelőek.
     * Az űrlap kitöltését segíti a beviteli mezőkben megjelenített, elvárt formátumra utaló szöveg.
     */
    public void btnRegisterOnClickListener() {
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("firstName", edtFirstName.getText().toString());
                    postData.put("lastName", edtLastName.getText().toString());
                    postData.put("pictureUrl",isMale ? "../../../../resources/pictures/user_man_1.png" :
                            "../../../../resources/pictures/user_woman_1.png" );
                    postData.put("username", edtUserName.getText().toString());
                    postData.put("password", edtPassword.getText().toString());
                    postData.put("email", edtEmail.getText().toString());
                    postData.put("phoneNumber", edtPhone.getText().toString());
                    postData.put("city", edtCity.getText().toString());
                    postData.put("birthDate", edtBirthDate.getText().toString());
                    postData.put("isMale",isMale);


                    String result = new RegisterNewUser().execute("http://10.0.3.2:5000/user/register", postData.toString()).get();

                    if(result.equals("200")){
                        Toast.makeText(RegistrationActivity.this, "Sikeres regisztráció! Üdvözöllek az alkalmazásban!", Toast.LENGTH_SHORT).show();
                        Intent registrationIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(registrationIntent);
                    }
                    else{
                        Toast.makeText(RegistrationActivity.this, "Hibás regisztráció! Próbálja meg újra", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }




    /**
     * A megadott adatok alapján létrejön egy JSON objektum amely elküldésre kerül a szervernek.
     * A szerver visszatér egy responseCode-al a kérés eredményéről.
     */
    public class RegisterNewUser extends AsyncTask<String,String,String> {

        int responseCode = 0;
        @Override
        protected String doInBackground(String... params) {
            try {

                String url = params[0];
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = params[1];

                // Send post request

                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
                writer.write(urlParameters);
                writer.close();
                wr.close();
                /*wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();*/

                responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return String.valueOf(responseCode);
        }
    }
}

