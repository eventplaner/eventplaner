package ch.hackathon.eventplaner;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import ch.hackathon.eventplaner.data.Participant;
import ch.hackathon.eventplaner.data.User;
import ch.hackathon.eventplaner.logic.EventManager;
import ch.hackathon.eventplaner.logic.SessionManager;

public class EventAddParticipantActivity extends Activity {
	private List<Participant> participantList;
	private int eventId; 

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_add_participant);

		if (android.os.Build.VERSION.SDK_INT >= 8) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		Bundle extras = getIntent().getExtras();
		eventId = extras.getInt(EventDetailActivity.EVENTEDIT_EXTRAS_KEY);
		
		// Load users
		SessionManager sessionManager = new SessionManager(getApplicationContext());
		List<User> usersList = sessionManager.getAllUsers();
		ListView usersListView = (ListView) findViewById(R.id.kontaktListView);
		participantList = new ArrayList<Participant>();
		for(User user : usersList)
		{
			Participant newParticipant = new Participant();
			newParticipant.setUser(user);
			participantList.add(newParticipant);
		}
		
		ParticipantListViewAdapter pla = new ParticipantListViewAdapter(this, participantList);
		usersListView.setAdapter(pla);
		final Context currentContext = getApplicationContext();
		usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				EventManager eventManager = new EventManager();
				Participant participant = participantList.get(position);
				participant.setEvent_id(position);
				eventManager.addParticipant(participant);
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_add_participant, menu);
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
