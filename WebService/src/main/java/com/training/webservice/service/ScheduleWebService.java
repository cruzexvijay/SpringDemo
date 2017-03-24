package com.training.webservice.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.JAXBException;

import com.training.spring.Factory.ApplicationFactory;
import com.training.spring.model.Schedule;
import com.training.spring.service.ScheduleService;
import com.training.ws.core.Application;

@Path("/schedule")
public class ScheduleWebService {

	private ApplicationFactory factory;
	
	public ScheduleWebService() throws IOException{
		factory = Application.getInstance().getFactoryInstance();
	}
	
	@GET
	@Path("/all")
	public List<Schedule> getAllSchedules() throws JAXBException{
		ScheduleService scheduleService = factory.getScheduleService();
		return scheduleService.findAllSchedules();
	}
	
}
