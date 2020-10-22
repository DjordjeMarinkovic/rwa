package rs.ac.ni.pmf.web.repository.specification;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.ProjectsSearchOptions;
import rs.ac.ni.pmf.web.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.model.entity.ProjectEntity.Status;
import rs.ac.ni.pmf.web.model.entity.ProjectEntity_;

@RequiredArgsConstructor
public class ProjectsSearchSpecification implements Specification<ProjectEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ProjectsSearchOptions projectSearch;

	@Override
	public Predicate toPredicate(Root<ProjectEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		
		final List<Predicate> predicate = new ArrayList<>();
		
		final Path<String> title= root.get(ProjectEntity_.title);
		//final Path<LocalDate> start_date= root.get(ProjectEntity_.start_date);
		// Path<Status> status= root.get(ProjectEntity_.status);
		
		final String title_filter= projectSearch.getTitle_filter();
		
		if (title_filter != null && !title_filter.trim().isEmpty()) {
			predicate.add(criteriaBuilder.like(criteriaBuilder.lower(title), "%" + title_filter.toLowerCase() + "%"));
		}
		
		return criteriaBuilder.and(predicate.toArray(new Predicate[predicate.size()]));
	}

}
