package com.training.spring.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.spring.XmlUtil.XmlMarshallerUnmarshaller;
import com.training.spring.model.Schedule;
import com.training.spring.model.Schedules;

public class ScheduleFileRepositoryImpl implements ScheduleRepository {
	
	@Autowired
	private File scheduleRepoFile;
	
	private Schedules schedules = Schedules.getInstance();
	
	@Autowired
	private XmlMarshallerUnmarshaller<Schedules> scheduleXmlMarshallerUnmarshaller;
	
		
	/* (non-Javadoc)
	 * @see com.training.spring.repository.ScheduleFileRepository#create(com.training.spring.model.Schedule)
	 */
	@Override
	public boolean create(Schedule newSchedule) throws IOException, JAXBException{

		if(!scheduleRepoFile.exists())
			scheduleRepoFile.createNewFile();

		System.out.println("Createing new Schedule");
		System.out.println(newSchedule);
		
		if(newSchedule.getId()==null || newSchedule.getId()==""){
			//System.out.println("No id found; setting one");
			newSchedule.setId(schedules.getRowId()+"");
			//System.out.println(newSchedule.getId());
			
		}
		
		if(searchSchedule(newSchedule)!=null){
			//System.out.println("already exists");
			return false;
		}

		schedules.addNewSchedule(newSchedule);
		
		boolean val =  scheduleXmlMarshallerUnmarshaller.marshalObjectstoXml(schedules, Schedules.class, scheduleRepoFile);
		System.out.println("values : "+val);
		return val;
	}
	
	/* (non-Javadoc)
	 * @see com.training.spring.repository.ScheduleFileRepository#findAllSchedules()
	 */
	@Override
	public List<Schedule> findAllSchedules() throws JAXBException{
		return getAllSchedules();
	}
	
	private List<Schedule> getAllSchedules(){
		
		try {
			schedules = (Schedules) scheduleXmlMarshallerUnmarshaller.unmarshalXmltoObjects(Schedules.class, scheduleRepoFile);
		} catch (JAXBException e) {
			return null;
		}
		
		return schedules.getScheduleList();
	}
	
	/* (non-Javadoc)
	 * @see com.training.spring.repository.ScheduleFileRepository#findScheduleById(java.lang.String)
	 */
	@Override
	public Schedule findScheduleById(String scheduleId) throws JAXBException{
		return searchSchedule(Schedule.builder().Id(scheduleId).build());
	}
	
	@Override
	public Schedule searchSchedule(Schedule newSchedule){
		
		List<Schedule> list =  getAllSchedules();
		Schedule result = null;
		
		if(list==null){
			//System.out.println("empty list");
			return null;
		}
		
		for(Schedule s : list){
			if(s.getId().equals(newSchedule.getId()) || s.equals(newSchedule)){
				System.out.println("already exists");
				result = s;
				break;
			}		
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.training.spring.repository.ScheduleFileRepository#findCandidateSchedules(java.lang.String)
	 */
	@Override
	public List<Schedule> findCandidateSchedules(String candidateId){
		
		List<Schedule> list = getAllSchedules();
		List<Schedule> newList = new ArrayList();
		
		if(list==null)
			return null;
		
		for(Schedule s : list){
			if(s.getCandidateId().equals(candidateId))
				newList.add(s);
		}
		
		return newList;
	}
	
	/* (non-Javadoc)
	 * @see com.training.spring.repository.ScheduleFileRepository#filterSchedulesByDate(java.util.Date)
	 */
	@Override
	public List<Schedule> filterSchedulesByDate(Date currentDate){
		List<Schedule> list = getAllSchedules();
		
		if(list==null)
			return null;
	
		List<Schedule> filteredList = new ArrayList();
		
		for(Schedule s : list){
			if(s.getDate().equals(currentDate))
				filteredList.add(s);
			
		}
		
		return filteredList;			
	}
	
}
