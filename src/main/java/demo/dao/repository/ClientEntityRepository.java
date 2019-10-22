package demo.dao.repository;

import demo.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientEntityRepository extends CrudRepository<ClientEntity, String> {
}
