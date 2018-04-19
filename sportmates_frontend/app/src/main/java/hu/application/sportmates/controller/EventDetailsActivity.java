package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import hu.application.sportmates.R;
import hu.application.sportmates.model.Event;

public class EventDetailsActivity extends AppCompatActivity {

    private int clickedEventID;

    private TextView
            eventName, eventLocation, eventPrice, eventStarDate, eventEndDate, eventHeadcount, eventAudience;

    private Event clickedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        initViews();
        Intent clickedEventDetails = getIntent();
        clickedEventID = clickedEventDetails.getIntExtra("Event ID", 0);

        String requestParam = "?id=" + clickedEventID;

        new GetEventsBasedOnID().execute("http://10.0.3.2:5000/event/by_id" + requestParam);
    }

    private void initViews() {
        eventName = findViewById(R.id.tvName);
        eventLocation = findViewById(R.id.tvLocale);
        eventPrice = findViewById(R.id.tvPrice);
        eventStarDate = findViewById(R.id.tvStartDate);
        eventEndDate = findViewById(R.id.tvEndDate);
        eventHeadcount = findViewById(R.id.tvHeadCount);
        eventAudience = findViewById(R.id.tvAudience);
    }



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

                JSONObject root = new JSONObject(JSONResponse);
                JSONObject jsonEvent = root.getJSONObject("data");
                clickedEvent = new Event(
                    jsonEvent.getInt("id"),
                    jsonEvent.getString("name"),
                    jsonEvent.getString("country"),
                    jsonEvent.getString("city"),
                    jsonEvent.getString("locale"),
                    jsonEvent.getInt("price"),
                    jsonEvent.getString("dateOfEvent"),
                    jsonEvent.getString("start"),
                    jsonEvent.getString("finish"),
                    jsonEvent.getInt("headcount"),
                    jsonEvent.getString("audience"),
                    jsonEvent.getString("description"),
                    jsonEvent.getString("organizer")
                );

                Log.e("Response by id", JSONResponse);

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


            eventName.setText(clickedEvent.getName());
            eventLocation.setText(clickedEvent.getLocale());
            eventPrice.setText(String.valueOf(clickedEvent.getPrice()));

            String dateOfEvent = clickedEvent.getDateOfEvent() + " " + clickedEvent.getStart();

            eventStarDate.setText(dateOfEvent);
            eventEndDate.setText(clickedEvent.getFinish());


            eventHeadcount.setText(String.valueOf(clickedEvent.getHeadCount()));
            eventAudience.setText(clickedEvent.getAudience());
        }
    }
}
