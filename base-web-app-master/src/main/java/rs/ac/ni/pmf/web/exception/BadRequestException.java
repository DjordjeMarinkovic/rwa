package rs.ac.ni.pmf.web.exception;

import rs.ac.ni.pmf.web.exception.ErrorInfo.ResourceType;

public class BadRequestException extends ResourceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(ResourceType resourceType, String message) {
		super(resourceType, message);
		// TODO Auto-generated constructor stub
	}

}
