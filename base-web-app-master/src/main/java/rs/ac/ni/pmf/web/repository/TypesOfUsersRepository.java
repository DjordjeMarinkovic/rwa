package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.web.model.entity.TypeOfUserEntity;

@Repository
public interface TypesOfUsersRepository extends CrudRepository<TypeOfUserEntity, Integer> {

	@Override
	List<TypeOfUserEntity> findAll();
}
