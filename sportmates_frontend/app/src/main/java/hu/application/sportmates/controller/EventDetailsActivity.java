package hu.application.sportmates.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import hu.application.sportmates.R;
import hu.application.sportmates.model.Comment;
import hu.application.sportmates.model.Event;
import hu.application.sportmates.model.User;
import iammert.com.expandablelib.ExpandCollapseListener;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class EventDetailsActivity extends AppCompatActivity {

    private int clickedEventID;

    private TextView
            tvEventName, tvEventLocation, tvEventPrice, tvEventStartDate, tvEventEndDate,
            tvEventHeadCount, tvEventAudience, tvEventDescription,
            tvEventAction;


    private ImageView imgEventAction, imgArrow;
    private EditText edtCommentField;
    private String prevActivityName;
    private Event clickedEvent;
    private Section<String, Comment> section;
    private ArrayList<Comment> clickedEventComments;
    private ExpandableLayout expandable;
    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        initViews();
        Intent eventDetailsWithUserData = getIntent();
        clickedEventID = eventDetailsWithUserData.getIntExtra("Event ID", 0);
        loggedInUser = eventDetailsWithUserData.getParcelableExtra("data_of_user");
        prevActivityName = eventDetailsWithUserData.getStringExtra("Previous Activity");

        initHeaderBasedOnPreviousActivity();

        clickedEventComments = new ArrayList<>();
        section = new Section<>();
        section.parent = "Hozzászólások";
        section.expanded = true;

        new GetEventDetailsByID().execute("http://10.0.3.2:5000/event/by_id?id=" + clickedEventID);
        new GetCommentsByEventID().execute("http://10.0.3.2:5000/comment/by_event_id?eventId=" + clickedEventID);


        eventActionListener();
        renderCommentSection();
        setSectionHeaderListener();
        sendMessageListener();


    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(EventDetailsActivity.this, MainActivity.class);
            intent.putExtra("data_of_user", loggedInUser);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void renderCommentSection() {
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
                ImageView picture = view.findViewById(R.id.imgUserComment);
                picture.setBackgroundResource(R.drawable.user_man_1);

                if (getLastComment().getId() == comment.getId()) {
                    edtCommentField.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // Hozzászólások klikk eseményei
    private void setSectionHeaderListener() {
        expandable.setExpandListener(new ExpandCollapseListener.ExpandListener<String>() {
            @Override
            public void onExpanded(int parentIndex, String parent, View view) {
                //Layout expanded
                imgArrow.setBackgroundResource(R.drawable.ic_arrow_up_gold_24dp);
                edtCommentField.setVisibility(View.VISIBLE);

            }
        });

        expandable.setCollapseListener(new ExpandCollapseListener.CollapseListener<String>() {
            @Override
            public void onCollapsed(int parentIndex, String parent, View view) {
                //Layout collapsed
                imgArrow.setBackgroundResource(R.drawable.ic_arrow_down_gold_24dp);
                edtCommentField.setVisibility(View.GONE);
            }
        });
    }

    private Comment getLastComment() {
        int size = clickedEventComments.size();
        return clickedEventComments.get(size - 1);
    }

    private void initHeaderBasedOnPreviousActivity() {
        if (prevActivityName.equals("MainActivity")) {
            tvEventAction.setText(getString(R.string.event_add));
            imgEventAction.setBackgroundResource(R.drawable.ic_add_event_white_24dp);
        } else {
            tvEventAction.setText(getString(R.string.event_remove));
            imgEventAction.setBackgroundResource(R.drawable.ic_remove_event_white_24dp);
        }
    }

    private void initViews() {
        tvEventName = findViewById(R.id.tvEventName);
        tvEventLocation = findViewById(R.id.tvLocale);
        tvEventPrice = findViewById(R.id.tvPrice);
        tvEventStartDate = findViewById(R.id.tvStartDate);
        tvEventEndDate = findViewById(R.id.tvEndDate);
        tvEventHeadCount = findViewById(R.id.tvHeadCount);
        tvEventAudience = findViewById(R.id.tvAudience);
        tvEventDescription = findViewById(R.id.tvDescription);
        expandable = findViewById(R.id.expendableCommentSection);
        edtCommentField = findViewById(R.id.edtCommentField);
        imgEventAction = findViewById(R.id.imgEventAction);
        tvEventAction = findViewById(R.id.txtEventAction);

    }

    private void createComment() {
        JSONObject postData = new JSONObject();
        try {

            String message = edtCommentField.getText().toString();

            Log.e("Logged in user", loggedInUser.toString());

            postData.put("message", URLEncoder.encode(message, "UTF-8"));
            postData.put("eventId", clickedEventID);
            postData.put("userId", String.valueOf(loggedInUser.getId()));

            String result = new InsertNewComment().execute("http://10.0.3.2:5000/comment/add", postData.toString()).get();

            if (result.equals("200")) {
                edtCommentField.setText("");
                Comment tmp = new Comment(message, clickedEventID, String.valueOf(loggedInUser.getId()));
                clickedEventComments.add(tmp);
                section.children.add(tmp);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessageListener() {
        edtCommentField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (edtCommentField.getRight() - edtCommentField.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Toast.makeText(EventDetailsActivity.this, "Üzenet elküldve", Toast.LENGTH_SHORT).show();
                        createComment();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void eventActionListener() {
        if (prevActivityName.equals("MainActivity")) {
            imgEventAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONObject postData = new JSONObject();
                    try {
                        postData.put("eventId", clickedEventID);
                        postData.put("userId", loggedInUser.getId());
                        String result = new AddEventToUser().execute("http://10.0.3.2:5000/event_user/signup", postData.toString()).get();
                        if (result.equals("200")) {
                            Toast.makeText(EventDetailsActivity.this, "Sikeres feljelentkezés", Toast.LENGTH_SHORT).show();
                            loggedInUser.getEventIDs().add(clickedEventID);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            imgEventAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean removed = false;
                    for (int i = 0; i < loggedInUser.getEventIDs().size(); i++) {
                        if (clickedEventID == loggedInUser.getEventIDs().get(i)) {
                            loggedInUser.getEventIDs().remove(clickedEvent);
                            removed = true;
                            break;
                        }
                    }

                    if (removed) {
                        JSONObject postData = new JSONObject();
                        try {
                            postData.put("eventId", clickedEventID);
                            postData.put("userId", loggedInUser.getId());
                            String result = new RemoveUserFromEvent().execute("http://10.0.3.2:5000/event_user/quit", postData.toString()).get();
                            if (result.equals("200")) {
                                for(int i = 0; i < loggedInUser.getEventIDs().size(); i++) {
                                    if(clickedEventID == loggedInUser.getEventIDs().get(i)) {
                                        loggedInUser.getEventIDs().remove(i);
                                        break;
                                    }
                                }
                                Toast.makeText(EventDetailsActivity.this, "Sikeres lejelentkezés", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(EventDetailsActivity.this, "Erre az eseményre nem vagy feljelentkezve", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    /////////////////////////////////////////////////////////////////////////
    //////////////////////////  BACKGROUND TASKS   //////////////////////////
    /////////////////////////////////////////////////////////////////////////

    // params, progress, result
    public class GetEventDetailsByID extends AsyncTask<String, String, String> {

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

                return JSONResponse;
            } catch (JSONException | IOException ex) {
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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tvEventName.setText(getString(R.string.event_name) + ": " + clickedEvent.getName());
            tvEventLocation.setText(getString(R.string.event_locale) + ": " + clickedEvent.getCountry() + ", " + clickedEvent.getCity() + ", " + clickedEvent.getLocale());
            tvEventPrice.setText(getString(R.string.event_price) + ": " + String.valueOf(clickedEvent.getPrice()) + " Ft");

            String dateOfEvent = clickedEvent.getDateOfEvent() + " " + clickedEvent.getStart();
            tvEventStartDate.setText(getString(R.string.event_start_date) + ": " + dateOfEvent);
            tvEventEndDate.setText(getString(R.string.event_end_date) + ": " + clickedEvent.getFinish());
            tvEventDescription.setText(getString(R.string.event_description) + ": " + clickedEvent.getDescription());
            tvEventHeadCount.setText(getString(R.string.event_headcount) + ": " + String.valueOf(clickedEvent.getHeadCount()) + " fő");
            tvEventAudience.setText(getString(R.string.event_audience) + ": " + clickedEvent.getAudience());
        }
    }

    public class GetCommentsByEventID extends AsyncTask<String, String, String> {
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
                for (int i = 0; i < data.length(); i++) {
                    JSONObject tmp = data.getJSONObject(i);
                    Comment comment = new Comment(
                            URLDecoder.decode(tmp.getString("message"), "UTF-8"),
                            tmp.getInt("eventId"),
                            tmp.getString("userId"));
                    clickedEventComments.add(comment);
                }

                return JSONResponse;
            } catch (JSONException | IOException ex) {
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
            section.children.addAll(clickedEventComments);
            expandable.addSection(section);
        }
    }

    public class InsertNewComment extends AsyncTask<String, String, String> {
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

    public class AddEventToUser extends AsyncTask<String, String, String> {
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

    public class RemoveUserFromEvent extends AsyncTask<String, String, String> {
        int responseCode = 0;

        @Override
        protected String doInBackground(String... strings) {
            try {

                String url = strings[0];
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
                con.setRequestMethod("DELETE");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = strings[1];

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

