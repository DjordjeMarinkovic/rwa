package rs.ac.ni.pmf.web.model.entity;

import java.time.LocalDate;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import rs.ac.ni.pmf.web.model.entity.ProjectEntity.Status;


@StaticMetamodel(ProjectEntity.class)
public class ProjectEntity_ {

	public static volatile SingularAttribute<ProjectEntity, String> title;
	//public static volatile SingularAttribute<ProjectEntity, LocalDate> start_date;
	//public static volatile SingularAttribute<ProjectEntity, Status> status;
	
	public static volatile SingularAttribute<ProjectEntity, SubjectEntity> subject;
}
