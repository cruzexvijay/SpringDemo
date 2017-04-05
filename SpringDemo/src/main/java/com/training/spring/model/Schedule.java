package com.training.spring.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "schedule")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "Id", "candidateId", "Location","medium","dateTime","notes" })
@EqualsAndHashCode(exclude = { "Id" })
public @Data class Schedule {

	private String Id;
	private String candidateId;
	private String Location;
	private String dateTime;
	private String medium;
	private String notes;

}