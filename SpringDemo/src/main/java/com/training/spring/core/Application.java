package com.training.spring.core;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.training.spring.Factory.ApplicationFactory;
import com.training.spring.Factory.FileFactory;
import com.training.spring.model.Candidate;
import com.training.spring.model.Schedule;
import com.training.spring.model.Test;
import com.training.spring.service.CandidateService;
import com.training.spring.service.ScheduleService;
import com.training.spring.service.TestService;

public class Application {

	public static void main(String[] args) throws IOException, ParserConfigurationException,
			TransformerConfigurationException, TransformerException, JAXBException {
			
		ApplicationFactory factory = ApplicationFactory.getInstance();
		
		factory.setProperties(new FileInputStream(new File("appConfig.properties")));
	
		CandidateService service = factory.getCandidateService();
		TestService testService = factory.getTestService();
		ScheduleService scheduleService = factory.getScheduleService();
		
		for (Test t : createDummyTest())
			testService.create(t);

		System.out.println(testService.getAllTests());

		for(Candidate c : createDummyCandidates())
			service.create(c);

		System.out.println(service.findAll());
		
		for(Schedule s : createDummySchedule())
			scheduleService.create(s);
			
		System.out.println(scheduleService.findAllSchedules());
	}

	private static List<Test> createDummyTest() {
		List<Test> testList = new ArrayList<>();
		
		Test newTest = Test.builder().candidateId("1").testId("1").questionName("cut the sticks")
				.questionPath("c:\\files").testStartDate(new Date()).feedBack("good").testStatus(Test.PASS).build();

		testList.add(newTest);

		newTest = Test.builder().candidateId("1").questionName("caesar cipher").questionPath("c:\\files")
				.testStartDate(new Date()).feedBack("poor").testStatus(Test.FAIL).build();

		testList.add(newTest);

		return testList;
	}

	private static List<Candidate> createDummyCandidates() {
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
	}
	
	private static List<Schedule> createDummySchedule(){
		List<Schedule> list = new ArrayList<>();
		
		Schedule newSchedule = Schedule.builder().candidateId("1").Location("MBP").date(new Date()).build();
		list.add(newSchedule);
		
		newSchedule = Schedule.builder().candidateId("2").Location("CHN").date(new Date()).build();
		list.add(newSchedule);
		
		newSchedule = Schedule.builder().candidateId("3").Location("MBP").date(new Date()).build();
		list.add(newSchedule);
		
		return list;
	}
}
