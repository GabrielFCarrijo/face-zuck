package br.com.faceZuck.service.register;

import br.com.faceZuck.config.exception.ValidationException;
import br.com.faceZuck.dto.RegisterDto;
import br.com.faceZuck.dto.form.RegisterForm;
import br.com.faceZuck.model.Register;
import br.com.faceZuck.repository.RegisterRepository;
import br.com.faceZuck.service.mapper.RegisterMapper;
import br.com.faceZuck.service.register.registerValidation.ValidateRegisterUser;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class RegisterService {

    @Inject
    RegisterRepository registerRepository;
    @Inject
    ValidateRegisterUser validateRegisterUser;
    @Inject
    RegisterMapper registerMapper;

    private static final String PATTERN_SENHA = "abcdefghijklmnopqrstuvxz1234567890.:_-]}{[&*)(%$@!|^~";
    private static final String REGEX = "^(?=.*[.:_&*)(%$@!|^~])(?=.*[\\d])(?=.*[a-zA-Z])[@!#$%^&*()\\[\\]{}/a-zA-Z0-9]{8,20}$";
    private static final Integer TAMANHO_SENHA = 16;

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

    public void registerUserGoogle(GoogleIdToken.Payload payload) {
        var registerForm = RegisterForm.builder()
                .name((String) payload.get("name"))
                .email(payload.getEmail())
                .password(generateRandomPassword())
                .build();

        registerUser(registerForm);
    }

    public boolean checkIfRegisterExists(String email) {
        var register = registerRepository.findByEmail(email);
        return register != null;
    }

    private String generateRandomPassword() {
        var random = RandomStringUtils.random(TAMANHO_SENHA, PATTERN_SENHA.toCharArray());
        while (!validatePassword(random)) {
            random = RandomStringUtils.random(TAMANHO_SENHA, PATTERN_SENHA.toCharArray());
        }
        return random;
    }

    private boolean validatePassword(String random) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(random);
        return m.matches();
    }

}
