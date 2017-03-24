package com.training.spring.model;

import java.util.Date;

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
@EqualsAndHashCode(exclude={"Id"})
@XmlRootElement(name="schedule")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"Id","candidateId","Location","date"})
public @Data class Schedule {

	private String Id;
	private String candidateId;
	private String Location;
	private Date date;
	
	@Override
	public String toString() {
		return "Schedule [Id=" + Id + ", candidateId=" + candidateId + ", Location=" + Location + ", date=" + date
				+ "]";
	}
	
	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (Location == null) {
			if (other.Location != null)
				return false;
		} else if (!Location.equals(other.Location))
			return false;
		if (TestId == null) {
			if (other.TestId != null)
				return false;
		} else if (!TestId.equals(other.TestId))
			return false;
		if (candidateId == null) {
			if (other.candidateId != null)
				return false;
		} else if (!candidateId.equals(other.candidateId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Location == null) ? 0 : Location.hashCode());
		result = prime * result + ((TestId == null) ? 0 : TestId.hashCode());
		result = prime * result + ((candidateId == null) ? 0 : candidateId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}
	*/
	
	
	
}
