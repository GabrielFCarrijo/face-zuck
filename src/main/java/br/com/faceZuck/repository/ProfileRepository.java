package br.com.faceZuck.repository;

import br.com.faceZuck.model.Profile;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class ProfileRepository implements PanacheRepository<Profile> {
}
