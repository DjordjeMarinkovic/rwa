package rs.ac.ni.pmf.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.ProjectDTO;
import rs.ac.ni.pmf.web.model.api.SubjectDTO;


@RequestMapping(path="/subjects")
public interface SubjectsRestController {

	@RequestMapping(method=RequestMethod.GET, path="", produces = MediaType.APPLICATION_JSON_VALUE)
	List<SubjectDTO> getAllUsers();
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method=RequestMethod.POST, path="", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	SubjectDTO addSubject(@RequestBody SubjectDTO subject) throws DuplicateResourceException, ResourceNotFoundException;
	
	@RequestMapping(method=RequestMethod.GET, path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	SubjectDTO getSubject(@PathVariable(name="id") int id) throws ResourceNotFoundException;
	
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	void deleteSubject(@PathVariable(name="id")int id) throws ResourceNotFoundException;
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	SubjectDTO updateSubject(@PathVariable(name = "id")int id, @RequestBody SubjectDTO subject) throws ResourceNotFoundException;
}
