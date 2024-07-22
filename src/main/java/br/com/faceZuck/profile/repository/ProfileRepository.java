package br.com.faceZuck.profile.repository;

import br.com.faceZuck.profile.model.Profile;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class ProfileRepository implements PanacheRepository<Profile> {
}
