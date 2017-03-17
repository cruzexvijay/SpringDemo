package com.training.spring.Factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.training.spring.config.AppConfig;
import com.training.spring.service.CandidateService;
import com.training.spring.service.TestService;

public class ApplicationFactory {

	private static ApplicationFactory factoryInstance;
	private ApplicationContext appContext;

	
	private static final Log log = LogFactory.getLog(ApplicationFactory.class);
		
	private ApplicationFactory(){
		
	}
	
	public static ApplicationFactory getInstance(){
		
		if(factoryInstance==null){
			factoryInstance = new ApplicationFactory();
			
		}
		
		return factoryInstance;
	}
	/**
	 * Custome property configuration
	 * @param in InputStream to the file
	 */
	public void setProperties(InputStream in){
		try {
				
			Properties properties = new Properties();
				
			FileFactory fac = FileFactory.newInstance();
			
			log.info("LOADING PROPERTIES FILE : ");
			properties.load(in);
			
			log.info("LOADED PROPERTIES FILE \n CONFIGURATIONS FOUND  : "+properties.size());
			
			String filePath = properties.getProperty("CandidateRepositoryFilePath");
			fac.setCandidateRepoFile(new File(filePath));
			
			
			filePath = properties.getProperty("TestRepositoryFilePath");
			fac.setTestRepoFile(new File(filePath));
			
			log.info("LOADING BEAN CONTEXT :");
			appContext = new AnnotationConfigApplicationContext(AppConfig.class);
			log.info("LOADED BEAN CONTEXT");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public CandidateService getCandidateService(){
		CandidateService service =  appContext.getBean("candidateService",CandidateService.class);
		return service;
	}
	
	public TestService getTestService(){
		return appContext.getBean("testService",TestService.class);
	}
	
}
