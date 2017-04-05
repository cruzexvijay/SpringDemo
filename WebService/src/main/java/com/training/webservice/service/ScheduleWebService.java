package com.training.webservice.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.training.spring.Factory.ApplicationFactory;
import com.training.spring.model.Schedule;
import com.training.spring.model.TestMedium;
import com.training.spring.service.ScheduleService;
import com.training.webservice.exceptions.AppException;
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
		//populate();
	}

	public void populate() throws IOException, JAXBException {
		for (Schedule s : createDummySchedule()) {
			log.info("date set : "+s.getDateTime());
			scheduleService.create(s);
		}
	}

	@GET
	@Path("/msg")
	public Response showMsg() {
		return Response.status(200).entity("Welcome").build();
	}

	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUpComingSchedules(@QueryParam("q") String filter) throws JAXBException, ParseException, AppException {
				
		List<Schedule> list = new LinkedList<>();
		List<Schedule> schedules = scheduleService.findAllSchedules();
		Date currentDate = new Date();
		
		if(filter==null||filter.equals("all")){
			filter = "none";
		}
		
		if(schedules==null || schedules.isEmpty()){
			throw AppException.builder().status(Response.Status.NOT_FOUND.getStatusCode())
			.errorCode(1295)
			.exceptionLink("http://localhost:8080/error?q=1295")
			.msg("No schedules found")
			.build();
		}
	
		filter = filter.toLowerCase();
				
		Date d = null;
		for (Schedule s : schedules) {
			
			d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").parse(s.getDateTime());
		
			switch(filter){
			case "upcoming":
				if(currentDate.getTime() < d.getTime()){
					list.add(s);
				}
				break;
			case "completed":
				if(currentDate.getTime() > d.getTime()){
					list.add(s);
				}
				break;
			case "ongoing":
				if((currentDate.getTime() - 3600000)<=d.getTime()){
					list.add(s);
				}
				break;
			case "none":
				list.add(s);
				break;
			
			}
			
				
		}

		String response = new Gson().toJson(list);
		return Response.status(200).entity(response).build();
	}
	
	
	@GET
	@Path("/{candidateId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCandidateSchedules(@PathParam("candidateId") String candidateId) throws AppException{
		log.info("FINDING CANDIDATE SCHEDULES");
		
		List<Schedule> schedules = scheduleService.findCandidateSchedules(candidateId);
		
		if(schedules==null || schedules.isEmpty()){
			System.out.println("list is empty");
			throw AppException.builder().status(Response.Status.NOT_FOUND.getStatusCode())
			.errorCode(1295)
			.exceptionLink("http://localhost:8080/errors?code=1295")
			.msg("No schedules found for the selected candidate")
			.build();
		}
			
			return Response.status(200).entity(new Gson().toJson(schedules)).build();
		
	}
	
	

	private static List<Schedule> createDummySchedule() {
		List<Schedule> list = new ArrayList<>();
		
		String date = "2017-03-27 12:30:00 PM";
		
		Schedule newSchedule = Schedule.builder().candidateId("1").Location("MBP").dateTime(date).medium(TestMedium.INPERSON.toString()).build();
		list.add(newSchedule);

		newSchedule = Schedule.builder().candidateId("2").Location("CHN").dateTime(date).medium(TestMedium.SKYPE.toString()).build();
		list.add(newSchedule);

		newSchedule = Schedule.builder().candidateId("3").Location("MBP").dateTime(date).medium(TestMedium.INPERSON.toString()).build();
		list.add(newSchedule);
		
		date = "2010-03-27 12:30:00 PM";
		
		newSchedule = Schedule.builder().candidateId("2").Location("CHN").dateTime(date).medium(TestMedium.SKYPE.toString()).build();
		//log.info("DATE : "+newSchedule.getDate().getTime());
		list.add(newSchedule);

		newSchedule = Schedule.builder().candidateId("3").Location("MBP").dateTime(date).medium(TestMedium.INPERSON.toString()).build();
		list.add(newSchedule);

		return list;
	}
	

}
