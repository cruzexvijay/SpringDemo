package com.training.spring.config;
import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.training.spring.Factory.FileFactory;
import com.training.spring.XmlUtil.XmlMarshallerUnmarshaller;
import com.training.spring.XmlUtil.XmlMarshallerUnmarshallerImpl;
import com.training.spring.model.Candidates;
import com.training.spring.model.Schedules;
import com.training.spring.model.Tests;
import com.training.spring.repository.CandidateFileRepositoryImpl;
import com.training.spring.repository.CandidateRepository;
import com.training.spring.repository.ScheduleFileRepositoryImpl;
import com.training.spring.repository.ScheduleRepository;
import com.training.spring.repository.TestFileRepositoryImpl;
import com.training.spring.repository.TestRepository;
import com.training.spring.service.CandidateService;
import com.training.spring.service.CandidateServiceImpl;
import com.training.spring.service.ScheduleService;
import com.training.spring.service.ScheduleServiceImpl;
import com.training.spring.service.TestService;
import com.training.spring.service.TestServiceImpl;

@Configuration
@ComponentScan({"com.training.spring"})
public class AppConfig {
	
	/**
	 * Repository Configurations
	 * @return
	 */
	@Bean(name="candidateRepository")
	public CandidateRepository getCandidateRepository(){
		return new CandidateFileRepositoryImpl();
	}
	
	@Bean(name="testRepository")
	public TestRepository getTestRepository(){
		return new TestFileRepositoryImpl();
	}
	
	@Bean(name="scheduleRepository")
	public ScheduleRepository getScheduleRepository(){
		return new ScheduleFileRepositoryImpl();
	}
	
//---------------------------------------------------------------------------------
	
	/**
	 * Service Configurations
	 * @return
	 */
	@Bean(name="candidateService")
	public CandidateService getCandidateService(){
		return new CandidateServiceImpl();
	}
	
	@Bean(name="testService")
	public TestService getTestService(){
		return new TestServiceImpl();
	}
	
	@Bean(name="scheduleService")
	public ScheduleService getScheduleService(){
		return new ScheduleServiceImpl();
	}
	
//------------------------------------------------------------------------------------
	
	/**
	 * XML marshaller configurations
	 * @return
	 */
	@Bean(name="candidatesXmlMarshallerUnmarshaller")
	public XmlMarshallerUnmarshaller<Candidates> getCandidateXmlMarshallerUnmarshaller(){
		return new XmlMarshallerUnmarshallerImpl<>();
	}
	
	@Bean(name="testsXmlMarshallerUnmarshaller")
	public XmlMarshallerUnmarshaller<Tests> getTestsXmlMarshallerUnmarshaller(){
		return new XmlMarshallerUnmarshallerImpl<>();
	}
	
	@Bean(name="scheduleXmlMarshallerUnmarshaller")
	public XmlMarshallerUnmarshaller<Schedules> getScheduleXmlMarshallerUnmarshaller(){
		return new XmlMarshallerUnmarshallerImpl<>();
	}
	
//-------------------------------------------------------------------------------------------
	
	/**
	 * File Configurations
	 * @return
	 */
	@Bean(name="candidateRepoFile")
	public File getCandidateRepoFile(){
		return FileFactory.newInstance().getCandidateRepoFile();
	}
	
	@Bean(name="testRepoFile")
	public File getTestRepoFile(){
		return FileFactory.newInstance().getTestRepoFile();
	}
	
	@Bean(name="scheduleRepoFile")
	public File getScheduleRepoFile(){
		return FileFactory.newInstance().getScheduleRepoFile();
	}
}
