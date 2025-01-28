package com.fontys.nameless_backend.Persistence.mongoDb;

import com.fontys.nameless_backend.Persistence.entities.layout.BoxListEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BoxListRepository extends MongoRepository<BoxListEntity, Integer> {
    @Query(sort = "{id: -1}")
    BoxListEntity findTopByOrderByIdDesc();
}
