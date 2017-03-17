package com.training.spring.repository;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.spring.Factory.ApplicationFactory;
import com.training.spring.Factory.FileFactory;
import com.training.spring.XmlUtil.XmlMarshallerUnmarshaller;
import com.training.spring.model.Candidate;
import com.training.spring.model.Test;
import com.training.spring.model.Tests;

public class TestFileRepositoryImpl implements TestRepository {
	
	@Autowired
	private XmlMarshallerUnmarshaller<Tests> testsXmlMarshallerUnmarshaller;
	
	private Tests tests = Tests.getInstance();

	@Autowired
	private File testRepoFile;
	
	public TestFileRepositoryImpl(){
		
	}
	
	public TestFileRepositoryImpl(File testRepoFile2) {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.training.spring.repository.TestRepository#getAllTests()
	 */
	@Override
	public List<Test> getAllTests() throws JAXBException{
		return getTestListFromRepo();
	}

	private List<Test> getTestListFromRepo() {
		try {
			tests = (Tests) testsXmlMarshallerUnmarshaller.unmarshalXmltoObjects(Tests.class, testRepoFile);
		} catch (JAXBException e) {
			return null;
		}
		
		return tests.getTestList();
	}

	@Override
	public boolean create(Test newTest) throws IOException, JAXBException {
		
		System.out.println("testRepo :"+testRepoFile);
		
		if(!testRepoFile.exists())
			testRepoFile.createNewFile();
		
		if(search(newTest.getTestId())!=null)
			return false;
		
		tests.getTestList().add(newTest);
		
		return testsXmlMarshallerUnmarshaller.marshalObjectstoXml(tests, Tests.class,testRepoFile);
	}


	@Override
	public List<Test> getAllCandidateTests(String candidateId) throws JAXBException {
		
		List<Test> allTests = this.getAllTests();
		List<Test> candidateTestList = new LinkedList();
		
		for(Test test : allTests){
			
			if(test.getCandidateId().equals(candidateId))
				candidateTestList.add(test);
			
		}
		
		return candidateTestList;
	}

	@Override
	public Test search(String testId) throws JAXBException {
		
		List<Test> testList = getAllTests();
		
		Test result = null;
		
		if(testList==null)
			return null;
		
		for(Test t : testList){
			if(t.getTestId().equals(testId)){
				result = t;
				break;
			}
		}
		
		return result;
	}
	
	
	
}
