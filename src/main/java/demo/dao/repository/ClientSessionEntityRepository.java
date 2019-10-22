package demo.dao.repository;

import demo.entities.ClientSessionEntity;
import demo.entities.ClientSessionEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientSessionEntityRepository extends CrudRepository<ClientSessionEntity, ClientSessionEntityPK> {
}