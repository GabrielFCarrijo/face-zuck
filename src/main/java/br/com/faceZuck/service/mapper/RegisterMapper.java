package br.com.faceZuck.service.mapper;

import br.com.faceZuck.dto.RegisterDto;
import br.com.faceZuck.model.Register;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegisterMapper {

    public RegisterDto toDTO(Register register) {
        RegisterDto dto = new RegisterDto();
        dto.setName(register.getName());
        dto.setUsername(register.getUsername());
        dto.setBirthDate(String.valueOf(register.getBirthDate()));
        dto.setEmail(register.getEmail());
        return dto;
    }

}
