package br.com.faceZuck.config.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionGlobalHandler implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        var details = new ExceptionDetails();
        details.setStatus(String.valueOf(Response.Status.BAD_REQUEST.getStatusCode()));
        details.setMessage(exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST).entity(details).build();
    }
}
