package com.training.spring.repository;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.training.spring.model.Candidate;
import com.training.spring.model.Test;

public interface TestRepository {

	List<Test> getAllTests() throws JAXBException;
	
	boolean create(Test newTest) throws IOException, JAXBException;
	
	List<Test> getAllCandidateTests(String candidateId) throws JAXBException;
	
	Test search(String testId) throws JAXBException;

}