package org.money.transfer.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception e) {
		StringBuilder response = new StringBuilder("{");
		response.append("\"error\":\"Error\",");
		response.append("\"message\":\"" + e.getMessage() + "\"");
		response.append("}");
		return Response.serverError().entity(response.toString()).type(MediaType.APPLICATION_JSON).build();
	}
}