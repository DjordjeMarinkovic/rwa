package rs.ac.ni.pmf.web.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;
import rs.ac.ni.pmf.web.exception.BadRequestException;
import rs.ac.ni.pmf.web.exception.DuplicateResourceException;
import rs.ac.ni.pmf.web.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.model.ProjectsSearchOptions;
import rs.ac.ni.pmf.web.model.api.ProjectDTO;
import rs.ac.ni.pmf.web.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.model.entity.ProjectEntity.Status;
import rs.ac.ni.pmf.web.model.entity.SubjectEntity;
import rs.ac.ni.pmf.web.model.entity.UserEntity;
import rs.ac.ni.pmf.web.model.mapper.ProjectsMapper;
import rs.ac.ni.pmf.web.repository.ProjectsRepository;
import rs.ac.ni.pmf.web.repository.SubjectsRepository;
import rs.ac.ni.pmf.web.repository.UsersRepository;
import rs.ac.ni.pmf.web.repository.specification.ProjectsSearchSpecification;

@Service
@RequiredArgsConstructor
public class ProjectsService {

	private static final Integer DEFAULT_PAGE_SIZE = 20;

	private final ProjectsRepository projectsRepository;
	private final SubjectsRepository subjectsRepository;
	private final UsersRepository userRepository;

	private final ProjectsMapper projectsMapper;

	@PersistenceContext
	private EntityManager entityManager;

	public Page<ProjectDTO> getAllProjects(final ProjectsSearchOptions searchProject) {

		final PageRequest pageRequest;

		if (searchProject.getPage() != null) {
			pageRequest = PageRequest.of(searchProject.getPage(),
					searchProject.getPage_size() != null ? searchProject.getPage_size() : DEFAULT_PAGE_SIZE);
		} else {
			pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
		}

		return projectsRepository.findAll(new ProjectsSearchSpecification(searchProject), pageRequest)
				.map(projectsMapper::toDto);
	}

