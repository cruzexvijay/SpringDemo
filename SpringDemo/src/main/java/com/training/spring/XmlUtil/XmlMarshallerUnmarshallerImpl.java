package com.training.spring.XmlUtil;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class XmlMarshallerUnmarshallerImpl<T> implements XmlMarshallerUnmarshaller<T> {
	
	public XmlMarshallerUnmarshallerImpl(T t){
	}
	
	/* (non-Javadoc)
	 * @see com.training.spring.XmlUtil.XmlMarshallerUnmarshaller#marshalObjectstoXml(java.lang.Object, java.lang.Class, java.io.File)
	 */
	@Override
	public boolean marshalObjectstoXml(Object obj,Class<T> clazz,File repoFile){
		
		Object newObj = clazz.cast(obj); // convert the object instance into instance of the given class.
		
		try{
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(newObj,repoFile);
		}catch (JAXBException e) {
			System.out.println("Exception occured :"+e.getMessage());
			return false;
		}
		
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see com.training.spring.XmlUtil.XmlMarshallerUnmarshaller#unmarshalXmltoObjects(java.lang.Class, java.io.File)
	 */
	/* (non-Javadoc)
	 * @see com.training.spring.XmlUtil.XmlMarshallerUnmarshaller#unmarshalXmltoObjects(java.lang.Class, java.io.InputStream)
	 */
	@Override
	public Object unmarshalXmltoObjects(Class<T> clazz,File repoFile) throws JAXBException{
		
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return unmarshaller.unmarshal(repoFile);
	}
	
}
