package demo.dao.repository;

import demo.entities.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileEntityRepository extends CrudRepository<ProfileEntity, Integer> {
}