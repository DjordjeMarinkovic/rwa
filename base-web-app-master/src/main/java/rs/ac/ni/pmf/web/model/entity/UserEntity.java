package rs.ac.ni.pmf.web.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int user_id;
	
	private String index;
	private String name;
	private String surname;
	private String mail;
	private String username;
	private String password;
	private int year_of_study;

	/*
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="type_user_id")
	private TypeOfUsers type_user;
	*/
	
	@OneToMany(fetch= FetchType.LAZY,
			mappedBy = "userProfessor")
	private List<SubjectEntity> subject;
	
	@ManyToMany
	@JoinTable(
			  name = "students_subjects", 
			  joinColumns = @JoinColumn(name = "students_id"), 
			  inverseJoinColumns = @JoinColumn(name = "subjects_id"))
	private List<SubjectEntity> subject_students;
	
	
	@OneToMany(fetch = FetchType.LAZY,
			mappedBy = "user")
	private List<ProjectEntity> projects;

}
