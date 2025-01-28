package com.fontys.nameless_backend.Persistence.mongoDb;

import com.fontys.nameless_backend.Persistence.entities.event.MainEventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MainEventEntityRepository extends MongoRepository<MainEventEntity, String> {
}
