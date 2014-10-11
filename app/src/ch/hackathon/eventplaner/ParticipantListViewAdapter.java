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
import ch.hackathon.eventplaner.data.Participant;

public class ParticipantListViewAdapter extends BaseAdapter {
	private List<Participant> participantlist;
	private Activity activity;

	public ParticipantListViewAdapter(Activity currentActivity, List<Participant> participants) {
		super();
		activity = currentActivity;
		participantlist = participants;
	}

	@Override
	public int getCount() {
		if (participantlist != null) {
			return participantlist.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (participantlist != null) {
			return participantlist.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		if (participantlist != null) {
			return position;
		}
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflator = activity.getLayoutInflater();
		Participant participantToRender = participantlist.get(position);
		if (convertView == null) {
			convertView = layoutInflator.inflate(R.layout.participant_listrow, null);
			
			// Set Title + Date
			// TODO: Print Date pretty
			TextView participantName = (TextView) convertView.findViewById(R.id.nameText);
			ImageView participantStatusImg = (ImageView) convertView.findViewById(R.id.statusImage);
			participantName.setText(participantToRender.getUser().getName());
			if (Boolean.TRUE.equals(participantToRender.isStatus())) {
				participantStatusImg.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_action_accept));
			}
			else if (Boolean.FALSE.equals(participantToRender.isStatus())) {
				participantStatusImg.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_action_cancel));
			}
			else {
				participantStatusImg.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_action_help));
			}
		}
		return convertView;
	}

}
