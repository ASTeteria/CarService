package javaspring.carservice.repository;

import javaspring.carservice.entity.Maintenance;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MaintenanceRepository extends MongoRepository<Maintenance, ObjectId> {
}
