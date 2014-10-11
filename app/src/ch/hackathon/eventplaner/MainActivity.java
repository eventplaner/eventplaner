package ch.hackathon.eventplaner;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import ch.hackathon.eventplaner.data.Event;
import ch.hackathon.eventplaner.data.User;
import ch.hackathon.eventplaner.logic.EventManager;
import ch.hackathon.eventplaner.logic.SessionManager;

public class MainActivity extends Activity {

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
		List<Event> eventlist = eventManager.getEventsForMainPage();
		ListView uiListView = (ListView) findViewById(R.id.mainEventListView);
		MainListViewAdapter mla = new MainListViewAdapter(this, eventlist);
		uiListView.setAdapter(mla);
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
