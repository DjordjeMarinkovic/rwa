package rs.ac.ni.pmf.web.model.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(UserEntity.class)
public class UserEntity_ {
	
	private static volatile SingularAttribute<UserEntity, String> name;
	private static volatile SingularAttribute<UserEntity, String> surname;
	
	//private static volatile SingularAttribute<UserEntity, TypeOfUserEntity> type_user;

}
