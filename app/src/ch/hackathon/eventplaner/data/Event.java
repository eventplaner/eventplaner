package ch.hackathon.eventplaner.data;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ch.hackathon.eventplaner.logic.EventManager;
import android.content.Context;

/**
 * An Event from the API
 */
public class Event {
	private int id = -1;
	private String name;
	private String description;
	private Date start;
	private Date end;
	private String position_latitude;
	private String position_longitude;
	private Date createDate;
	private Date changeDate;
	private int createuser_id;
	private List<Participant> participants;
	private Context appcontext;
	
	public Event (Context context) {
		appcontext = context;
	}
	
	public List<Participant> getParticipants() {
		EventManager em = new EventManager(appcontext);
		participants = em.getParticipantsOfEvent(this);
		return participants;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getPosition_latitude() {
		return position_latitude;
	}
	public void setPosition_latitude(String position_latitude) {
		this.position_latitude = position_latitude;
	}
	public String getPosition_longitude() {
		return position_longitude;
	}
	public void setPosition_longitude(String position_longitude) {
		this.position_longitude = position_longitude;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public int getCreateuser_id() {
		return createuser_id;
	}
	public void setCreateuser_id(int createuser_id) {
		this.createuser_id = createuser_id;
	}
	private String getLocalisedDate(Date date,Context context)
	{
		Format dateFormat = android.text.format.DateFormat.getDateFormat(context);
		SimpleDateFormat sdf = new SimpleDateFormat(((SimpleDateFormat) dateFormat).toLocalizedPattern());
		return sdf.format(date);
	}
	
	private String getLocalisedTime(Date date, Context context)
	{
		Format timeFormat = android.text.format.DateFormat.getTimeFormat(context);
		SimpleDateFormat sdf = new SimpleDateFormat(((SimpleDateFormat) timeFormat).toLocalizedPattern());
		return sdf.format(date);
	}
	
	public String getLocalisedStartDateTime(Context context)
	{
		String date = getLocalisedDate(start, context);
		String time = getLocalisedTime(start, context);
		return String.format("%s %s", date,time);
	}
	
	public String getLocalisedEndDateTime(Context context)
	{
		String date = getLocalisedDate(end, context);
		String time = getLocalisedTime(end, context);
		return String.format("%s %s", date,time);
	}
	
	public String getLocalisedStartDate(Context context)
	{
		return getLocalisedDate(start, context);
	}
	
	public String getLocalisedStartTime(Context context)
	{
		return getLocalisedTime(start, context);
	}
	
	public String getLocalisedEndDate(Context context)
	{
		return getLocalisedDate(end, context);
	}
	
	public String getLocalisedEndTime(Context context)
	{
		return getLocalisedTime(end, context);
	}
	
	public String getLocalisedCreateDate(Context context)
	{
		return getLocalisedDate(createDate, context);
	}
	
	public String getLocalisedChangeDate(Context context)
	{
		return getLocalisedDate(changeDate, context);
	}
}
