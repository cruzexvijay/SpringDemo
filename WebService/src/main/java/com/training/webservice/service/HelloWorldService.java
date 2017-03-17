package com.training.webservice.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloWorldService {

	@GET
	@Path("/msg")
	public String getWelcomeMessage(){
		return "Hello World";
		//com.sun.jersey.config.property.packages
		
	}
	
}
