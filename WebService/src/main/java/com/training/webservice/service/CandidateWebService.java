package com.training.webservice.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import com.google.gson.Gson;
import com.training.spring.Factory.ApplicationFactory;
import com.training.spring.model.Candidate;
import com.training.spring.service.CandidateService;
import com.training.webservice.exceptions.AppException;
import com.training.ws.core.Application;

@Path("/candidate")
public class CandidateWebService {
	
	private ApplicationFactory factory;
	private CandidateService service;
	
	public CandidateWebService() throws IOException, TransformerConfigurationException, ParserConfigurationException, TransformerException, JAXBException {
		factory = Application.getInstance().getFactoryInstance();
		service = factory.getCandidateService();
		
	/*	for (Candidate c : createDummyCandidates()) {
			service.create(c);
		}*/

	}
	
	@POST
	@Path("/candidate/new")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void save(){
		
	}
	
	

	@GET
	@Path("/{candidateId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCandidateDetails(@PathParam("candidateId") String candidateId) throws JAXBException, AppException {
		
		Candidate c  = service.search(candidateId);
		Gson gson = new Gson();
		String response = "[]";
		if(c!=null){
			response = gson.toJson(c);
		}else{
			throw AppException.builder().status(Response.Status.NOT_FOUND.getStatusCode())
			.errorCode(1295)
			.msg("No candidate found with the given candidate id "+candidateId)
			.exceptionLink("http://localhost:8080/errors/code=1295")
			.build();
		}
		
		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCandidates() throws JAXBException, IOException, TransformerConfigurationException,
			ParserConfigurationException, TransformerException {

		List<Candidate> list = service.findAll();

		Gson gson = new Gson();
		String json = gson.toJson(list);
		return Response.status(200).entity(json).build();
	}

	@GET
	@Path("/{candidateId}/test")
	public Response getAllTests(@PathParam("candidateId") String candidateId) throws IOException, JAXBException, AppException {
		System.out.println("Delegating tast to test webservice");
		return new TestWebService().getAllTests(candidateId);
	}
	
	
	@GET
	@Path("/{candidateId}/schedule")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSchedules(@PathParam("candidateId") String candidateId) throws AppException, IOException, JAXBException{
		return new ScheduleWebService().getCandidateSchedules(candidateId);
	}

	/*public static List<Test> createDummyTest() {
		List<Test> testList = new ArrayList<>();

		Test newTest = Test.builder().candidateId("1").testId("1").questionName("cut the sticks")
				.questionPath("c:\\files").testStartDate(new Date()).feedBack("good").testStatus(Test.PASS).build();

		testList.add(newTest);

		newTest = Test.builder().candidateId("2").testId("2").questionName("caesar cipher").questionPath("c:\\files")
				.testStartDate(new Date()).feedBack("poor").testStatus(Test.FAIL).build();

		testList.add(newTest);

		return testList;
	}

	public static List<Candidate> createDummyCandidates() {
		List<Candidate> candidateList = new ArrayList<>();

		Candidate newCandidate = Candidate.builder().candidateId("1").contactNumber("9994319194")
				.firstName("Vijay Kumar").lastName("Rajendran").emailId("rvjkmr@outlook.com").build();

		candidateList.add(newCandidate);

		newCandidate = Candidate.builder().candidateId("2").contactNumber("7550097491").firstName("Vj kmr")
				.lastName("R").emailId("rvjkmr@outlook.com").build();

		candidateList.add(newCandidate);

		newCandidate = Candidate.builder().candidateId("3").contactNumber("3432237843").firstName("adsfasdfas")
				.lastName("asdfasdf").emailId("asdfasdf@outlook.com").build();

		candidateList.add(newCandidate);

		return candidateList;
	}*/

}
