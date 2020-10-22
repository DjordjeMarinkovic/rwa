package rs.ac.ni.pmf.web.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.controller.SubjectsRestController;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.api.SubjectDTO;
import rs.ac.ni.pmf.web.service.SubjectsService;

@RestController
@RequiredArgsConstructor
public class SubjectsRestControllerImpl implements SubjectsRestController {

	private final SubjectsService subjectsService;
	
	@Override
	public List<SubjectDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return subjectsService.getAllSubjects();
	}

	@Override
	public SubjectDTO addSubject(SubjectDTO subject)throws DuplicateResourceException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		return subjectsService.addSubjects(subject);
	}

	@Override
	public SubjectDTO getSubject(final int id)throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return subjectsService.getSubject(id);
	}

	@Override
	public void deleteSubject(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		subjectsService.deleteSubject(id);
	}

	@Override
	public SubjectDTO updateSubject(int id, SubjectDTO subject) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return subjectsService.updateSubject(id, subject);
	}

}
