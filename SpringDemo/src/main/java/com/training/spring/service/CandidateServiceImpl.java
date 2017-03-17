package com.training.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.spring.model.Candidate;
import com.training.spring.repository.CandidateRepository;
import com.training.spring.repository.TestRepository;

@Service("candidateService")
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private TestRepository testRepository;
			
	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		super();
		this.candidateRepository = candidateRepository;
	}
	
	public CandidateServiceImpl() {
		// TODO Auto-generated constructor stub
	}


	public void setCandidateRepository(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.training.spring.service.CandidateService#findAll()
	 */
	@Override
	public List<Candidate> findAll() throws JAXBException {
		return candidateRepository.findAll();
	}


	@Override
	public boolean create(Candidate candidate) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, JAXBException {
		return candidateRepository.create(candidate);
	}


	@Override
	public Candidate search(String candidateId) throws JAXBException {
		return candidateRepository.search(candidateId);
	}
}
