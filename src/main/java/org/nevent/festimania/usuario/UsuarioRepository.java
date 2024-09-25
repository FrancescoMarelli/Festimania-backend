package org.nevent.festimania.usuario;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}
