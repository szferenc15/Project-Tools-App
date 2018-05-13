package hu.application.sportmates.model;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hu.application.sportmates.R;

/**
 * Az alkalmazásban található események egy ListViewban jellenek meg
 * Saját adapterre van szükség ahhoz, hogy egyedi módon tudjuk megjelenítő az eseményeket a programban
 * A megjelenítés kinézetét a layout/event_page.xml írja le.
 */
public class EventAdapter extends ArrayAdapter {
    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.event_page, parent, false);
        }
        Event currentEvent = (Event) getItem(position);

        TextView eventName = listItemView.findViewById(R.id.tvEventName);
        eventName.setText(currentEvent.getName());
        return listItemView;
    }
}
