package rs.ac.ni.pmf.web.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorInfo {

	private ErrorCode errorCode;
	private ResourceType resourceType;

	private String message;

	public enum ErrorCode {
		NOT_FOUND,
		DUPLICATE,
		BAD_STATUS,
		UNAUTHORIZED,
		AUTHENTICATIO_FAILD
		
	}

	public enum ResourceType {
		ACCES,
		SUBJECT,
		PROJECT,
		USER,
		TYPEOFUSER
	}
}
