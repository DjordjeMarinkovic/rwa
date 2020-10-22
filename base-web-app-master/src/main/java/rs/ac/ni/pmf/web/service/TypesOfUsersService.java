package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.model.api.TypeOfUserDTO;
import rs.ac.ni.pmf.web.model.api.UserDTO;
import rs.ac.ni.pmf.web.model.entity.TypeOfUserEntity;
import rs.ac.ni.pmf.web.model.entity.UserEntity;
import rs.ac.ni.pmf.web.model.mapper.TypesOfUsersMapper;
import rs.ac.ni.pmf.web.repository.TypesOfUsersRepository;

@Service
@RequiredArgsConstructor
public class TypesOfUsersService {

	private final TypesOfUsersRepository typeRepository;
	
	private final TypesOfUsersMapper typeMapper;
	
	
	public TypeOfUserDTO addType(final TypeOfUserDTO typeDto) throws DuplicateResourceException {
		final int id = typeDto.getId_user_type();

		if( id == 0 || typeRepository.existsById(id)) {
			throw new DuplicateResourceException(ResourceType.TYPEOFUSER, "Type with this id: '" + id + "' already exists" );
		}

		final TypeOfUserEntity typeEntity = typeMapper.toEntity(typeDto);
		final TypeOfUserEntity savedEntity = typeRepository.save(typeEntity);
		return typeMapper.toDto(savedEntity);
	}
	
	public List<TypeOfUserDTO> getAllTypes() {
		return typeRepository.findAll().stream().map(typeMapper::toDto).collect(Collectors.toList());
	}
	
}
