package ch.hackathon.eventplaner;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ch.hackathon.eventplaner.data.Event;
import ch.hackathon.eventplaner.logic.EventManager;

/**
 * Adapter for the List on the MainActivity (with the users events)
 */
public class MainListViewAdapter extends BaseAdapter {
	private List<Event> eventlist; // events in the list
	private Activity activity; // last activity the user opens
	
	/**
	 * Create a new MainListViewAdapter object
	 * @param currentActivity the activity that will call this methode
	 * @param events a list of displayed events
	 */
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
	/**
	 *  Render one row of the MainListView
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflator = activity.getLayoutInflater();
		Event eventToRender = eventlist.get(position);
		if (convertView == null) {
			convertView = layoutInflator.inflate(R.layout.main_listrow, null);
			
			// Set Title + Date
			// TODO: Print Date pretty
			TextView eventtitle = (TextView) convertView.findViewById(R.id.eventTitleTextView);
			TextView eventdate = (TextView) convertView.findViewById(R.id.eventDateTextView);
			ImageView statusImage = (ImageView) convertView.findViewById(R.id.statusImage);
			eventtitle.setText(eventToRender.getName());
			eventdate.setText(eventToRender.getLocalisedStartDateTime(activity));
			EventManager em = new EventManager(activity.getApplicationContext());
			Boolean ownStatus = em.getCurrentUserstatusOfEvent(eventToRender);
			
			if (Boolean.TRUE.equals(ownStatus)) {
				statusImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_action_accept));
			}
			else if (Boolean.FALSE.equals(ownStatus)) {
				statusImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_action_cancel));
			}
			else {
				statusImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_action_help));
			}
		}
		return convertView;
	}
}
