package demo.dao.repository;

import demo.entities.SessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionEntityRepository extends CrudRepository<SessionEntity, Integer> {
}