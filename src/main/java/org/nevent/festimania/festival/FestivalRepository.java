package org.nevent.festimania.festival;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface FestivalRepository extends MongoRepository<Festival, Integer> {
}
