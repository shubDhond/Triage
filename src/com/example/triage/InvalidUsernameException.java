package com.example.triage;

public class InvalidUsernameException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4731460666715482910L;
	
	public InvalidUsernameException(){
		super("Invalid User Name Entered");
	}

}
