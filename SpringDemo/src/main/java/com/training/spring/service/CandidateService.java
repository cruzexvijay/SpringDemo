package com.training.spring.service;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import com.training.spring.model.Candidate;

public interface CandidateService {

	List<Candidate> findAll() throws JAXBException;
	
	boolean create(Candidate candidate)throws IOException,ParserConfigurationException, TransformerConfigurationException, TransformerException, JAXBException;
	
	Candidate search(Candidate newCandidate) throws JAXBException;

	Candidate search(String candidateId)throws JAXBException;
}