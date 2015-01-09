package com.example.triage;

public class InvalidPasswordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7549451131369445732L;
	
	public InvalidPasswordException(){
		super("Invalid Password Entered");
	}

}
