package com.training.webservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AppException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8835306048671174368L;
	
	Integer status;
	Integer errorCode;
	String exceptionLink;
	String msg;
	
	

}
