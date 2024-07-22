package br.com.faceZuck.dto.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
    private String name;
    private String username;
    private String birthDate;
    private String email;
    private String password;
}
