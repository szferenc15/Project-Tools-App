package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import hu.application.sportmates.model.User;

public class NewEventActivity extends AppCompatActivity {

    private EditText
            edtEventName, edtEventLocation, edtEventCountry, edtEventCity, edtEventPrice, edtEventStartDate, edtEventEndDate,
            edtEventHeadCount, edtEventAudience, edtEventDescription;
    private Spinner spEventCategory;
    private String selectedCategory;

    private User loggedInUser;
    private static String newEventId;
    private ArrayList<String> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        Intent user_data = getIntent();
        loggedInUser = user_data.getParcelableExtra("data_of_user");

        initViews();
        items = new ArrayList<>();
        new GetAllCategories().execute("http://10.0.3.2:5000/sport_category/all");

    }

    private void initViews() {
        edtEventName = findViewById(R.id.edtEventName);
        edtEventLocation = findViewById(R.id.edtLocale);
        edtEventCountry = findViewById(R.id.edtCountry);
        edtEventCity = findViewById(R.id.edtCity);
        edtEventPrice = findViewById(R.id.edtPrice);
        edtEventStartDate = findViewById(R.id.edtStartDate);
        edtEventEndDate = findViewById(R.id.edtEndDate);
        edtEventHeadCount = findViewById(R.id.edtHeadCount);
        edtEventAudience = findViewById(R.id.edtAudience);
        edtEventDescription = findViewById(R.id.edtDescription);
        spEventCategory = findViewById(R.id.spCategory);
    }

    public void createNewEvent(View view) {

        JSONObject postData = new JSONObject();
        try {
            postData.put("organizer", loggedInUser.getUsername());
            postData.put("category", selectedCategory);
            postData.put("name", edtEventName.getText().toString());
            postData.put("country", edtEventCountry.getText().toString());
            postData.put("city", edtEventCity.getText().toString());
            postData.put("locale", edtEventLocation.getText().toString());
            postData.put("price", Integer.parseInt(edtEventPrice.getText().toString()));
            postData.put("dateOfEvent", "2018-12-31");
            postData.put("start", "10:00:00");
            postData.put("finish", "11:00:00");
            postData.put("headcount", Integer.parseInt(edtEventHeadCount.getText().toString()));
            postData.put("audience", edtEventAudience.getText().toString());
            postData.put("description", edtEventDescription.getText().toString());

            String result = new SuccessfulNewEvent().execute("http://10.0.3.2:5000/event/add", postData.toString()).get();
            Log.e("NEW EVENT RESPONSE", result);

            if (result.equals("200")) {
                signUpToTheNewEvent();
            } else {
                Toast.makeText(NewEventActivity.this, "Hibás esemény felvitel: " + result, Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void signUpToTheNewEvent() throws Exception {
        newEventId = new GetNewEventID().execute("http://10.0.3.2:5000/event/all").get();
        Log.e("NEWEVENTID ", newEventId);
        JSONObject postData = new JSONObject();
        JSONObject postDataComment = new JSONObject();
        try {
            postData.put("eventId", newEventId);
            postData.put("userId", loggedInUser.getId());

            postDataComment.put("message", "message");
            postDataComment.put("eventId", newEventId);
            postDataComment.put("userId", loggedInUser.getId());

            String result = new SuccessfulNewEvent().execute("http://10.0.3.2:5000/event_user/signup", postData.toString()).get();
            Log.e("NEW EVENT SIGNUP", result);

            result = new SuccessfulNewEvent().execute("http://10.0.3.2:5000/comment/add", postDataComment.toString()).get();
            Log.e("NEW COMMENT", result);

            Intent newEventAdded = new Intent(NewEventActivity.this, MainActivity.class);
            newEventAdded.putExtra("data_of_user", loggedInUser);
            startActivity(newEventAdded);
            //Toast.makeText(NewEventActivity.this, "S: " + result, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class GetAllCategories extends AsyncTask<String, String, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... urls) {
            HttpURLConnection conn = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urls[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(false);
                conn.connect();

                InputStream stream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String JSONResponse = buffer.toString();

                JSONObject root = new JSONObject(JSONResponse);
                JSONArray data = root.getJSONArray("data");
                JSONObject jsonEvent;

                //events.clear();
                for (int i = 0; i < data.length(); i++) {
                    jsonEvent = data.getJSONObject(i);
                    //Log.e("EVENT: ",jsonEvent.toString());
                    Log.e("ITEM", jsonEvent.getString("category"));
                    items.add(jsonEvent.getString("category"));
                }
                return items;
            } catch (JSONException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            //items = result;
            Log.e("on post execute size:", String.valueOf(result.size()));
            for (String s : result) {
                Log.e("ON POST EXECUTE: ", s);
            }
            ArrayAdapter<String> result2 = new ArrayAdapter<>(NewEventActivity.this, R.layout.spinner_item, result);
            //Log.e("Result  ", result.toString());
            spEventCategory.setAdapter(result2);
            spEventCategory.setSelection(0);
            spEventCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedCategory = adapterView.getItemAtPosition(i).toString();
                    //Log.e("KATT", "Katt");
                    //Log.e("SELECTED ITEM = ",adapterView.getItemAtPosition(i).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //Log.e("SELECTED ITEM = ","semmi");
                }
            });
        }
    }

    public class GetNewEventID extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection conn = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urls[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream stream = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String JSONResponse = buffer.toString();

                JSONObject root = new JSONObject(JSONResponse);
                JSONArray data = root.getJSONArray("data");
                JSONObject jsonEvent;
                jsonEvent = data.getJSONObject(data.length() - 1);
                return jsonEvent.getString("id").toString();
            } catch (JSONException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Log.e("onpostban",s);
        }
    }

    public class SuccessfulNewEvent extends AsyncTask<String, String, String> {
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

    public class NewEventConnect extends AsyncTask<String, String, String> {

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
