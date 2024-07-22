package br.com.faceZuck.service.register;

import br.com.faceZuck.config.exception.ValidationException;
import br.com.faceZuck.dto.RegisterDto;
import br.com.faceZuck.dto.form.RegisterForm;
import br.com.faceZuck.model.Register;
import br.com.faceZuck.repository.RegisterRepository;
import br.com.faceZuck.repository.mapper.RegisterMapper;
import br.com.faceZuck.service.register.registerValidation.ValidateRegisterUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RegisterService {

    @Inject
    RegisterRepository registerRepository;
    @Inject
    ValidateRegisterUser validateRegisterUser;
    @Inject
    RegisterMapper registerMapper;

    public void registerUser(RegisterForm registerForm) {
        validateRegisterUser.validateRegisterUser(registerForm);

        Register register = new Register();
        register.setName(registerForm.getName());
        register.setUsername(registerForm.getUsername());
        register.setBirthDate(LocalDate.parse(registerForm.getBirthDate()));
        register.setEmail(registerForm.getEmail());
        register.setPassword(BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt()));

        registerRepository.persist(register);
    }

    public RegisterDto findRegister(Long id) {
        Register register = registerRepository.findById(id);
        if (register == null) {
            throw new ValidationException("User not found");
        }
        return registerMapper.toDTO(register);
    }

}
