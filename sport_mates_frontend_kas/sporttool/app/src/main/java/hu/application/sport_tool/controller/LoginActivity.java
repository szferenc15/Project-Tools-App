package com.example.kaszon.sporttool;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button btLogin = (Button) findViewById(R.id.login);
        final EditText etName = (EditText) findViewById(R.id.name);
        final EditText etPw = (EditText) findViewById(R.id.password);
        final Button btReg = (Button) findViewById(R.id.reg);



        btLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                JSONObject postData = new JSONObject();
                try {
                    postData.put("identifier",etName.getText().toString());
                    postData.put("password",etPw.getText().toString());

                    new LoginConnect().execute("http://10.0.3.2:5000/user/login", postData.toString());

                } catch (JSONException e) {
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


    public class LoginConnect extends AsyncTask<String,String,String>{
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

                int responseCode = con.getResponseCode();
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

            return "";
        }
    }

}
