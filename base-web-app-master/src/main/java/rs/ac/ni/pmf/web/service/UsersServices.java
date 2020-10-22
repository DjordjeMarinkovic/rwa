package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.UserDTO;
import rs.ac.ni.pmf.web.model.entity.UserEntity;
import rs.ac.ni.pmf.web.model.mapper.UsersMapper;
import rs.ac.ni.pmf.web.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersServices {
	
	private final UsersMapper usersMapper;
	private final UsersRepository usersRepository;
	
	public UserDTO addUser(final UserDTO userDto) throws DuplicateResourceException {
		final int id = userDto.getId();

		if( id == 0 || usersRepository.existsById(id)) {
			throw new DuplicateResourceException(ResourceType.USER, "User with this id: '" + id + "' already exists" );
		}

		final UserEntity userEntity = usersMapper.toEntity(userDto);
		final UserEntity savedEntity = usersRepository.save(userEntity);
		return usersMapper.toDto(savedEntity);
	}
	
	public List<UserDTO> getAllTUsers() {
		return usersRepository.findAll().stream().map(usersMapper::toDto).collect(Collectors.toList());
	}
	
	
	public UserDTO getUser(final int id)throws ResourceNotFoundException {
		final UserEntity userEntity= usersRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException(ResourceType.USER, "User with this id: '" + "'not exists"));
		return usersMapper.toDto(userEntity);
	}
	
	public void deleteUser(final int  id)throws ResourceNotFoundException {
		if(!usersRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.USER, "User with id: '" + id + "' not exists");
		}
		
		usersRepository.deleteById(id);
	}
	
	public UserDTO updateUser(final int id, final UserDTO userDTO)throws ResourceNotFoundException {
		
		/*if(userDTO.getIndex().equals(null) || !index.equals(userDTO.getIndex())) {
			
		}
		*/
		final UserEntity userEntity= usersRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException(ResourceType.USER, "User with id: '" + id + "' not exists"));
		
		userEntity.setIndex(userDTO.getIndex());
		userEntity.setName(userDTO.getName());
		userEntity.setSurname(userDTO.getSurmane());
		userEntity.setMail(userDTO.getMail());
		userEntity.setUsername(userDTO.getUsername());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setYear_of_study(userDTO.getYear_of_study());
		
		final UserEntity saveUser= usersRepository.save(userEntity);
		
		return usersMapper.toDto(saveUser);
	}

}
