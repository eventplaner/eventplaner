package ch.hackathon.eventplaner.data;

import java.util.Date;

/**
 * An Event from the API
 */
public class Event {
	private int id;
	private String name;
	private String description;
	private Date start;
	private Date end;
	private String position_latitude;
	private String position_longitude;
	private Date createDate;
	private Date changeDate;
	private int createuser_id;
	
	
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
}
