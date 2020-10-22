package rs.ac.ni.pmf.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.model.api.SubjectDTO;
import rs.ac.ni.pmf.web.model.entity.SubjectEntity;
import rs.ac.ni.pmf.web.model.entity.UserEntity;
import rs.ac.ni.pmf.web.model.mapper.SubjectsMapper;
import rs.ac.ni.pmf.web.repository.SubjectsRepository;
import rs.ac.ni.pmf.web.repository.UsersRepository;


@Service
@RequiredArgsConstructor
public class SubjectsService {

	private final SubjectsRepository subjectsRepository;
	private final UsersRepository usersRepository;
	
	private final SubjectsMapper subjectsMapper; 
	
	public List<SubjectDTO> getAllSubjects(){
		
		return subjectsRepository.findAll().stream().map(subjectsMapper::toDTO).collect(Collectors.toList());
	}
	
	public SubjectDTO addSubjects(final SubjectDTO subject)throws DuplicateResourceException, ResourceNotFoundException {
		
		final int user_id= subject.getProfessor_id();
		
		final UserEntity userEntity= usersRepository.findById(user_id).orElseThrow(
				()-> new ResourceNotFoundException(ResourceType.USER, "User with id:" + user_id + "not exists"));
		
		final SubjectEntity subjectEntity= subjectsMapper.toEntity(subject, userEntity);
		final SubjectEntity savedEntity = subjectsRepository.save(subjectEntity);
		
		return subjectsMapper.toDTO(savedEntity); 
	}
	
	public SubjectDTO getSubject(final int id) throws ResourceNotFoundException {
		
		final SubjectEntity subjectEntity= subjectsRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException(ResourceType.SUBJECT, "Subject withd id= " + id + "not found"));
		
		return subjectsMapper.toDTO(subjectEntity);
		
		
	}
	
	public void deleteSubject(final int id) throws ResourceNotFoundException {
		
		if(!subjectsRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.SUBJECT, "Subject witth id: '" + id + "' not exists");
		}
		
		subjectsRepository.deleteById(id);
	}
	
	public SubjectDTO updateSubject(final int id, final SubjectDTO subjectDTO) throws ResourceNotFoundException {
		
		final SubjectEntity subjectEntity= subjectsRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException(ResourceType.SUBJECT, "Subject with id: '" + id + "' not exists"));
		
		subjectEntity.setName(subjectDTO.getName());
		
		final SubjectEntity saveEntity= subjectsRepository.save(subjectEntity);
		
		return subjectsMapper.toDTO(saveEntity);
	}
	
	
}
