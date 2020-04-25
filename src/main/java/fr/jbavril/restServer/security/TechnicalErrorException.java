package fr.jbavril.restServer.security;

public class TechnicalErrorException extends RuntimeException{

	private static final long serialVersionUID = 2085788753650380977L;

	private Long id;
	
	public TechnicalErrorException() {
		super();
	}

	public TechnicalErrorException(String message) {
		super(message);
	}
	
	public TechnicalErrorException(Throwable cause) {
		super(cause);
	}
	
	public TechnicalErrorException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public TechnicalErrorException(Long id) {
		super(id.toString());
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
