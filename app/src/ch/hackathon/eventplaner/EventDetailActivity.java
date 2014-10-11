package ch.hackathon.eventplaner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import ch.hackathon.eventplaner.data.Event;
import ch.hackathon.eventplaner.logic.EventManager;

public class EventDetailActivity extends Activity {
	private Event selectedEvent;
	public static final String EVENTEDIT_EXTRAS_KEY = "selectedEventId";

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_detail);
		if (android.os.Build.VERSION.SDK_INT >= 8) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		// Load selected Event
		EventManager eventmanager = new EventManager();
		Bundle intentextras = getIntent().getExtras();
		int selectedEventId = intentextras
				.getInt(MainActivity.EVENTDETAIL_EXTRAS_KEY);
		selectedEvent = eventmanager.getEventById(selectedEventId);

		// Put selected Event in the UI
		TextView eventNameText = (TextView) findViewById(R.id.EventNameText);
		TextView eventLocationText = (TextView) findViewById(R.id.LocationText);
		TextView eventStartDateText = (TextView) findViewById(R.id.StartDateText);
		TextView eventEndDateText = (TextView) findViewById(R.id.EndDateText);
		eventNameText.setText(selectedEvent.getName());
		eventLocationText.setText(selectedEvent.getPosition_longitude() + "; "
				+ selectedEvent.getPosition_latitude());
		eventStartDateText.setText(selectedEvent.getStart().toGMTString());
		eventEndDateText.setText(selectedEvent.getEnd().toGMTString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_edit:
			Intent editIntent = new Intent(this, EventEditActivity.class);
			editIntent.putExtra(EVENTEDIT_EXTRAS_KEY, selectedEvent.getId());
			startActivity(editIntent);
			break;
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed in the Action
			// Bar.
			Intent parentActivityIntent = new Intent(this, MainActivity.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
