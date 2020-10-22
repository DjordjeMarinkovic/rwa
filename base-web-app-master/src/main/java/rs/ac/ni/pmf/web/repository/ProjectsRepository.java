package rs.ac.ni.pmf.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import rs.ac.ni.pmf.web.model.entity.ProjectEntity;

@Repository
public interface ProjectsRepository extends PagingAndSortingRepository<ProjectEntity, Integer>, JpaSpecificationExecutor<ProjectEntity> {

	@Override
	List<ProjectEntity> findAll();
}
