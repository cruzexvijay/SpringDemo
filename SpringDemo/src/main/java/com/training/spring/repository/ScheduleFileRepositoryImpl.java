package com.training.spring.repository;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.spring.XmlUtil.XmlMarshallerUnmarshaller;
import com.training.spring.model.Schedule;
import com.training.spring.model.Schedules;
import com.training.spring.model.Test;

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

		Schedule s = searchSchedule(newSchedule);
		
		if(s!=null){
			//System.out.println("Object already exists. returning");
			return false;
		}

		if(newSchedule.getId()==null || newSchedule.getId()==""){
			newSchedule.setId(schedules.getRowId()+"");
		}

		schedules.addNewSchedule(newSchedule);
		
		return scheduleXmlMarshallerUnmarshaller.marshalObjectstoXml(schedules, Schedules.class, scheduleRepoFile);
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
	public Schedule searchSchedule(Schedule newSchedule) throws JAXBException{
		
		List<Schedule> scheduleList = findAllSchedules();
		
		Schedule result = null;
		
		if(scheduleList==null)
			return null;
		
		for(Schedule s : scheduleList){
			
			if(s.getId().equals(newSchedule.getId())||s.equals(newSchedule)){
				result = s;
				break;
			}
		}
		
		return result;
	}
	
	/*private boolean checkEquals(Schedule t, Schedule newSchedule) {
		boolean result = false;
		
		System.out.println("calling check equals");
		
		if(t!=null &&  newSchedule!=null){
			
			if(t.getCandidateId()!=null && newSchedule.getCandidateId()!=null){
				
				if(t.getCandidateId().equals(newSchedule.getCandidateId())){
					
					System.out.println("candidate Id id equal");
					if(t.getDate() != null && newSchedule.getDate() != null){
						
						int val = t.getDate().compareTo(newSchedule.getDate());
						
						if(val==0){
							
							System.out.println("date is equal");
							System.out.println(t.getDate().getTime());
							System.out.println(newSchedule.getDate().getTime());
							
							if(t.getLocation()!=null && newSchedule.getLocation()!=null){
								
								if(t.getLocation().equals(newSchedule.getLocation())){
									
									System.out.println("location is equal");
									result = true;
								}
								
							}
							
						}else{
							System.out.println("date is not equal"+val);
							System.out.println(t.getDate().getTime());
							System.out.println(newSchedule.getDate().getTime());
						}
						
					}
					
				}
				
			}
			
		}
		
		return result;
	}
*/
	
	/* (non-Javadoc)
	 * @see com.training.spring.repository.ScheduleFileRepository#findCandidateSchedules(java.lang.String)
	 */
	@Override
	public List<Schedule> findCandidateSchedules(String candidateId){
		
		List<Schedule> list = getAllSchedules();
		List<Schedule> newList = new ArrayList();
				
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
	public List<Schedule> filterSchedulesByDate(Date currentDate) throws ParseException{
		List<Schedule> list = getAllSchedules();
		
		if(list==null)
			return null;
	
		List<Schedule> filteredList = new ArrayList<Schedule>();
		
		Date d = null;
		
		for(Schedule s : list){
			
			d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").parse(s.getDateTime());
			if(d.equals(currentDate))
				filteredList.add(s);
			
		}
		
		return filteredList;			
	}
	
}
