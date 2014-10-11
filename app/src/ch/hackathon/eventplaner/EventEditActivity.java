package ch.hackathon.eventplaner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
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
		EventManager eventmanager = new EventManager(getApplicationContext());
		Bundle intentextras = getIntent().getExtras();
		int selectedEventId = intentextras
				.getInt(EventDetailActivity.EVENTEDIT_EXTRAS_KEY);
		selectedEvent = eventmanager.getEventById(selectedEventId);

		if (selectedEvent != null) {
			// Put selected Event in the UI
			TextView eventNameText = (TextView) findViewById(R.id.EventNameText);
			Button eventStartDateButton = (Button) findViewById(R.id.StartDateButton);
			eventStartDateButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 DialogFragment datepickerfragment = new DatePickerFragment();
					 ((DatePickerFragment) datepickerfragment).setDatafieldRessource(R.id.StartDateButton);
					 datepickerfragment.show(getFragmentManager(), "datePicker");
				}
			});
			Button eventStartTimeButton = (Button) findViewById(R.id.StartTimeButton);
			Button eventEndDateButton = (Button) findViewById(R.id.EndDateButton);
			eventEndDateButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 DialogFragment datepickerfragment = new DatePickerFragment();
					 ((DatePickerFragment) datepickerfragment).setDatafieldRessource(R.id.EndDateButton);
					 datepickerfragment.show(getFragmentManager(), "datePicker");
				}
			});
			Button eventEndTimeButton = (Button) findViewById(R.id.EndTimeButton);
			eventNameText.setText(selectedEvent.getName());
			eventStartDateButton.setText(selectedEvent
					.getLocalisedStartDate(getApplicationContext()));
			eventStartTimeButton.setText(selectedEvent
					.getLocalisedStartTime(getApplicationContext()));

			eventEndDateButton.setText(selectedEvent
					.getLocalisedEndDate(getApplicationContext()));
			eventEndTimeButton.setText(selectedEvent
					.getLocalisedEndTime(getApplicationContext()));
		} else {
			selectedEvent = new Event(getApplicationContext());
		}

		Button saveButton = (Button) findViewById(R.id.edit_savebutton);
		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EventManager eventManager = new EventManager(
						getApplicationContext());
				TextView eventNameText = (TextView) findViewById(R.id.EventNameText);
				Button eventStartDateButton = (Button) findViewById(R.id.StartDateButton);
				Button eventStartTimeButton = (Button) findViewById(R.id.StartTimeButton);
				Button eventEndDateButton = (Button) findViewById(R.id.EndDateButton);
				Button eventEndTimeButton = (Button) findViewById(R.id.EndTimeButton);
				
				selectedEvent.setStart(((Date)eventStartDateButton.getTag()));
				selectedEvent.setEnd(((Date)eventStartDateButton.getTag()));

				selectedEvent.setName(eventNameText.getText().toString());
				// TODO: Parse DATEs
				selectedEvent.setStart(new Date());
				selectedEvent.setEnd(new Date());
				eventManager.saveEvent(selectedEvent);
				Toast.makeText(getApplicationContext(), "Event saved!",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		});
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

	public class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		private int datafieldRessource;
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			Button datefield = (Button) findViewById(datafieldRessource);
			Event fakeevent = new Event(getApplicationContext());
			Calendar cal = new GregorianCalendar();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month + 1);
			cal.set(Calendar.DAY_OF_MONTH, day);
			fakeevent.setStart(new Date(cal.getTimeInMillis()));
			datefield.setText(fakeevent.getLocalisedStartDate(getApplicationContext()));
			datefield.setTag(new Date(cal.getTimeInMillis()));
		}
		
		public void setDatafieldRessource (int ressourceId) {
			datafieldRessource = ressourceId;
		}
		
		
	}

}
