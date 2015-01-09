package com.example.triage;

import java.util.Date;
import java.util.TreeMap;

/**
 * Represents a nurse user that can login and perform multiple operations
 * in the Triage application.
 * 
 * @author g3hatemk
 */
public class Nurse extends User {

	/**
	 * Auto-generated ID to pass objects through different activities.
	 */
	private static final long serialVersionUID = -4605471502624662302L;

	/**
	 * Constructs a nurse given a username and password.
	 * 
	 * @param username
	 *            Username of the Nurse used for logging in.
	 * @param password
	 *            Password of the Nurse used for logging in.
	 */
	public Nurse(String username, String password) {
		super(username, password);
	}

	/**
	 * Creates a Visit Record of a patient, given the health card number 
	 * of that patient.
	 * 
	 * @param hcn
	 *            Health card number of the patient that the visit record is 
	 *            to be created.
	 */
	public void createVR(String hcn) {
		TreeMap<Date, Number[]> emptyMap = new TreeMap<Date, Number[]>();
		Date measurement = new Date(System.currentTimeMillis());
		VisitRecords v = new VisitRecords(hcn, measurement, emptyMap);
		MainActivity.getPatient_list().get(hcn).addVisitRecord(measurement, v);
		MainActivity.getPatient_list().get(hcn).setFirstTimer(false);

	}

	/**
	 * Updates the visit record of a patient with a new array of measurements.
	 * 
	 * @param p
	 *            Patient for which the visit record is to be updated.
	 * @param vitalSigns
	 *            Array of numbers that contain the updated vital signs.
	 */
	public void UpdateVisitRecord(Patient p, Number[] vitalSigns) {
		p.setTemp((Double) vitalSigns[0]);
		p.setSystolicBloodPressure((Double) vitalSigns[1]);
		p.setDiastolicBloodPressure((Double) vitalSigns[2]);
		p.setHeartRate((Integer) vitalSigns[3]);

		Date measurement = new Date(System.currentTimeMillis());
		Date key = p.getArriveTimeToVisitRecords().lastKey();
		VisitRecords recent = p.getArriveTimeToVisitRecords().get(key);
		recent.getTimeofMeasurementstovitalSigns().put(measurement,vitalSigns);

	}

	/**
	 * Return the previous records of a given patient.
	 * 
	 * @param p
	 *            Patient for which the records are to be viewed.
	 * @return The map of different visit records of a patient sorted by the
	 *         arrival time of the patient.
	 */
	public TreeMap<Date, VisitRecords> viewPreviousRecords(Patient p) {
		return p.getArriveTimeToVisitRecords();
	}

	/**
	 * Set the time a patient was seen by a doctor.
	 * 
	 * @param d
	 *            The Date and Time that the patient was seen by a doctor.
	 * @param v
	 *            The Visit Record for which the time seen by a doctor is
	 *            attributed.
	 */
	public void setSeenByDoctor(Date d, VisitRecords v) {
		MainActivity.getPatient_list().get(v.getHealth_card_number()).getArriveTimeToVisitRecords().get(v.getArrival_time()).setTimeSeen(d);
	}

}
