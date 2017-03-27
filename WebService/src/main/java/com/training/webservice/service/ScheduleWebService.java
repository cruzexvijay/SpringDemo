package com.training.webservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.training.spring.Factory.ApplicationFactory;
import com.training.spring.model.Schedule;
import com.training.spring.service.ScheduleService;
import com.training.ws.core.Application;

@Path("/schedule")
public class ScheduleWebService {

	private ApplicationFactory factory;
	private static Log log = LogFactory.getLog(ScheduleService.class);
	private ScheduleService scheduleService;

	public ScheduleWebService() throws IOException, JAXBException {
		factory = Application.getInstance().getFactoryInstance();
		scheduleService = factory.getScheduleService();
		// log.info("Schedule web service");
		populate();
	}

	public void populate() throws IOException, JAXBException {
		for (Schedule s : createDummySchedule()) {
			scheduleService.create(s);
		}
	}

	@GET
	@Path("/msg")
	public Response showMsg() {
		return Response.status(200).entity("Welcome").build();
	}

	@GET
	@Path("/all")
	public Response getAllSchedules() throws JAXBException, IOException {

		List<Schedule> list = scheduleService.findAllSchedules();

		log.info("SCHEDULES : " + list.size());

		Gson son = new Gson();

		String s = son.toJson(list);

		return Response.status(200).entity(s).build();
	}

	@GET
	@Path("/upcoming")
	public Response getUpComingSchedules() throws JAXBException {

		List<Schedule> list = new LinkedList<>();
		Date currentDate = new Date();
		
		log.info("Current Date :"+currentDate+" in ms :"+currentDate.getTime());

		for (Schedule s : scheduleService.findAllSchedules()) {
			log.info("List Date : "+new Date(s.getDate()));
			if(s.getDate()>currentDate.getTime()){
				list.add(s);
				
			}
		}

		String response = new Gson().toJson(list);
		return Response.status(200).entity(response).build();
	}
	
	

	private static List<Schedule> createDummySchedule() {
		List<Schedule> list = new ArrayList<>();
		
	
		Date d = setCalendar(2017, 03, 27, 12, 30, 00,Calendar.AM_PM).getTime();
		
		long date = d.getTime();
		
		Schedule newSchedule = Schedule.builder().candidateId("1").Location("MBP").date(date).build();
		list.add(newSchedule);

		newSchedule = Schedule.builder().candidateId("2").Location("CHN").date(date).build();
		list.add(newSchedule);

		newSchedule = Schedule.builder().candidateId("3").Location("MBP").date(date).build();
		list.add(newSchedule);
		
		d = setCalendar(2010, 03, 27, 12, 30, 00,Calendar.AM_PM).getTime();
		
		date = d.getTime();
		
		newSchedule = Schedule.builder().candidateId("2").Location("CHN").date(date).build();
		//log.info("DATE : "+newSchedule.getDate().getTime());
		list.add(newSchedule);

		newSchedule = Schedule.builder().candidateId("3").Location("MBP").date(date).build();
		list.add(newSchedule);

		return list;
	}
	
	private static Calendar setCalendar(int year,int month,int date,int hours,int mins,int seconds,int AMPM){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH,month);
		c.set(Calendar.DATE, (date));
		c.set(Calendar.HOUR,(hours));
		c.set(Calendar.MINUTE, mins);
		c.set(Calendar.SECOND, seconds);
		c.set(Calendar.AM_PM, AMPM);
		
		return c;
	}

}
