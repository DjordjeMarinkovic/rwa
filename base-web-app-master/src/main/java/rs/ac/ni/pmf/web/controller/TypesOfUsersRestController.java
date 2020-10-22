package rs.ac.ni.pmf.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.TypeOfUserDTO;
import rs.ac.ni.pmf.web.model.api.UserDTO;

@RequestMapping(path = "/typesOfUsers")
public interface TypesOfUsersRestController {
	
	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	List<TypeOfUserDTO> getAllUsers();
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	TypeOfUserDTO getType(@PathVariable(name = "id")int id) throws ResourceNotFoundException;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	TypeOfUserDTO addType(@RequestBody TypeOfUserDTO type) throws DuplicateResourceException;
	
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	void deleteType(@PathVariable(name="id")int id) throws ResourceNotFoundException;
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	TypeOfUserDTO updateType(@PathVariable(name = "id")int id, @RequestBody UserDTO user) throws ResourceNotFoundException;

}
