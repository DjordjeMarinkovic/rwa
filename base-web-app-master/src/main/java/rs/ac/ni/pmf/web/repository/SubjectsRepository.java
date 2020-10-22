package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import rs.ac.ni.pmf.web.model.entity.SubjectEntity;

public interface SubjectsRepository extends CrudRepository<SubjectEntity, Integer>, JpaSpecificationExecutor<SubjectEntity> {
	
	@Override
	List<SubjectEntity> findAll(); 
	
	
}
