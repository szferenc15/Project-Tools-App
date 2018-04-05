package hu.application.sport_tool.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hu.application.sport_tool.R;

public class EventAdapter extends ArrayAdapter {

    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, 0,events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.event_page, parent, false);
        }
        Event currentEvent = (Event)getItem(position);

        TextView eventName = listItemView.findViewById(R.id.tvEventName);
        eventName.setText(getContext().getResources().getString(R.string.event_name) + currentEvent.getName());

        TextView audience = listItemView.findViewById(R.id.tvAudience);
        audience.setText(getContext().getResources().getString(R.string.event_audience) + currentEvent.getAudience());

        TextView dateOfEvent = listItemView.findViewById(R.id.tvDateOfEvent);
        dateOfEvent.setText(getContext().getResources().getString(R.string.event_start_date) + currentEvent.getDateOfEvent() + ":" + currentEvent.getStart());

        TextView endOfEvent = listItemView.findViewById(R.id.tvEndOfEvent);
        endOfEvent.setText(getContext().getResources().getString(R.string.event_end_date)+ currentEvent.getDateOfEvent() + ":" + currentEvent.getFinish());

        TextView price = listItemView.findViewById(R.id.tvPrice);
        price.setText(getContext().getResources().getString(R.string.event_price)+ currentEvent.getPrice() + " forint");

        TextView locale = listItemView.findViewById(R.id.tvLocale);
        locale.setText(getContext().getResources().getString(R.string.event_locale) + currentEvent.getLocale());

        TextView description = listItemView.findViewById(R.id.tvDescription);
        description.setText(getContext().getResources().getString(R.string.event_description) + currentEvent.getDescription());
        return listItemView;
    }
}
