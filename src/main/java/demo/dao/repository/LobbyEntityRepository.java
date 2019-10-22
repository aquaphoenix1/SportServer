package demo.dao.repository;

import demo.entities.LobbyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LobbyEntityRepository extends CrudRepository<LobbyEntity, Integer> {
}
