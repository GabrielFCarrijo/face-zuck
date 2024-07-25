package br.com.faceZuck.repository;

import br.com.faceZuck.model.Register;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegisterRepository implements PanacheRepository<Register> {

	public Register findByEmail(String email) {
		return find("email", email).firstResult();
	}
}
