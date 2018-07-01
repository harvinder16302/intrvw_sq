package com.tiaa.mproduct.exception;

public class InsufficientRawMaterialException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5084976973521215232L;

	public InsufficientRawMaterialException(String message, Throwable cause) {
		super("Insufficient Rawmaterial " + message, cause);
	}

	public InsufficientRawMaterialException(String message) {
		super("Insufficient Rawmaterial " + message);
	}
	
}
