package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.hibernate.boot.jaxb.hbm.spi.SubEntityInfo;

@StaticMetamodel(SubjectEntity.class)
public class SubjectEntity_ {

	private static volatile SingularAttribute<SubjectEntity, String> name;
	
	private static volatile ListAttribute<SubjectEntity, ProjectEntity> projects;
	
	private static volatile SingularAttribute<SubjectEntity, UserEntity> userProfessor;
}
