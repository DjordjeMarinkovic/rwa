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
public class ProjectDTO {

	private int id;
	private String title;
	
	private java.sql.Date start_date;
	private int number_of_bods;
	private int final_grade;
	private String final_comment;
	private int  status;
	private int subject_id;
	
	private Integer user_id;
	//private int subject_id;
	
}
