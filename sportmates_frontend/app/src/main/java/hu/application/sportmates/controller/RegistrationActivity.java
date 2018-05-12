package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import hu.application.sportmates.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText edtLastName, edtFirstName, edtUserName, edtPassword, edtEmail, edtBirthDate,
            edtCity, edtPhone;

    private Button btnRegistration;

    private boolean isMale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        btnRegisterOnClickListener();
    }

    private void initViews() {
        edtLastName = findViewById(R.id.reg_lastname);
        edtFirstName = findViewById(R.id.reg_firstname);
        edtUserName = findViewById(R.id.reg_username);
        edtPassword = findViewById(R.id.reg_password);
        edtEmail = findViewById(R.id.reg_email);
        edtBirthDate = findViewById(R.id.reg_birthdate);
        edtCity = findViewById(R.id.reg_city);
        edtPhone = findViewById(R.id.reg_phonenumber);
        btnRegistration = findViewById(R.id.btnRegistration);
    }

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
                        Intent registSuccess = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(registSuccess);
                    }
                    else{
                        Toast.makeText(RegistrationActivity.this, "Hibás regisztráció: " + result, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public class RegisterNewUser extends AsyncTask<String,String,String> {

        int responseCode = 0;
        @Override
        protected String doInBackground(String... strings) {
            try {

                String url = strings[0];
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
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

