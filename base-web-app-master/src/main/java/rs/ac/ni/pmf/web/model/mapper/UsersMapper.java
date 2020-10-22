package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.UserDTO;
import rs.ac.ni.pmf.web.model.entity.TypeOfUserEntity;
import rs.ac.ni.pmf.web.model.entity.UserEntity;

@Component
public class UsersMapper {

	public UserDTO toDto(final UserEntity userEntity) {
		
		return UserDTO.builder()
				.id(userEntity.getUser_id())
				.index(userEntity.getIndex())
				.name(userEntity.getName())
				.surmane(userEntity.getSurname())
				.mail(userEntity.getMail())
				.username(userEntity.getUsername())
				.password(userEntity.getPassword())
				.year_of_study(userEntity.getYear_of_study())
				.type_user(userEntity.getType_user().getId_user_type())
				.build();
	}
	
public UserEntity toEntity(final UserDTO userDTO, final TypeOfUserEntity typeEntity) {
		
		return UserEntity.builder()
				.user_id(userDTO.getId())
				.index(userDTO.getIndex())
				.name(userDTO.getName())
				.surname(userDTO.getSurmane())
				.mail(userDTO.getMail())
				.username(userDTO.getUsername())
				.password(userDTO.getPassword())
				.year_of_study(userDTO.getYear_of_study())
				.type_user(typeEntity)
				.build();
	}
}
