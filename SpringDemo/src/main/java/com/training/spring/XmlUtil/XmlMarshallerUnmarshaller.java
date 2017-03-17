package com.training.spring.XmlUtil;

import java.io.File;

import javax.xml.bind.JAXBException;

public interface XmlMarshallerUnmarshaller<T> {

	/* (non-Javadoc)
	 * @see com.training.spring.XmlUtil.XmlMarshallerUnmarshaller#marshalObjectstoXml(java.lang.Object, java.lang.Class, java.io.File)
	 */
	boolean marshalObjectstoXml(Object obj, Class<T> clazz, File repoFile);

	/* (non-Javadoc)
	 * @see com.training.spring.XmlUtil.XmlMarshallerUnmarshaller#unmarshalXmltoObjects(java.lang.Class, java.io.File)
	 */
	Object unmarshalXmltoObjects(Class<T> clazz, File repoFile) throws JAXBException;

}