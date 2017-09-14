package com.company.desafioluiz.exception;

public class EmailAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistsException() {
        super();
    }
    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
    public EmailAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}
