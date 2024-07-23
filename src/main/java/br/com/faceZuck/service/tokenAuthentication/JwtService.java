package br.com.faceZuck.service.tokenAuthentication;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class JwtService {

    public String generateToken(String username) {
        Set<String> groups = new HashSet<>();
        groups.add("User");

        return Jwt.issuer("face-zuck")
                .upn(username)
                .groups(groups)
                .sign();
    }
}
