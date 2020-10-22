package rs.ac.ni.pmf.web.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name="projects")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_project;
	
	private String title;
	
	private java.sql.Date start_date;
	
	private int number_of_bods;
	private int final_grade;
	private String final_comment;
	//private Integer id_user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_subject")
	private SubjectEntity subject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_students")
	private UserEntity user;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;

	public enum Status{
		AVAILABLE,
		BUSY,
		COMPLETED,
		IN_THE_PRODACTION,
		RATED,
		CURRENTLY_UNAVAILABLE;
		
		@JsonValue
	    public int toValue() {
	        return ordinal();
	    }
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}
	}
	
}
