package com.example.triage;

import java.io.Serializable;

/**
 * Represents an user with capabilities to login and access the app.
 * 
 * @author g3hatemk
 * 
 */
public abstract class User implements Serializable {

	/**
	 * Auto-generated ID to pass objects through different activities.
	 */
	private static final long serialVersionUID = 6188865253728522019L;
	/**
	 * Unique username attributed to a specific user-password combination.
	 */
	private String username;
	/**
	 * Unique password attributed to a specific user-password combination.
	 */
	private String password;

	/**
	 * Given the username-password combination from passwords.txt, construct a
	 * User.
	 * 
	 * @param username
	 *            The username given.
	 * @param password
	 *            The password given.
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Given a health card number, return the patient, for which the health
	 * card number pertains.
	 * 
	 * @param hcn
	 *            The health card number of the patient to be looked up.
	 * @return The patient that is assigned the given health card number.
	 */
	public Patient lookUp(String hcn) {
		return MainActivity.getPatient_list().get(hcn);
	}
}
