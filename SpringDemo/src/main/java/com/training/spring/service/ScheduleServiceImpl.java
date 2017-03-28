package com.training.spring.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.spring.model.Schedule;
import com.training.spring.repository.ScheduleRepository;

public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Override
	public boolean create(Schedule newSchedule) throws IOException, JAXBException {
		return scheduleRepository.create(newSchedule);
	}

	@Override
	public List<Schedule> findAllSchedules() throws JAXBException {
		return scheduleRepository.findAllSchedules();
	}

	@Override
	public Schedule findScheduleById(String scheduleId) throws JAXBException {
		return scheduleRepository.findScheduleById(scheduleId);
	}

	@Override
	public List<Schedule> findCandidateSchedules(String candidateId) {
		return scheduleRepository.findCandidateSchedules(candidateId);
	}

	@Override
	public List<Schedule> filterSchedulesByDate(Date currentDate) throws ParseException {
		return scheduleRepository.filterSchedulesByDate(currentDate);
	}

}
