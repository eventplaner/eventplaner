package ch.hackathon.eventplaner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import ch.hackathon.eventplaner.data.Event;
import ch.hackathon.eventplaner.logic.EventManager;

public class EventEditActivity extends Activity {
	private Event selectedEvent;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_edit);

		if (android.os.Build.VERSION.SDK_INT >= 8) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		// Get selected Event
		EventManager eventmanager = new EventManager();
		Bundle intentextras = getIntent().getExtras();
		int selectedEventId = intentextras
				.getInt(EventDetailActivity.EVENTEDIT_EXTRAS_KEY);
		selectedEvent = eventmanager.getEventById(selectedEventId);
		
		if (selectedEvent  != null) {
			// Put selected Event in the UI
			TextView eventNameText = (TextView) findViewById(R.id.EventNameText);
			Button eventStartDateButton = (Button) findViewById(R.id.StartDateButton);
			Button eventStartTimeButton = (Button) findViewById(R.id.StartTimeButton);
			Button eventEndDateButton = (Button) findViewById(R.id.EndDateButton);
			Button eventEndTimeButton = (Button) findViewById(R.id.EndTimeButton);
			eventNameText.setText(selectedEvent.getName());
			eventStartDateButton.setText(selectedEvent.getLocalisedStartDate(getApplicationContext()));
			eventStartTimeButton.setText(selectedEvent.getLocalisedStartTime(getApplicationContext()));
			
			eventEndDateButton.setText(selectedEvent.getLocalisedEndDate(getApplicationContext()));
			eventEndTimeButton.setText(selectedEvent.getLocalisedEndTime(getApplicationContext()));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
