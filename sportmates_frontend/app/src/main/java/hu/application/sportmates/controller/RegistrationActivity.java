package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import hu.application.sportmates.R;
import hu.application.sportmates.model.User;

public class RegistrationActivity extends AppCompatActivity {

    private TextView lastNameTextView, firstNameTextView, userNameTextView, passwordTextView, emailTextView, birthdateTextView,
            cityTextView, phoneTextView;
    private String lastName,firstName,userName,password,email,birthdate,city,phone;
    private boolean isMale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        lastNameTextView = findViewById(R.id.reg_lastname);
        firstNameTextView = findViewById(R.id.reg_firstname);
        userNameTextView = findViewById(R.id.reg_username);
        passwordTextView = findViewById(R.id.reg_password);
        emailTextView = findViewById(R.id.reg_email);
        birthdateTextView = findViewById(R.id.reg_birthdate);
        cityTextView = findViewById(R.id.reg_city);
        phoneTextView = findViewById(R.id.reg_phonenumber);
    }

    public void onClick(View view) {
        lastName = lastNameTextView.getText().toString();
        firstName = firstNameTextView.getText().toString();
        userName = userNameTextView.getText().toString();
        password = passwordTextView.getText().toString();
        email = emailTextView.getText().toString();
        birthdate = birthdateTextView.getText().toString();
        city = cityTextView.getText().toString();
        phone = phoneTextView.getText().toString();

        JSONObject postData = new JSONObject();
        try {
            postData.put("firstName",firstNameTextView.getText().toString());
            postData.put("lastName",lastNameTextView.getText().toString());
            postData.put("pictureUrl",isMale ? "../../../../resources/pictures/user_man_1.png" :
                    "../../../../resources/pictures/user_woman_1.png" );
            postData.put("username",userNameTextView.getText().toString());
            postData.put("password",passwordTextView.getText().toString());
            postData.put("email",emailTextView.getText().toString());
            postData.put("phoneNumber",phoneTextView.getText().toString());
            postData.put("city",cityTextView.getText().toString());
            postData.put("birthDate",birthdateTextView.getText().toString());
            postData.put("isMale",isMale);

            //LoginConnect send = new LoginConnect();

            //send.doInBackground("http://10.0.3.2:5000/user/login", postData.toString());
            String result = new RegistrationConnect().execute("http://10.0.3.2:5000/user/register", postData.toString()).get();
            //Log.e("REGIST", result);

            if(result.equals("200")){
                //Log.e("user: ",requestedUser.toString());
                Intent registSuccess = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(registSuccess);
            }
            else{
                Toast.makeText(RegistrationActivity.this, "Hibás regisztráció: " + result, Toast.LENGTH_SHORT).show();
            }
            //Log.e("Vege","Vege a loginactivitynek");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class RegistrationConnect extends AsyncTask<String,String,String> {

        int responseCode = 0;
        @Override
        protected String doInBackground(String... strings) {
            try {

                String url = strings[0];
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add reuqest header
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = strings[1];

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
                System.out.println(response.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return String.valueOf(responseCode);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        if  (!checked){
            ((RadioButton) view).setChecked(checked);
        }
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_male:
                if (checked)
                    isMale = true;
                    break;
            case R.id.radioButton_female:
                if (checked)
                    isMale = false;
                    break;
        }
    }
}

