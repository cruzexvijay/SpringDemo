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
@Setter
public class Tests {

	private static Tests tests;
	
	@XmlElement(name="Test", required=true)
	private List<Test> testList = new LinkedList<Test>();
	
	private Tests(){ }
	
	public static Tests getInstance(){
		if(tests==null)
			tests = new Tests();
		
		return tests;
	}
	
}
