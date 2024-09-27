package org.nevent.festimania.domain.festival;


import org.nevent.festimania.domain.artista.Artista;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FestivalRepository extends MongoRepository<Festival, String> {
    // busca artista por id
    Artista findArtistaById(String id);
}
