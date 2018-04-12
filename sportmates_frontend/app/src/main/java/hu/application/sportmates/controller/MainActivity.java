package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle; // bal felső sarokban található ikon a menü lenyitásához
    private Toolbar toolbar;


    // Események megjelenítéshez
    private ArrayList<Event> events;
    private ListView eventsListView;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent user_data = getIntent();
        User loggedInUser = (User)user_data.getParcelableExtra("data_of_user");
        Toast.makeText(MainActivity.this, loggedInUser.getUsername(), Toast.LENGTH_LONG).show();

        navigationView = findViewById(R.id.navigation_view_menu);
        drawerLayout = findViewById(R.id.navigationSideBar);
        toolbar = findViewById(R.id.toolbar_overlay);

        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_account:
                        intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_my_events:
                        intent = new Intent(MainActivity.this, EventActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_logout:
                        /// TODO: Vissza a bejelentkező képernyőre
                        break;
                }
                return false;
            }
        });

        View headerView = navigationView.getHeaderView(0);
        TextView nav_user = headerView.findViewById(R.id.nav_header);

        /// TODO: Ha kész a login, akkor ezen a ponton már tudni fogom ki lépett be
        /// TODO: Így átírhatom ezt a dummy textet a felhasználó nevére
        nav_user.setText("Üdvözöllek Herr Ferke!");

        events = new ArrayList<>();

        new GetEventsBasedOnUser().execute("http://10.0.3.2:5000/event/all");

        eventsListView = findViewById(R.id.eventsListView);

        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String selectedItem = ((TextView)(((LinearLayout)view).getChildAt(0))).getText().toString();

                //Log.e("mainactivity",selectedItem);
                //Toast.makeText(MainActivity.this, selectedItem, Toast.LENGTH_LONG).show();
                Event clickedEvent = events.get(position);

                // Toast.makeText(MainActivity.this, clickedEvent.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, clickedEvent.toString(), Toast.LENGTH_LONG).show();

                // Intent event_by_id = new Intent();


            }
        });

        eventAdapter = new EventAdapter(this, events);
        eventsListView.setAdapter(eventAdapter);
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class GetEventsBasedOnUser extends AsyncTask<String,String,String> {

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
                    Event tmp = new Event(
                            jsonEvent.getInt("id"),
                            jsonEvent.getString("audience"),
                            jsonEvent.getString("dateOfEvent"),
                            jsonEvent.getString("description"),
                            jsonEvent.getString("finish"),
                            jsonEvent.getInt("headcount"),
                            jsonEvent.getString("locale"),
                            jsonEvent.getString("name"),
                            jsonEvent.getInt("price"),
                            jsonEvent.getString("start"),
                            jsonEvent.getString("organizer")

                    );
                    Log.e( String.valueOf((i+1)) + ". event", tmp.getName());
                    events.add(tmp);
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
}
