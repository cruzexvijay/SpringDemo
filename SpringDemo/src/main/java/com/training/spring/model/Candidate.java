package com.training.spring.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name="candidate")
@XmlType(propOrder = {"candidateId","firstName","lastName","emailId","contactNumber"})
public @Data class Candidate {

	private @NonNull String candidateId;
	private @NonNull String firstName;
	private @NonNull String lastName;
	private @NonNull String emailId;
	private @NonNull String contactNumber;
	
	//public List<Test> testList=new LinkedList();
	
	@Override
	public String toString() {
		
		String res = "Candidate: \n\t Id :"+candidateId+"\n\t First Name : "+firstName+"\n\t";
		res += " Last Name : "+lastName+"\n\t Email Id : "+emailId+"\n\t Contact Number : "+contactNumber+"\n\t";
		
		return res;
	}
	
	
	
}
