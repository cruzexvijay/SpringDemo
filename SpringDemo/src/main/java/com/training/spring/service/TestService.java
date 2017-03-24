package com.training.spring.service;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.training.spring.model.Candidate;
import com.training.spring.model.Test;

public interface TestService {

	List<Test> getAllTests() throws JAXBException;
	
	boolean create(Test newTest) throws IOException, JAXBException;
	
	List<Test> getAllCandidateTests(String candidateId) throws JAXBException;
	
	Test search(Test newTest) throws JAXBException;

	Test findById(String testId) throws JAXBException;
}