package br.com.faceZuck.service.register.registerValidation;

import br.com.faceZuck.config.exception.ValidationException;
import br.com.faceZuck.dto.form.RegisterForm;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ValidateRegisterUser {

    public void validateRegisterUser(RegisterForm registerForm) {
        List<String> errors = new ArrayList<>();

        if (registerForm.getName() == null || registerForm.getName().isEmpty()) {
            errors.add("Name must be informed");
        }
        if (registerForm.getUsername() == null || registerForm.getUsername().isEmpty()) {
            errors.add("Username must be informed");
        }
        if (registerForm.getBirthDate() == null || registerForm.getBirthDate().isEmpty()) {
            errors.add("Birth date must be informed");
        }
        if (registerForm.getEmail() == null || registerForm.getEmail().isEmpty()) {
            errors.add("Email must be informed");
        }
        if (registerForm.getPassword() == null || registerForm.getPassword().isEmpty()) {
            errors.add("Password must be informed");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}
