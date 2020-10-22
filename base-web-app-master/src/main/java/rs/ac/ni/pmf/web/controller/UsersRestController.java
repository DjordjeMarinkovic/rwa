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
import rs.ac.ni.pmf.web.model.api.UserDTO;


@RequestMapping(path="/users")
public interface UsersRestController {

	@RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	List<UserDTO> getAllUsers();
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	UserDTO getUser(@PathVariable(name = "id")int id) throws ResourceNotFoundException;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	UserDTO addUser(@RequestBody UserDTO user) throws DuplicateResourceException;
	
	@RequestMapping(method = RequestMethod.DELETE, path="/{id}")
	void deleteUser(@PathVariable(name="id")int id) throws ResourceNotFoundException;
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	UserDTO updateUser(@PathVariable(name = "id")int id, @RequestBody UserDTO user) throws ResourceNotFoundException;
}


