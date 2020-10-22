package rs.ac.ni.pmf.web.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.ProjectDTO;

@RequestMapping(path = "projects")
public interface ProjectsRestController {
	
	//@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	Page<ProjectDTO> getAllProjectss(
			@RequestParam(name = "title", required = false)final String title_filter,
			@RequestParam(name = "start_date", required = false)final LocalDate start_date_filter,
			@RequestParam(name = "page", required = false)final Integer page,
			@RequestParam(name = "page_size", required = false)final Integer page_size);
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ProjectDTO getProject(@PathVariable(name = "id")int id) throws ResourceNotFoundException;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ProjectDTO addProject(@RequestBody ProjectDTO project) throws DuplicateResourceException, ResourceNotFoundException, BadRequestException;
	
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	void deleteProject(@PathVariable(name="id")int id) throws ResourceNotFoundException;
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ProjectDTO updateProject(@PathVariable(name = "id")int id, @RequestBody ProjectDTO project) throws ResourceNotFoundException;
	
	@RequestMapping(method = RequestMethod.PUT, path = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ProjectDTO updateProjectAsStudent(@PathVariable(name = "id")int id, @RequestBody ProjectDTO project) throws ResourceNotFoundException, BadRequestException;
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/professor", produces = MediaType.APPLICATION_JSON_VALUE)
	ProjectDTO updateProjectAsProfessor(@PathVariable(name = "id")int id, @RequestBody ProjectDTO project) throws ResourceNotFoundException, BadRequestException;

}
