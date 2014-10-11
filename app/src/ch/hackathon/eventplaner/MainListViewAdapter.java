package ch.hackathon.eventplaner;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ch.hackathon.eventplaner.data.Event;

public class MainListViewAdapter extends BaseAdapter {
	private List<Event> eventlist;
	private Activity activity;

	public MainListViewAdapter(Activity currentActivity, List<Event> events) {
		super();
		activity = currentActivity;
		eventlist = events;
	}

	@Override
	public int getCount() {
		if (eventlist != null) {
			return eventlist.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (eventlist != null) {
			return eventlist.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (eventlist != null) {
			return position;
		}
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflator = activity.getLayoutInflater();
		Event eventToRender = eventlist.get(position);
		if (convertView == null) {
			convertView = layoutInflator.inflate(R.layout.main_listrow, null);
			
			// Set Title + Date
			// TODO: Print Date pretty
			TextView eventtitle = (TextView) convertView.findViewById(R.id.eventTitleTextView);
			TextView eventdate = (TextView) convertView.findViewById(R.id.eventDateTextView);
			eventtitle.setText(eventToRender.getName());
			eventdate.setText(eventToRender.getLocalisedStartDateTime(activity));
		}
		return convertView;
	}

}
