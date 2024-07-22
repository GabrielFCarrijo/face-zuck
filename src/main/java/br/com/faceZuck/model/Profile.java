package br.com.faceZuck.model;

import br.com.faceZuck.model.enums.EducationLevel;
import br.com.faceZuck.model.enums.MaritalStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private EducationLevel educationLevel;
	private MaritalStatus maritalStatus;
	private Address address;
	private String aboutMe;
	@OneToOne
	@JoinColumn(name = "idCadastro")
	private Register register;

}
