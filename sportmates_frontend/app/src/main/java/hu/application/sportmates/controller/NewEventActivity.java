package hu.application.sportmates.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import hu.application.sportmates.R;
import hu.application.sportmates.model.Event;
import hu.application.sportmates.model.User;

public class NewEventActivity extends AppCompatActivity {

    private EditText
            eventName, eventLocation, eventCountry, eventCity, eventPrice, eventStartDate, eventEndTime,
            eventHeadcount, eventAudience, eventDescription;
    private Spinner eventCategory;

    private User loggedInUser;
    private ImageView imgArrow;

    private ArrayList<String> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        Intent user_data = getIntent();
        loggedInUser = user_data.getParcelableExtra("data_of_user");

        initViews();
        items = new ArrayList<>();
        new getAllCategorys().execute("http://10.0.3.2:5000/sport_category/all");
        /*if (items.isEmpty()){
            Log.e("Items: ","üres");
        }
*/
        for( String i : items){
            Log.e("Items: ", i );
        }
        ArrayAdapter<String> result = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);
        Log.e("Result  ", result.toString());
        eventCategory.setAdapter(result);
        eventCategory.setSelection(0);
        eventCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("KATT", "Katt");
                Log.e("SELECTED ITEM = ",adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.e("SELECTED ITEM = ","semmi");
            }
        });

    }

    private void initViews() {
        eventName = findViewById(R.id.etEventName);
        eventLocation = findViewById(R.id.etLocale);
        eventCountry = findViewById(R.id.etCounty);
        eventCity = findViewById(R.id.etCity);
        eventPrice = findViewById(R.id.etPrice);
        eventStartDate = findViewById(R.id.etStartDate);
        eventEndTime = findViewById(R.id.etEndTime);
        eventHeadcount = findViewById(R.id.etHeadCount);
        eventAudience = findViewById(R.id.etAudience);
        eventDescription = findViewById(R.id.etDescription);
        eventCategory = findViewById(R.id.spCategory);
    }


    public class getAllCategorys extends AsyncTask<String,String,String> {

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

                JSONObject root = new JSONObject(JSONResponse);
                JSONArray data = root.getJSONArray("data");
                JSONObject jsonEvent;

                //events.clear();
                for(int i = 0; i <data.length(); i++) {
                    jsonEvent = data.getJSONObject(i);
                    Log.e("EVENT: ",jsonEvent.toString());
                    Log.e("ITEM",jsonEvent.getString("category"));
                    items.add(jsonEvent.getString("category"));
                }
                for (String i : items){
                    Log.e("item ",i);
                }
                return JSONResponse;
            }
            catch (JSONException ex) { ex.printStackTrace();}
            catch (IOException ex) {
                ex.printStackTrace();
            } finally {
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
        }
    }


    public void newEventClick(View view) {

        JSONObject postData = new JSONObject();
        try {
            postData.put("organizer",loggedInUser.getUsername());

            postData.put("category", "Futás");

            postData.put("name",eventName.getText().toString());
            postData.put("country",eventCountry.getText().toString());
            postData.put("city",eventCity.getText().toString());
            postData.put("locale",eventLocation.getText().toString());
            postData.put("price", Integer.parseInt(eventPrice.getText().toString()));
            postData.put("dateOfEvent",eventStartDate.getText().toString());
            postData.put("start","10:00:00");
            postData.put("finish",eventEndTime.getText().toString());
            postData.put("headcount",Integer.parseInt(eventHeadcount.getText().toString()));
            postData.put("audience",eventAudience.getText().toString());
            postData.put("description",eventDescription.getText().toString());

            //LoginConnect send = new LoginConnect();

            //send.doInBackground("http://10.0.3.2:5000/user/login", postData.toString());
            String result = new NewEventConnect().execute("http://10.0.3.2:5000/event/add", postData.toString()).get();
            //Log.e("REGIST", result);

            Toast.makeText(NewEventActivity.this,"Új esemény: " + result,Toast.LENGTH_SHORT).show();
            /*
            if(result.equals("200")){
                //Log.e("user: ",requestedUser.toString());
                Intent registSuccess = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(registSuccess);
            }
            else{
                Toast.makeText(RegistrationActivity.this, "Hibás regisztráció: " + result, Toast.LENGTH_SHORT).show();
            }*/
            //Log.e("Vege","Vege a loginactivitynek");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class NewEventConnect extends AsyncTask<String,String,String> {

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
}
