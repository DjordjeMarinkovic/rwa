package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.UserEntity;


@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Integer> {

	@Override
	List<UserEntity> findAll(); 
}
