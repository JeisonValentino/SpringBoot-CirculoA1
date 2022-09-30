package com.ProyectoEmpresarial.app.dtoRest;

import org.springframework.stereotype.Component;

@Component
public class Response extends RuntimeException{
	/**
	 *
	 */
	private static final long serialVersionUID = -7255500657440516689L;
	/**
	 *
	 */


	private String message;

	public Response() {

	}

	public String getMessage() {
		return message;
	}

	public Response(String message) {
		super(message);
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 @Override
	    public synchronized Throwable fillInStackTrace() {
	        return this;
	    }
}
