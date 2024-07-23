package br.com.faceZuck.controller;

import br.com.faceZuck.dto.form.LoginForm;
import br.com.faceZuck.service.login.LoginService;
import br.com.faceZuck.service.tokenAuthentication.JwtService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    LoginService loginService;

    @Inject
    JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginForm loginForm) {
        var user = loginService.validateLogin(loginForm);
        if (user != null) {
            String token = jwtService.generateToken(user.getUsername());
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
