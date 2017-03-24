package com.training.spring.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Tests")
@XmlRootElement(name="Tests")
@Getter 
public class Tests {

	private static Tests tests;
	
	@XmlElement(name="Test", required=true)
	private List<Test> testList = new LinkedList<Test>();
	
	private static int LAST_ROW_ID;
	
	private Tests(){ }
	
	public static Tests getInstance(){
		if(tests==null)
			tests = new Tests();
		
		return tests;
	}
	
	public int getRowId(){
		return ++LAST_ROW_ID;
	}
	
	public void addNewTest(Test newTest){
		String id = newTest.getTestId();
		if(id!=null){
			LAST_ROW_ID = Integer.parseInt(id);
			//System.out.println("last row id : "+LAST_ROW_ID);
		}
			
		this.testList.add(newTest);
	}
}
