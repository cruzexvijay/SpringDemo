package com.training.spring.service;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.spring.model.Candidate;
import com.training.spring.model.Test;
import com.training.spring.repository.TestRepository;

public class TestServiceImpl implements TestService {
	
	/* (non-Javadoc)
	 * @see com.training.spring.service.TestService#getAllTests()
	 */
	
	@Autowired
	private TestRepository testRepository;
	
	@Override
	public List<Test> getAllTests() throws JAXBException{
		return testRepository.getAllTests();
	}

	@Override
	public boolean create(Test newTest) throws IOException, JAXBException {
		return testRepository.create(newTest);
	}

	@Override
	public List<Test> getAllCandidateTests(String candidateId) throws JAXBException {
		return testRepository.getAllCandidateTests(candidateId);
	}

	@Override
	public Test search(Test newTest) throws JAXBException {
		return testRepository.search(newTest);
	}

	@Override
	public Test findById(String testId) throws JAXBException {
		return testRepository.findById(testId);
	}
	
	

}
