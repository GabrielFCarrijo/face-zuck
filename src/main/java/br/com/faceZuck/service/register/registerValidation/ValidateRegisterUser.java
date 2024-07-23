package br.com.faceZuck.service.register.registerValidation;

import br.com.faceZuck.config.exception.ValidationException;
import br.com.faceZuck.dto.form.RegisterForm;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ValidateRegisterUser {

    public void validateRegisterUser(RegisterForm registerForm) {
        List<String> errors = new ArrayList<>();

        if (StringUtils.isBlank(registerForm.getName())) {
            errors.add("Name must be informed");
        }
        if (StringUtils.isBlank(registerForm.getUsername())) {
            errors.add("Username must be informed");
        }
        if (StringUtils.isBlank(registerForm.getBirthDate())) {
            errors.add("Birth date must be informed");
        }
        if (StringUtils.isBlank(registerForm.getEmail())) {
            errors.add("Email must be informed");
        }
        if (StringUtils.isBlank(registerForm.getPassword())) {
            errors.add("Password must be informed");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}
