package demo.dao.repository;

import demo.entities.TrainerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerEntityRepository extends CrudRepository<TrainerEntity, Integer> {
}