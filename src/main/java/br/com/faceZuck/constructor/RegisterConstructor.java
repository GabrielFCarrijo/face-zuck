package br.com.faceZuck.constructor;

import br.com.faceZuck.dto.form.RegisterForm;
import br.com.faceZuck.service.register.RegisterService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/register")
public class RegisterConstructor {

    @Inject
    RegisterService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegisterForm registerForm) {
        service.registerUser(registerForm);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Long id) {
        var userDto = service.findRegister(id);
        return Response.ok(userDto).build();
    }
}
