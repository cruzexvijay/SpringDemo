package com.training.spring.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="Schedules")
@XmlRootElement(name="Schedules") 
@XmlAccessorType(XmlAccessType.FIELD)
public class Schedules {

	@XmlElement(name="Schedule",required=true)
	private List<Schedule> scheduleList = new LinkedList<>();
	
	private static Schedules instance = new Schedules();
	
	@XmlElement(required=false)
	private int LAST_ROW_ID;
	
	private Schedules(){
	}
	
	public static Schedules getInstance(){
		return instance;
	}
	
	public void addNewSchedule(Schedule newSchedule){
		if(newSchedule.getCandidateId()!=null){
			LAST_ROW_ID = Integer.parseInt(newSchedule.getId());
		}
		this.scheduleList.add(newSchedule);
		//System.out.println("added to list"+this.scheduleList.size());
	}
	
	public int getRowId(){
		return ++(this.LAST_ROW_ID);
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	
}
