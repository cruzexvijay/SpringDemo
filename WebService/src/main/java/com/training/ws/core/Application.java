package com.training.ws.core;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.spring.Factory.ApplicationFactory;

public class Application {
	
	
	private static Log log = LogFactory.getLog(Application.class);
	
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
		
		/*in =  loadPropertyFile();
		
		System.out.println(in);
		
		Properties p = new Properties();
		
		p.load(in);
		log.info(p.keySet());
		
		log.info("config loaded : "+p.size());
		*/
		return loadPropertyFile();
	}
	
	public InputStream loadPropertyFile() throws IOException{
		//InputStream in = this.getClass().getClassLoader().getResourceAsStream("appConfig.properties");
				
		InputStream in = new FileInputStream("C:\\Users\\590834\\git\\WebService\\appConfig.properties");
		log.info(in);
		//return new FileInputStream("..\\appConfig.properties");
		return in;
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
