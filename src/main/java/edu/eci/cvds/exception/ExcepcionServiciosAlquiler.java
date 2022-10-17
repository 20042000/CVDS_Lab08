package edu.eci.cvds.exception;

public class ExcepcionServiciosAlquiler extends Exception{
	public ExcepcionServiciosAlquiler() {
		super();
	}
	
	public ExcepcionServiciosAlquiler(String message) {
		super(message);
	}
	
	public ExcepcionServiciosAlquiler(String message, Throwable throwable) {
		super(message, throwable);
	}
}
