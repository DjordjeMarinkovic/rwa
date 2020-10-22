package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.TypesOfUsersRestController;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.TypeOfUserDTO;
import rs.ac.ni.pmf.web.model.api.UserDTO;
import rs.ac.ni.pmf.web.repository.TypesOfUsersRepository;
import rs.ac.ni.pmf.web.service.TypesOfUsersService;

@RestController
@RequiredArgsConstructor
public class TypesOfUsersRestControllerImpl implements TypesOfUsersRestController{

	private final TypesOfUsersService typeService;
	@Override
	public List<TypeOfUserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return typeService.getAllTypes();
	}

	@Override
	public TypeOfUserDTO getType(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeOfUserDTO addType(TypeOfUserDTO type) throws DuplicateResourceException {
		// TODO Auto-generated method stub
		return typeService.addType(type);
	}

	@Override
	public void deleteType(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TypeOfUserDTO updateType(int id, UserDTO user) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
