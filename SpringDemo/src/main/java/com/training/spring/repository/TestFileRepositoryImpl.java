package com.training.spring.repository;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.spring.XmlUtil.XmlMarshallerUnmarshaller;
import com.training.spring.model.Test;
import com.training.spring.model.Tests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
		
	//	System.out.println("testRepo :"+testRepoFile);
				
		if(!testRepoFile.exists())
			testRepoFile.createNewFile();
		
	//	System.out.println(newTest);
		
		if(newTest.getTestId()==null || newTest.getTestId()==""){
			newTest.setTestId(tests.getRowId()+""); //create a new row id
			//System.out.println(newTest.getTestId());
		}
		
		if(search(newTest)!=null){
			return false;
		}
						
		tests.addNewTest(newTest);
		
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
	public Test search(Test newTest) throws JAXBException {
		
		//System.out.println("search : "+newTest);
		
		List<Test> testList = getAllTests();
		
		Test result = null;
		
		if(testList==null)
			return null;
		
		for(Test t : testList){
			if(t.getTestId().equals(newTest.getTestId())||t.equals(newTest)){
				result = t;
				break;
			}
		}
		
		return result;
	}

	@Override
	public Test findById(String testId) throws JAXBException {
		return search(Test.builder().testId(testId).build());
		
	}
	
	
	
	
}
