package com.training.spring.repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.training.spring.model.Schedule;

public interface ScheduleRepository {

	boolean create(Schedule newSchedule) throws IOException, JAXBException;

	List<Schedule> findAllSchedules() throws JAXBException;

	List<Schedule> findCandidateSchedules(String candidateId);

	Schedule searchSchedule(Schedule newSchedule) throws JAXBException;

	Schedule findScheduleById(String scheduleId) throws JAXBException;

	List<Schedule> filterSchedulesByDate(long currentDate);

}