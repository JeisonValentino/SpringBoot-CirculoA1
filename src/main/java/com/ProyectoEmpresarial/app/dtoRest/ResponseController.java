package com.ProyectoEmpresarial.app.dtoRest;

import org.springframework.stereotype.Component;

@Component
public class ResponseController extends RuntimeException{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public ResponseController() {

	}

	public String getMessage() {
		return message;
	}

	public ResponseController(String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
