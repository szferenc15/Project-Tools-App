package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

/**
 * NewEventActivity: A megjelenítésért az activity_new_event.xml felel.
 * Új esemény hozzáadása az adatbázishoz (ez az esemény automatikusan hozzáadódik a bejelentkezett
 * felhasználó eseményeihez, mert ő a szervező, tehát részt vesz rajta.)
 */
public class NewEventActivity extends AppCompatActivity {

    private EditText
            edtEventName, edtEventLocation, edtEventCountry, edtEventCity, edtEventPrice,
            edtEventDay, edtEventStartDate, edtEventEndDate,
            edtEventHeadCount, edtEventDescription;
    private Spinner spEventCategory, spGenderCategory;
    private User loggedInUser;
    private String newEventId;
    private ArrayList<String> items;

    private final ArrayList<String> genderCategories = new ArrayList<>();



    /**
     * Fogadjuk a MainActivity-től kapott Intent-et, így tudni fogjuk ki szeretne új eseményt hozzáadni
     * az adatbázishoz. Létrehozzuk az új esemény hozzáadásához szükséges űrlapot.
     * Az activity_new_event.xml-ben található egy Spinner komponens, amely lehetővé teszi, hogy
     * a felhasználó egy legördülő listából választhasson az adatbázisban megtalálható sportkategóriák közül.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        Intent user_data = getIntent();
        loggedInUser = user_data.getParcelableExtra("data_of_user");

        initViews();
        items = new ArrayList<>();
        genderCategories.add("Férfi");
        genderCategories.add("Nő");
        genderCategories.add("Vegyes");


        spinnerOnClickListener(spGenderCategory, genderCategories);
        new GetAllCategoriesTask().execute("http://10.0.3.2:5000/sport_category/all");

    }

    /**
     * activity_new_event.xml-ben található elemek (szövegbeviteli mezők, spinner) hozzákapcsolása
     * az őket vezérlő kódhoz
     */
    private void initViews() {
        edtEventName = findViewById(R.id.edtEventName);
        edtEventLocation = findViewById(R.id.edtLocale);
        edtEventCountry = findViewById(R.id.edtCountry);
        edtEventCity = findViewById(R.id.edtCity);
        edtEventPrice = findViewById(R.id.edtPrice);
        edtEventDay = findViewById(R.id.edtEventDay);
        edtEventStartDate = findViewById(R.id.edtStartDate);
        edtEventEndDate = findViewById(R.id.edtEndDate);
        edtEventHeadCount = findViewById(R.id.edtHeadCount);
        edtEventDescription = findViewById(R.id.edtDescription);
        spEventCategory = findViewById(R.id.spEventCategories);
        spGenderCategory = findViewById(R.id.spGenderCategories);
    }

    private void spinnerOnClickListener(Spinner spinner, ArrayList<String> items) {
        ArrayAdapter<String> spinnerItems = new ArrayAdapter<>(NewEventActivity.this, R.layout.spinner_item, items);
        spinner.setAdapter(spinnerItems);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //selectedCategory = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * JSON objektum létrehozása az űrlapban megadott adatok alapján, amelyet továbbításra kerül
     * a szervernek. Ha a szerver 200-as responseCode-al tér vissza, akkor sikeres volt az új esemény feldolgozása
     * így az megjelenik a bejelentkezett felhasználó eseményei között.
     * @param view
     * View paraméter ahhoz szükséges, mert itt az activity_new_event.xml-ben létrehozott
     * gomb (imageview - kép az űrlap alján) az onClick attribútum használatával hajtja végre
     * ezt az eljárást.
     */
    public void createNewEvent(View view) {

        JSONObject postData = new JSONObject();
        try {
            postData.put("organizer", loggedInUser.getUsername());
            //postData.put("category", selectedCategory);
            postData.put("category", items.get(spEventCategory.getSelectedItemPosition()));
            postData.put("name", edtEventName.getText().toString());
            postData.put("country", edtEventCountry.getText().toString());
            postData.put("city", edtEventCity.getText().toString());
            postData.put("locale", edtEventLocation.getText().toString());
            postData.put("price", Integer.parseInt(edtEventPrice.getText().toString()));
            postData.put("dateOfEvent", edtEventDay.getText().toString());
            postData.put("start", edtEventStartDate.getText().toString());
            postData.put("finish", edtEventEndDate.getText().toString());
            postData.put("headcount", Integer.parseInt(edtEventHeadCount.getText().toString()));
            postData.put("audience", genderCategories.get(spGenderCategory.getSelectedItemPosition()));
            postData.put("description", edtEventDescription.getText().toString());

            String result = new CreateNewEventTask().execute("http://10.0.3.2:5000/event/add", postData.toString()).get();
            //Log.e("NEW EVENT RESPONSE", result);

            if (result.equals("200")) {
                signUpToTheNewEvent();
                Toast.makeText(NewEventActivity.this, "Az esemény sikeresen létrejött!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(NewEventActivity.this, "Hiba az adatok kitöltésében!", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * A létrejött új eseményhez hozzárendeli a szervezőt.
     * @throws Exception
     * Az eljárás eldobja a kivételt, amelyet a createNewEvent catch ága fog lekezelni.
     */
    private void signUpToTheNewEvent() throws Exception {
        JSONObject postData = new JSONObject();
        try {
            newEventId = new GetNewEventIDTask().execute("http://10.0.3.2:5000/event/all").get();
            postData.put("eventId", newEventId);
            postData.put("userId", loggedInUser.getId());
            String result = new AddEventToUser().execute("http://10.0.3.2:5000/event_user/signup", "POST", postData.toString()).get();
            if (result.equals("200")) {
                loggedInUser.getEventIDs().add(Integer.parseInt(newEventId));
                Intent newEventAdded = new Intent(NewEventActivity.this, MainActivity.class);
                newEventAdded.putExtra("data_of_user", loggedInUser);
                startActivity(newEventAdded);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * A Background Thread-en lekérdezésre kerül az összes sport kategória.
     * Azok megjelenítése a spinnerben.
     */
    public class GetAllCategoriesTask extends AsyncTask<String, String, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            HttpURLConnection conn = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
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

                for (int i = 0; i < data.length(); i++) {
                    jsonEvent = data.getJSONObject(i);
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
            //ArrayAdapter<String> spinnerItems = new ArrayAdapter<>(NewEventActivity.this, R.layout.spinner_item, result);
            spinnerOnClickListener(spEventCategory, result);
        }
    }

    /**
     * Utoljára az adatbázishoz adott esemény azonosítójának lekérdezése.
     */
    public class GetNewEventIDTask extends AsyncTask<String, String, String> {

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

    /**
     * Új esemény létrehozása ha a megadott adatok megfelelőek.
     */
    public class CreateNewEventTask extends AsyncTask<String, String, String> {
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

    /**
     * A felhasználó hozzárendelése az újonnan létrehozott eseményhez (mint szervező.)
     */
    public class AddEventToUser extends AsyncTask<String, String, String> {
        int responseCode = 0;

        @Override
        protected String doInBackground(String... params) {
            try {

                String url = params[0];
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
                con.setRequestMethod(params[1]);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = params[2];

                //Log.e("URL", url);
                //Log.e("URL PARAMETERS", urlParameters);

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
                        new InputStreamReader(con.getInputStream())
                );
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
