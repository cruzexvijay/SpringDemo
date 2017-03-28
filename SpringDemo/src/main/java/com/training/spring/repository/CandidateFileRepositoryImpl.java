package com.training.spring.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.spring.Factory.FileFactory;
import com.training.spring.XmlUtil.XmlMarshallerUnmarshaller;
import com.training.spring.model.Candidate;
import com.training.spring.model.Candidates;

import lombok.NonNull;

@Repository("candidateRepository")
public class CandidateFileRepositoryImpl implements CandidateRepository {
	
	@Autowired
	private File candidateRepoFile;
	private Candidates candidates = Candidates.getInstance();
		
	/* (non-Javadoc)
	 * @see com.training.spring.repository.CandidateRepository#findAll()
	 */
	@Autowired
	private @NonNull TestRepository testRepository;
	
	@Autowired
	private @NonNull XmlMarshallerUnmarshaller<Candidates> candidatesXmlMarshallerUnmarshaller; 
	
	public CandidateFileRepositoryImpl(File candidateRepositoryFile) {
		candidateRepoFile = candidateRepositoryFile;
	}

	public CandidateFileRepositoryImpl() {
		//candidateRepoFile = FileFactory.newInstance().getCandidateRepoFile();
	}

	@Override
	public List<Candidate> findAll() throws JAXBException{
		return getCandidateListFromRepo();
	}

	private List<Candidate> getCandidateListFromRepo(){
		try {
			candidates = (Candidates) candidatesXmlMarshallerUnmarshaller.unmarshalXmltoObjects(Candidates.class,candidateRepoFile);
		} catch (JAXBException e) {
			return null;
		}
		return candidates.getCandidateList();
	}
	
	@Override
	public boolean create(Candidate newCandidate) throws IOException,JAXBException {
		
		if(!candidateRepoFile.exists())
			candidateRepoFile.createNewFile();
				
		if(search(newCandidate)!=null)
				return false;
		
		newCandidate.setCandidateId(candidates.getLAST_ROW_ID()+"");
		candidates.addNewCandidate(newCandidate);
				
		return candidatesXmlMarshallerUnmarshaller.marshalObjectstoXml(candidates, Candidates.class,candidateRepoFile);
	}

	@Override
	public Candidate search(Candidate newCandidate) throws JAXBException {
		
		Candidate result = null;
					
		List<Candidate> candidateList = findAll();
		
		if(candidateList==null)
			return null;
		
		for(Candidate c : candidateList){	
			if(c.getCandidateId().equals(newCandidate.getCandidateId()) || c.equals(newCandidate)){
				result = c;
				break;
			}	
		}
				
		return result;
	}

	@Override
	public Candidate search(String candidateId) throws JAXBException{
		
		Candidate result = null;
		
		List<Candidate> candidateList = findAll();
		
		if(candidateList==null)
			return null;
		
		for(Candidate c : candidateList){	
			if(c.getCandidateId().equals(candidateId)){
				result = c;
				break;
			}	
		}
				
		return result;
	}
	
	
	
}
