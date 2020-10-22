package rs.ac.ni.pmf.web.controller.impl;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.ProjectsRestController;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ProjectsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ProjectDTO;
import rs.ac.ni.pmf.web.service.ProjectsService;

@RestController
@RequiredArgsConstructor
public class ProjectsRestControllerImpl implements ProjectsRestController {
	
	final ProjectsService projectsService;

	@Override
	public Page<ProjectDTO> getAllProjectss(
			final String title_filter,
			final LocalDate start_date_filter,
			final Integer page,
			final Integer page_size) {
		// TODO Auto-generated method stub
		final ProjectsSearchOptions searchProject= ProjectsSearchOptions.builder()
				.title_filter(title_filter)
				.start_date_filter(start_date_filter)
				.page(page)
				.page_size(page_size)
				.build();
		
		return projectsService.getAllProjects(searchProject);
	}

	@Override
	public ProjectDTO getProject(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return projectsService.getProject(id);
	}

	@Override
	public ProjectDTO addProject(ProjectDTO project) throws DuplicateResourceException, ResourceNotFoundException, BadRequestException {
		// TODO Auto-generated method stub
		return projectsService.addProject(project);
	}

	@Override
	public void deleteProject(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		projectsService.deleteProject(id);
	}

	@Override
	public ProjectDTO updateProject(int id, ProjectDTO project) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return projectsService.upadateProject(id, project);
	}

	@Override
	public ProjectDTO updateProjectAsStudent(int id, ProjectDTO project) throws ResourceNotFoundException, BadRequestException {
		// TODO Auto-generated method stub
		return projectsService.upadateProjectStatusAsStudent(id, project);
	}

	@Override
	public ProjectDTO updateProjectAsProfessor(int id, ProjectDTO project)
			throws ResourceNotFoundException, BadRequestException {
		// TODO Auto-generated method stub
		return projectsService.upadateProjectStatusAsProfessor(id, project);
	}

}
