package com.concrete.desafioluiz.exception;

public class InvalidSessionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidSessionException() {
        super();
    }
    public InvalidSessionException(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidSessionException(String message) {
        super(message);
    }
    public InvalidSessionException(Throwable cause) {
        super(cause);
    }
    
}
