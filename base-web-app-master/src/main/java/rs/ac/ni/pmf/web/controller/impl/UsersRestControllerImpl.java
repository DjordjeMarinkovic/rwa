package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.UsersRestController;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.UserDTO;
import rs.ac.ni.pmf.web.service.UsersServices;


@RestController
@RequiredArgsConstructor
public class UsersRestControllerImpl implements UsersRestController {
	
	private final UsersServices userService;

	@Override
	public UserDTO addUser(UserDTO user)throws DuplicateResourceException {
		// TODO Auto-generated method stub
		return userService.addUser(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return userService.getAllTUsers();
	}

	@Override
	public UserDTO getUser(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return userService.getUser(id);
	}

	@Override
	public void deleteUser(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		userService.deleteUser(id);
	}

	@Override
	public UserDTO updateUser(int id, UserDTO user) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return userService.updateUser(id, user);
	}
	
}
