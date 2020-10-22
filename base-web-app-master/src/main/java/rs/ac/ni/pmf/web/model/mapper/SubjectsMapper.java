package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.SubjectDTO;
import rs.ac.ni.pmf.web.model.entity.SubjectEntity;
import rs.ac.ni.pmf.web.model.entity.UserEntity;

@Component
public class SubjectsMapper {

	public SubjectDTO toDTO(final SubjectEntity subjectEntity) {
		return SubjectDTO.builder()
				.id_subject(subjectEntity.getId_subject())
				.name(subjectEntity.getName())
				.professor_id(subjectEntity.getUserProfessor().getUser_id())
				.build();
	}
	
	public SubjectEntity toEntity(final SubjectDTO subjectDTO, final UserEntity userEntity) {
		return SubjectEntity.builder()
				.id_subject(subjectDTO.getId_subject())
				.name(subjectDTO.getName())
				.userProfessor(userEntity)
				.build();
	}
}
