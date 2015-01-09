package com.example.triage;

import java.util.Date;

/**
 * Represents a physician who can log in to the application.
 * 
 * @author g3hatemk
 * 
 */
public class Physician extends User {

	/**
	 * Auto-generated ID to pass objects through different activities.
	 */
	private static final long serialVersionUID = -4423902762751265501L;

	/**
	 * Constructs a physician, given an username and password.
	 * 
	 * @param username
	 *            The username of the physician used to log in to the app.
	 * @param password
	 *            The password of the physician used to log in to the app.
	 */
	public Physician(String username, String password) {
		super(username, password);
	}

	/**
	 * Given a patient and prescription info, record that prescription in the
	 * patient's record.
	 * 
	 * @param p
	 *            The patient for which the prescription is prescribed to.
	 * @param prescription
	 *            The drug and instructions to be prescribed to the patient.
	 */
	public void recordPrescription(Patient p, String prescription) {
		Date date = new Date(System.currentTimeMillis());
		p.addPrescription(date, prescription);
	}

}
