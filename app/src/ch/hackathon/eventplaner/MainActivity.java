package ch.hackathon.eventplaner;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import ch.hackathon.eventplaner.data.Event;
import ch.hackathon.eventplaner.data.User;
import ch.hackathon.eventplaner.logic.EventManager;
import ch.hackathon.eventplaner.logic.SessionManager;

public class MainActivity extends Activity {
	private List<Event> eventlist;
	public static final String EVENTDETAIL_EXTRAS_KEY = "selectedEventId";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Load events of the user
		EventManager eventManager = new EventManager(getApplicationContext());
		eventlist = eventManager.getEventsForMainPage();
		ListView uiListView = (ListView) findViewById(R.id.mainEventListView);
		MainListViewAdapter mla = new MainListViewAdapter(this, eventlist);
		uiListView.setAdapter(mla);
		uiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent detailView = new Intent(getApplicationContext(),	EventDetailActivity.class);
				detailView.putExtra(EVENTDETAIL_EXTRAS_KEY, eventlist.get(position).getId());
				startActivity(detailView);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_newEvent:
			Intent editIntent = new Intent(this, EventEditActivity.class);
			editIntent.putExtra(EventDetailActivity.EVENTEDIT_EXTRAS_KEY, -1);
			startActivity(editIntent);
			break;
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed in the Action
			// Bar.
			Intent parentActivityIntent = new Intent(this, MainActivity.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
