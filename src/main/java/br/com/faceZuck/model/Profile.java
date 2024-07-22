package br.com.faceZuck.model;

import br.com.faceZuck.Cadastro;
import br.com.faceZuck.model.enums.EducationLevelEnum;
import br.com.faceZuck.model.enums.MaritalStatusEnum;
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
	private String nome;
	private EducationLevelEnum escolaridade;
	private MaritalStatusEnum estadoCivil;
	private Address address;
	private String sobreMim;
	@OneToOne
	@JoinColumn(name = "idCadastro")
	private Cadastro cadastro;

}
