package com.training.spring.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.training.spring.model.Schedule;

public interface ScheduleService {

	boolean create(Schedule newSchedule) throws IOException, JAXBException;

	List<Schedule> findAllSchedules() throws JAXBException;

	Schedule findScheduleById(String scheduleId) throws JAXBException;

	List<Schedule> findCandidateSchedules(String candidateId);

	List<Schedule> filterSchedulesByDate(Date currentDate) throws ParseException;

}