package ch.hackathon.eventplaner;

import java.util.List;

import android.app.Activity;
import android.content.Context;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Load user
		SessionManager sessionManager  = new SessionManager(getApplicationContext());
		User currentuser = sessionManager.getUser();
		TextView welcometext = (TextView) findViewById(R.id.textView1);
		welcometext.setText(getString(R.string.welcome) + " " + currentuser.getName());
	
		// Load events of the user
		EventManager eventManager  = new EventManager();
		eventlist = eventManager.getEventsForMainPage();
		ListView uiListView = (ListView) findViewById(R.id.mainEventListView);
		MainListViewAdapter mla = new MainListViewAdapter(this, eventlist);
		uiListView.setAdapter(mla);
		final Context currentCobntext = getApplicationContext();
		uiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent detailView = new Intent(getApplicationContext(), EventDetailActivity.class);
				detailView.putExtra("selectedEventId", eventlist.get(position).getId());
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
