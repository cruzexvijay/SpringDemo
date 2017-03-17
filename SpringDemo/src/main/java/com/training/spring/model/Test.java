package com.training.spring.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name="test")
@XmlType(propOrder={"testId","candidateId","questionName","questionPath","testStartDate","feedBack","testStatus"})
public @Data class Test {
	
	public static final int PASS=1;
	public static final int FAIL=0;

	private String testId;
	private String candidateId;
	private String questionName;
	private String questionPath;
	private Date testStartDate;
	private String feedBack;
	private int testStatus;
	
	@Override
	public String toString() {

		String res = "\n\t\tTest: \n\t\t Id :"+testId+"\n\t\t Question Name : "+questionName+"\n\t\t";
		res += " Question Path : "+questionPath+"\n\t\t Start Date : "+testStartDate+"\n\t\t Result : "+(testStatus==PASS?"PASS":"FAIL")+"\n\t\t";
		res += " Feedback : "+feedBack+"\n\t\t";
		
		return res;
	}

	
	
}
