package br.com.glsoftware.conting.exceptions;

public class ElementAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ElementAlreadyExistException(String message){ 
		
		super(message);
	}
}
