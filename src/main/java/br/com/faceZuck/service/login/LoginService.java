package br.com.faceZuck.service.login;

import br.com.faceZuck.dto.form.LoginForm;
import br.com.faceZuck.model.Register;
import br.com.faceZuck.repository.RegisterRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class LoginService {

    @Inject
    RegisterRepository registerRepository;

    public Register validateLogin(LoginForm loginForm) {
        Register user = registerRepository.find("username", loginForm.getUsername()).firstResult();
        if (user != null && BCrypt.checkpw(loginForm.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

}
