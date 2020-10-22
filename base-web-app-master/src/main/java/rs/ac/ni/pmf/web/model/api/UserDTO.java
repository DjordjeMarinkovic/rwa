package rs.ac.ni.pmf.web.model.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserDTO {
	
	private int id;
	private String index;
	private String name;
	private String surmane;
	private String mail;
	private String username;
	private String password;
	private int year_of_study;
	
}
