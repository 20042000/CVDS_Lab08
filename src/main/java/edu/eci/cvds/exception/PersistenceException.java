package edu.eci.cvds.exception;

public class PersistenceException extends Exception {
	
	public PersistenceException() {
		super();
	}
	
	public PersistenceException(String message,Throwable throwable) {
		super(message);
	}
}
