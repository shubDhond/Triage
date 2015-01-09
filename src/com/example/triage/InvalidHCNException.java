package com.example.triage;


public class InvalidHCNException extends Exception {
	
	private static final long serialVersionUID = -7306317551920111133L;

	public InvalidHCNException(){
		super("Invalid Health Card Number");
	}
}
