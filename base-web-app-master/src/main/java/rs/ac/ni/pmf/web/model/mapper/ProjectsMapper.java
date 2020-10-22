package rs.ac.ni.pmf.web.model.mapper;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.web.model.api.ProjectDTO;
import rs.ac.ni.pmf.web.model.api.SubjectDTO;
import rs.ac.ni.pmf.web.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.model.entity.ProjectEntity.Status;
import rs.ac.ni.pmf.web.model.entity.SubjectEntity;
import rs.ac.ni.pmf.web.model.entity.UserEntity;

@Component
public class ProjectsMapper {

	public ProjectDTO toDto(final ProjectEntity projectEntity) {
		//java.sql.Date dateData = projectEntity.getStart_date();
		return ProjectDTO.builder()
				.id(projectEntity.getId_project())
				.title(projectEntity.getTitle())
				.start_date(projectEntity.getStart_date())
				.final_date(projectEntity.getFinal_date())
				.number_of_bods(projectEntity.getNumber_of_bods())
				.final_grade(projectEntity.getFinal_grade())
				.final_comment(projectEntity.getFinal_comment())
				.subject_id(projectEntity.getSubject().getId_subject())
				.user_id(projectEntity.getUser().getUser_id())
				.status(projectEntity.getStatus().toValue())
				.build();
	}
	
	public ProjectEntity toEntity(final ProjectDTO projectDTO, final SubjectEntity subjectEntity, final UserEntity userEntity) {

		//java.sql.Date dateData = new java.sql.Date(projectDTO.getStart_date());
		return ProjectEntity.builder()
				.id_project(projectDTO.getId())
				.title(projectDTO.getTitle())
				.start_date(projectDTO.getStart_date())
				.final_date(projectDTO.getFinal_date())
				.number_of_bods(projectDTO.getNumber_of_bods())
				.final_grade(projectDTO.getFinal_grade())
				.final_comment(projectDTO.getFinal_comment())
				.subject(subjectEntity)
				.user(userEntity)
				.status(Status.values()[projectDTO.getStatus()])
				.build();
	}
	
	
}
