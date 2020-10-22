package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.TypeOfUserDTO;
import rs.ac.ni.pmf.web.model.entity.TypeOfUserEntity;

@Component
public class TypesOfUsersMapper {

	public TypeOfUserDTO toDto(TypeOfUserEntity typeEntity) {
		
		return TypeOfUserDTO.builder()
				.id_user_type(typeEntity.getId_user_type())
				.name_type(typeEntity.getName_type())
				.build();
	}
	
	
public TypeOfUserEntity toEntity(TypeOfUserDTO typeDTO) {
		
		return TypeOfUserEntity.builder()
				.id_user_type(typeDTO.getId_user_type())
				.name_type(typeDTO.getName_type())
				.build();
	}
}
