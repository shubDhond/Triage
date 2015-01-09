package com.example.triage;


public class UnauthorizedException extends Exception {

	private static final long serialVersionUID = 1534554415605993876L;

	public UnauthorizedException(){
		
		super("Patient with this Health Card Number does not exist.");
		
	}
}

