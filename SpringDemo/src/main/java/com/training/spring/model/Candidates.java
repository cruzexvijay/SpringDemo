package com.training.spring.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Candidates")
@XmlRootElement(name="Candidates")
@Getter 
@Setter
public class Candidates {
	
	private static Candidates candidates;
	
	//@XmlElementWrapper(name="Candidate")
	@XmlElement(name="Candidate", required=true)
	private List<Candidate> candidateList=new LinkedList<>();
	
	
	private int LAST_ROW_ID;
	
	private Candidates(){
		
	}
	
	public static Candidates getInstance(){
		
		if(candidates==null)
			candidates =  new Candidates();
		
		return candidates;	
	}
	
	public int getRowId(){
		return ++this.LAST_ROW_ID;
	}
	
	public void addNewCandidate(Candidate newCandidate){
		if(newCandidate.getCandidateId()!=null){
			this.LAST_ROW_ID = Integer.parseInt(newCandidate.getCandidateId());
		}
		this.candidateList.add(newCandidate);
	}

}
