package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import java.util.ArrayList;

import hu.application.sportmates.R;
import hu.application.sportmates.model.Comment;
import hu.application.sportmates.model.Event;
import iammert.com.expandablelib.ExpandCollapseListener;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class EventDetailsActivity extends AppCompatActivity {

    private int clickedEventID;

    private TextView
            eventName, eventLocation, eventPrice, eventStartDate, eventEndDate, eventHeadcount, eventAudience, eventDescription;


    private ImageView imgArrow;

    private Event clickedEvent;
    private ArrayList<Comment> clickedEventComments;

    private ExpandableLayout expandable;
    private boolean isCommentRequest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        initViews();
        Intent clickedEventDetails = getIntent();
        clickedEventID = clickedEventDetails.getIntExtra("Event ID", 0);

        clickedEventComments = new ArrayList<>();

        new GetEventDetailsBasedOnID().execute("http://10.0.3.2:5000/event/by_id?id=" + clickedEventID, "false");

        new GetEventDetailsBasedOnID().execute("http://10.0.3.2:5000/comment/by_event_id?eventId="+ clickedEventID, "true");

        expandable.setRenderer(new ExpandableLayout.Renderer<String, Comment>() {
            // Klikkelhető felület, amely legördül
            @Override
            public void renderParent(View view, String s, boolean isExpanded, int parentPosition) {
                TextView commentsHeader = view.findViewById(R.id.tvCommentsParent);
                commentsHeader.setText("Hozzászólások");
                imgArrow = view.findViewById(R.id.imgArrow);
                imgArrow.setBackgroundResource(R.drawable.ic_arrow_down_gold_24dp);

            }

            @Override
            public void renderChild(View view, Comment comment, int parentPosition, int childPosition) {

                TextView user = view.findViewById(R.id.tvCommentUser);
                user.setText(comment.getUserId());
                user.setTextColor(getResources().getColor(R.color.buttonTextColor));
                TextView message = view.findViewById(R.id.tvCommentMessage);
                message.setText(comment.getMessage());
                message.setTextColor(getResources().getColor(R.color.headerTextView));

                /// TODO: Picture nem alapján
                ImageView picture = view.findViewById(R.id.imgUserComment);
                picture.setBackgroundResource( R.drawable.user_man_1 );
            }
        });

        expandable.setExpandListener(new ExpandCollapseListener.ExpandListener<String>() {
            @Override
            public void onExpanded(int parentIndex, String parent, View view) {
                //Layout expanded
                imgArrow.setBackgroundResource(R.drawable.ic_arrow_up_gold_24dp);

            }
        });

        expandable.setCollapseListener(new ExpandCollapseListener.CollapseListener<String>() {
            @Override
            public void onCollapsed(int parentIndex, String parent, View view) {
                //Layout collapsed
                imgArrow.setBackgroundResource(R.drawable.ic_arrow_down_gold_24dp);
            }
        });

    }

    private Section<String, Comment> getCommentSection() {
        Section<String, Comment> section = new Section<>();
        section.parent = "Hozzászólások";
        section.children.addAll(clickedEventComments);
        return section;
    }



    private void initViews() {
        eventName = findViewById(R.id.tvEventName);
        eventLocation = findViewById(R.id.tvLocale);
        eventPrice = findViewById(R.id.tvPrice);
        eventStartDate = findViewById(R.id.tvStartDate);
        eventEndDate = findViewById(R.id.tvEndDate);
        eventHeadcount = findViewById(R.id.tvHeadCount);
        eventAudience = findViewById(R.id.tvAudience);
        eventDescription = findViewById(R.id.tvDescription);
        expandable = findViewById(R.id.expendableCommentSection);
    }

    public class GetEventDetailsBasedOnID extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection conn = null;
            BufferedReader reader = null;
            try {

                isCommentRequest = urls[1].equals("true") ? true : false;

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

                if(!isCommentRequest) {
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
                }
                else {
                    JSONArray data = root.getJSONArray("data");
                    for(int i = 0; i < data.length(); i++) {
                        JSONObject tmp = data.getJSONObject(i);
                        Comment comment = new Comment(
                                tmp.getInt("id"),
                                tmp.getString("message"),
                                tmp.getInt("eventId"),
                                tmp.getString("userId"));

                        clickedEventComments.add(comment);

                    }

                    expandable.addSection(getCommentSection());
                }
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


            eventName.setText(getString(R.string.event_name) + ": " + clickedEvent.getName());
            eventLocation.setText(getString(R.string.event_locale) + ": " + clickedEvent.getCountry() + ", " + clickedEvent.getCity() + ", " + clickedEvent.getLocale());




            eventPrice.setText(getString(R.string.event_price) + ": " + String.valueOf(clickedEvent.getPrice()) + " Ft");

            String dateOfEvent = clickedEvent.getDateOfEvent() + " " + clickedEvent.getStart();

            eventStartDate.setText(getString(R.string.event_start_date) + ": " + dateOfEvent);
            eventEndDate.setText(getString(R.string.event_end_date) + ": " + clickedEvent.getFinish());

            eventDescription.setText(getString(R.string.event_description) + ": " + clickedEvent.getDescription());
            eventHeadcount.setText(getString(R.string.event_headcount) + ": " + String.valueOf(clickedEvent.getHeadCount()) + " fő");
            eventAudience.setText(getString(R.string.event_audience) + ": " + clickedEvent.getAudience());

        }
    }
}

