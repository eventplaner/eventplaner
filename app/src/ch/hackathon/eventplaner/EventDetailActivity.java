package ch.hackathon.eventplaner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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
		final int selectedEventId = intentextras
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
		eventStartDateText.setText(selectedEvent.getLocalisedStartDateTime(getApplicationContext()));
		eventEndDateText.setText(selectedEvent.getLocalisedEndDateTime(getApplicationContext()));
	
		// Fill the participants list
		ListView participantList = (ListView) findViewById(R.id.detailsParticipantList);
		ParticipantListViewAdapter plva = new ParticipantListViewAdapter(this, eventmanager.getParticipantsOfEvent(selectedEvent));
		participantList.setAdapter(plva);
		participantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO: Implement something?
			}
		});
		
		// Add Participants Button
		Button addParticipantButton = (Button) findViewById(R.id.detailsNewParticipantButton);
		addParticipantButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent newParticipantIntent = new Intent(getApplicationContext(), EventAddParticipantActivity.class);
				newParticipantIntent.putExtra(EVENTEDIT_EXTRAS_KEY, selectedEventId);
				startActivity(newParticipantIntent);
			}
		});
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
