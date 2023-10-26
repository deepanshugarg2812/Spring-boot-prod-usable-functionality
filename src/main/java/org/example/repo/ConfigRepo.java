package org.example.repo;

import org.example.entity.ConfigMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepo extends MongoRepository<ConfigMaster, String> {

}
