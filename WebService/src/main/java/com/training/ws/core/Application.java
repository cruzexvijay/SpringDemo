package com.training.ws.core;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.training.spring.Factory.ApplicationFactory;
import com.training.spring.model.Candidate;
import com.training.spring.model.Test;

public class Application {
	
	
	private InputStream in;
	
	private static Application instance;
	private ApplicationFactory appFactory;

	private Application(){
		
	}
	
	public static Application getInstance(){
		
		if(instance==null)
			instance = new Application();
		
		return instance;
	}
	
	public InputStream getPropertyStream() throws IOException{
		return loadPropertyFile();
	}
	
	public InputStream loadPropertyFile() throws IOException{
		return in = new FileInputStream("D:\\Spring Workspace\\WebService\\appConfig.properties");
	}
	
	public ApplicationFactory getFactoryInstance() throws IOException{
		
		appFactory = ApplicationFactory.getInstance();
		
		appFactory.setProperties(getPropertyStream());
		
		return appFactory;
	}
	
 /*	public static void main(String[] args) throws JAXBException, IOException, TransformerConfigurationException, ParserConfigurationException, TransformerException {
		
		ApplicationFactory factory = ApplicationFactory.getInstance();

		factory.setProperties(new FileInputStream("appConfig.properties"));
			
		CandidateService service = factory.getCandidateService();
		TestService testService = factory.getTestService();
		
		for(Test t : createDummyTest())
			testService.create(t);
		
		for(Candidate c : createDummyCandidates())
			service.create(c);
		
		candidateList = service.findAll();
		
		System.out.println(candidateList);
		
		testList = testService.getAllTests();
		
		System.out.println(testList);
	}*/
	
}
