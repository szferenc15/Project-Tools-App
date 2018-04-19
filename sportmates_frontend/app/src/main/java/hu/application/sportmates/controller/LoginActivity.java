package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import hu.application.sportmates.R;
import hu.application.sportmates.model.User;


public class LoginActivity extends AppCompatActivity {

    User requestedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button btLogin = (Button) findViewById(R.id.login);
        final EditText etName = (EditText) findViewById(R.id.name);
        etName.requestFocus();
        final EditText etPw = (EditText) findViewById(R.id.password);
        final Button btReg = (Button) findViewById(R.id.reg);

        btReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent regist = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(regist);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("identifier",etName.getText().toString());
                    postData.put("password",etPw.getText().toString());

                    //LoginConnect send = new LoginConnect();

                    //send.doInBackground("http://10.0.3.2:5000/user/login", postData.toString());
                    String result = new LoginConnect().execute("http://10.0.3.2:5000/user/login", postData.toString()).get();

                    if(result.equals("200")){
                        //Log.e("user: ",requestedUser.toString());
                        Intent loginSuccess = new Intent(LoginActivity.this, MainActivity.class);
                        loginSuccess.putExtra("data_of_user", requestedUser);
                        startActivity(loginSuccess);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Hibás bejelentkezés: " + result, Toast.LENGTH_SHORT).show();
                    }
                    //Log.e("Vege","Vege a loginactivitynek");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //TextView tx = (TextView)findViewById(R.id.textView2);
        //TextView tx2 = (TextView)findViewById(R.id.textView);

        //Typeface custom_font = Typeface.createFromAsset(getAssets(), "assets/fonts/ToxTypewriter.ttf");

        //tx.setTypeface(custom_font);
        //tx2.setTypeface(custom_font);

    }


    public class LoginConnect extends AsyncTask<String,String,String> {

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
