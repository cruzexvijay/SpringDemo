package com.training.spring.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="schedule")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"Id","candidateId","Location","dateTime"})
@EqualsAndHashCode(exclude={"Id"})
public @Data class Schedule {

	private String Id;
	private String candidateId;
	private String Location;
	private String dateTime;
	
}
