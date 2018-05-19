package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import hu.application.sportmates.R;
import hu.application.sportmates.model.Event;
import hu.application.sportmates.model.EventAdapter;
import hu.application.sportmates.model.User;

/**
 * UserEventsActivity: A megjelenítésért az activity_event.xml felel.
 * A felhasználó saját eseményei jelennek meg egy listában
 */
public class UserEventsActivity extends AppCompatActivity {

    private User loggedInUser;
    private ArrayList<Event> myEvents;
    private ListView eventsListView;
    private EventAdapter eventAdapter;

    /**
     * Fogadjuk a MainActivity-től elküldött Intent-et, így tudjuk, mely felhasználó eseményeit kell
     * megjelenítenünk a listában.
     * A továbbított felhasználó eseményazonosítói alapján lekérjük az eseményeket és megjelenítjük
     * őket a listában.
     * A lista elemeihez eseménykezelőket hozunk létre, így egy eseményre történő kattintáskor
     * az adott esemény részleteit lehet megtekinteni.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        myEvents = new ArrayList<>();
        eventsListView = findViewById(R.id.eventsListView);

        Intent user_data = getIntent();
        loggedInUser = user_data.getParcelableExtra("data_of_user");

        for(int i = 0; i < loggedInUser.getEventIDs().size(); i++) {
            new GetEventsBasedOnID().execute("http://10.0.3.2:5000/event/by_id?id=" + loggedInUser.getEventIDs().get(i));
        }

        listViewClickListener();

        eventAdapter = new EventAdapter(this, myEvents);
        eventsListView.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
    }

    /**
     * Eseménykezelő a lista elemeihez
     */
    private void listViewClickListener() {

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event clickedEvent = myEvents.get(position);
                Intent eventDetailsIntent = new Intent(UserEventsActivity.this, EventDetailsActivity.class);
                eventDetailsIntent.putExtra("Event ID", clickedEvent.getId());
                eventDetailsIntent.putExtra("data_of_user", loggedInUser);
                eventDetailsIntent.putExtra("Previous Activity", "User Events Activity");
                startActivity(eventDetailsIntent);
            }
        });
    }

    /**
     * Visszalépéskor az UserEventsActivity egy Intent-be csomagolva elküldi a
     * bejelentkezett felhasználó adatait a MainActivity-nek.
     * Ez alapján tud frissülni a főoldalon a közelben lévő események listája.
     * Az intent küldésével egyidőben visszatér a főoldalra.
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent;
            intent = new Intent(UserEventsActivity.this, MainActivity.class);
            intent.putExtra("data_of_user", loggedInUser);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Események lekérdezése a bejelentkezett felhasználó eseményazonosítói alapján
     */
    public class GetEventsBasedOnID extends AsyncTask<String,String,String> {

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

                //Log.e("EventDetails", JSONResponse);

                JSONObject root = new JSONObject(JSONResponse);
                JSONObject jsonEvent = root.getJSONObject("data");

                // Annyi eseményen vesz részt a felhasználó, ahány event id jön le hozzá kapcsolva
                // az adatbázisból
                Event tmp = new Event(  jsonEvent.getInt("id"),
                                        jsonEvent.getString("name"),
                                        jsonEvent.getString("country"),
                                        jsonEvent.getString("city"),
                                        jsonEvent.getString("locale"),
                                        jsonEvent.getString("category"),
                                        jsonEvent.getInt("price"),
                                        jsonEvent.getString("dateOfEvent"),
                                        jsonEvent.getString("start"),
                                        jsonEvent.getString("finish"),
                                        jsonEvent.getInt("headcount"),
                                        jsonEvent.getString("audience"),
                                        jsonEvent.getString("description"),
                                        jsonEvent.getString("organizer"));
                myEvents.add(tmp);


                return JSONResponse;

            }
            catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
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


}
