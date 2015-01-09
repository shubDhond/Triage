package com.example.triage;

public class InvalidMeasurementException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1034199951124118725L;
	
	public InvalidMeasurementException(){
		super("Invalid Measurement Entered");
	}

}
