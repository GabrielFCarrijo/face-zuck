package br.com.faceZuck.controller;

import br.com.faceZuck.dto.form.LoginForm;
import br.com.faceZuck.service.login.LoginService;
import br.com.faceZuck.service.register.RegisterService;
import br.com.faceZuck.service.tokenAuthentication.GoogleTokenService;
import br.com.faceZuck.service.tokenAuthentication.JwtService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    LoginService loginService;
    @Inject
    JwtService jwtService;
    @Inject
    GoogleTokenService googleTokenService;
    @Inject
    RegisterService registerService;

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

    @Path("/google")
    @POST
    public Response loginGoogle(@QueryParam("idToken") String idToken) {
        try {
            GoogleIdToken.Payload payload = googleTokenService.getEmailFromGoogleToken(idToken);
            String email = payload.getEmail();
            boolean registerExists = registerService.checkIfRegisterExists(email);
            if (!registerExists) {
                registerService.registerUserGoogle(payload);
            }

            String token = jwtService.generateToken(email);

            return Response.ok(token).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error during authentication").build();
        }
    }
}