	public ProjectDTO getProject(final int id) throws ResourceNotFoundException {
		final ProjectEntity projectEntity = projectsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.PROJECT, "Project with id: '" + id + "' not exists"));

		return projectsMapper.toDto(projectEntity);
	}

	public ProjectDTO addProject(final ProjectDTO projectDTO)
			throws DuplicateResourceException, ResourceNotFoundException, BadRequestException {

		// proveri
		final int subject_id = projectDTO.getSubject_id();
		final Integer user_id = projectDTO.getUser_id();
		final int id = projectDTO.getId();

		final UserEntity userEntity;

		if (projectDTO.getStatus() > 0) {
			throw new BadRequestException(ResourceType.PROJECT, "Status must be 0");
		}

		if (id == 0 || projectsRepository.existsById(id)) {
			throw new DuplicateResourceException(ResourceType.PROJECT, "Project with id: '" + id + "' already exists");
		}

		final SubjectEntity subjectEntity = subjectsRepository.findById(subject_id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.SUBJECT,
						"Subject with id: '" + subject_id + "' not exists"));

		if (user_id != 0) {
			userEntity = userRepository.findById(user_id)
					.orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER,
							"User with id:'" + user_id + "' not exists"));
		} else {
			userEntity = null;
			;
		}

		final ProjectEntity projectEntity = projectsMapper.toEntity(projectDTO, subjectEntity, userEntity);
		final ProjectEntity saveProject = projectsRepository.save(projectEntity);

		return projectsMapper.toDto(saveProject);
	}

	public void deleteProject(final int id) throws ResourceNotFoundException {

		if (!projectsRepository.existsById(id)) {
			throw new ResourceNotFoundException(ResourceType.PROJECT, "Project with id: '" + id + "' not exists");
		}

		projectsRepository.deleteById(id);
	}

	public ProjectDTO upadateProject(final int id, final ProjectDTO projectDTO) throws ResourceNotFoundException {

		final ProjectEntity projectEntity = projectsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.PROJECT, "Project with id: '" + id + "' not exists"));

		final UserEntity userEntity = userRepository.findById(projectDTO.getUser_id())
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER,
						"User with id: '" + projectDTO.getUser_id() + "' not exists"));

		projectEntity.setTitle(projectDTO.getTitle());
		projectEntity.setStart_date(projectDTO.getStart_date());
		projectEntity.setFinal_date(projectDTO.getFinal_date());
		projectEntity.setNumber_of_bods(projectDTO.getNumber_of_bods());
		projectEntity.setFinal_grade(projectDTO.getFinal_grade());
		projectEntity.setFinal_comment(projectDTO.getFinal_comment());
		projectEntity.setStatus(Status.values()[projectDTO.getStatus()]);
		projectEntity.setUser(userEntity);

		final ProjectEntity saveEntity = projectsRepository.save(projectEntity);

		return projectsMapper.toDto(saveEntity);

	}

	public ProjectDTO upadateProjectStatusAsStudent(final int id, final ProjectDTO projectDTO)
			throws ResourceNotFoundException, BadRequestException {

		final ProjectEntity projectEntity = projectsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.PROJECT, "Project with id: '" + id + "' not exists"));

		final UserEntity userEntity = userRepository.findById(projectDTO.getUser_id())
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER,
						"User with id: '" + projectDTO.getUser_id() + "' not exists"));

		Status changeToStatus = Status.values()[projectDTO.getStatus()];
		if (changeToStatus == Status.BUSY || changeToStatus == Status.COMPLETED
				|| changeToStatus == Status.CURRENTLY_UNAVAILABLE) {
			if (projectEntity.getStatus() == Status.AVAILABLE) {
				if (changeToStatus == Status.BUSY) {
					projectEntity.setStatus(Status.BUSY);
				} else {
					throw new BadRequestException(ResourceType.PROJECT, "Project not available");
				}
			} else if (projectEntity.getStatus() == Status.IN_THE_PRODACTION) {
				if (changeToStatus == Status.CURRENTLY_UNAVAILABLE) {
					projectEntity.setStatus(Status.CURRENTLY_UNAVAILABLE);
				} else if (changeToStatus == Status.COMPLETED) {
					projectEntity.setStatus(Status.COMPLETED);
				} else {
					throw new BadRequestException(ResourceType.PROJECT, "Project not in production");
				}
			} else {
				throw new BadRequestException(ResourceType.PROJECT, "You can't change status");
			}

		} else {
			throw new BadRequestException(ResourceType.PROJECT, "Your status is incorrect");
		}
		projectEntity.setTitle(projectEntity.getTitle());
		projectEntity.setStart_date(projectEntity.getStart_date());
		projectEntity.setNumber_of_bods(projectEntity.getNumber_of_bods());
		projectEntity.setFinal_grade(projectEntity.getFinal_grade());
		projectEntity.setFinal_comment(projectEntity.getFinal_comment());
		projectEntity.setUser(userEntity);

		final ProjectEntity saveEntity = projectsRepository.save(projectEntity);

		return projectsMapper.toDto(saveEntity);

	}
	
	
	public ProjectDTO upadateProjectStatusAsProfessor(final int id, final ProjectDTO projectDTO)
			throws ResourceNotFoundException, BadRequestException {

		final ProjectEntity projectEntity = projectsRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.PROJECT, "Project with id: '" + id + "' not exists"));

		final UserEntity userEntity = userRepository.findById(projectDTO.getUser_id())
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.USER,
						"User with id: '" + projectDTO.getUser_id() + "' not exists"));

		Status changeToStatus = Status.values()[projectDTO.getStatus()];
		
		
		if (changeToStatus == Status.IN_THE_PRODACTION || changeToStatus == Status.RATED
				|| changeToStatus == Status.AVAILABLE) {
			if (projectEntity.getStatus() == Status.BUSY) {
				if (changeToStatus == Status.IN_THE_PRODACTION) {
					projectEntity.setStatus(Status.IN_THE_PRODACTION);
				}else if(changeToStatus == Status.AVAILABLE){
					projectEntity.setStatus(Status.AVAILABLE);
				}
				else {
					throw new BadRequestException(ResourceType.PROJECT, "You sent wrong status");
				}
			} else if (projectEntity.getStatus() == Status.COMPLETED) {
				if (changeToStatus == Status.RATED) {
					projectEntity.setStatus(Status.RATED);
				} else if (changeToStatus == Status.IN_THE_PRODACTION) {
					projectEntity.setStatus(Status.IN_THE_PRODACTION);
				} else {
					throw new BadRequestException(ResourceType.PROJECT, "You sent wrong status");
				}
			} else {
				throw new BadRequestException(ResourceType.PROJECT, "You can't change status");
			}

		} else {
			throw new BadRequestException(ResourceType.PROJECT, "Your status is incorrect");
		}
		
		
		
		
		/*if (changeToStatus == Status.BUSY || changeToStatus == Status.COMPLETED
				|| changeToStatus == Status.CURRENTLY_UNAVAILABLE) {
			if (projectEntity.getStatus() == Status.AVAILABLE) {
				if (changeToStatus == Status.BUSY) {
					projectEntity.setStatus(Status.BUSY);
				} else {
					throw new BadRequestException(ResourceType.PROJECT, "Project not available");
				}
			} else if (projectEntity.getStatus() == Status.IN_THE_PRODACTION) {
				if (changeToStatus == Status.CURRENTLY_UNAVAILABLE) {
					projectEntity.setStatus(Status.CURRENTLY_UNAVAILABLE);
				} else if (changeToStatus == Status.COMPLETED) {
					projectEntity.setStatus(Status.COMPLETED);
				} else {
					throw new BadRequestException(ResourceType.PROJECT, "Project not in production");
				}
			} else {
				throw new BadRequestException(ResourceType.PROJECT, "You can't change status");
			}

		} else {
			throw new BadRequestException(ResourceType.PROJECT, "Your status is incorrect");
		}*/
		projectEntity.setTitle(projectEntity.getTitle());
		projectEntity.setStart_date(projectEntity.getStart_date());
		projectEntity.setNumber_of_bods(projectEntity.getNumber_of_bods());
		projectEntity.setFinal_grade(projectEntity.getFinal_grade());
		projectEntity.setFinal_comment(projectEntity.getFinal_comment());
		projectEntity.setUser(userEntity);

		final ProjectEntity saveEntity = projectsRepository.save(projectEntity);

		return projectsMapper.toDto(saveEntity);

	}

}
