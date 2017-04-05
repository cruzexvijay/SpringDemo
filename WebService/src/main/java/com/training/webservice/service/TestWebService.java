package com.training.webservice.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import com.google.gson.Gson;
import com.training.spring.Factory.ApplicationFactory;
import com.training.spring.model.Test;
import com.training.spring.service.TestService;
import com.training.webservice.exceptions.AppException;

@Path("/test/")
public class TestWebService {
	
	private static TestService service;
	
	public TestWebService(){
		service = ApplicationFactory.getInstance().getTestService();
	}

	@GET
	@Path("/{candidateId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTests(@PathParam("candidateId") String candidateId) throws IOException, JAXBException, AppException {
		
		List<Test> tests = service.getAllCandidateTests(candidateId);
		
		if(tests.isEmpty()){
			throw AppException.builder().status(Response.Status.NOT_FOUND.getStatusCode())
			.errorCode(1295)
			.msg("No tests found for candidate with candidateId "+candidateId)
			.exceptionLink("http://localhost:8080/errors?code=1295").build();
		}
		
		String response = new Gson().toJson(tests);
		return Response.status(200).entity(response).build();
	}
	
	
}
