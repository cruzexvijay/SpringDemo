package com.training.webservice.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.sun.jersey.spi.resource.Singleton;

@Provider
@Singleton
public class AppExceptionMapper implements ExceptionMapper<AppException> {

	@Override
	public Response toResponse(AppException exception) {
		
		return Response.status(exception.getStatus())
				.entity(new Gson().toJson(exception))
				.type(MediaType.APPLICATION_JSON).build();
	}

}
