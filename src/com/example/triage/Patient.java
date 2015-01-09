package com.example.triage;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;

/**
 * Represents a Patient that enters the Emergency Room
 * 
 * @author c3teohyi
 * 
 */
public class Patient implements Serializable {

	/**
	 * Auto-generated ID to pass objects through different activities.
	 */
	private static final long serialVersionUID = -6086880863328138516L;
	/**
	 * Array of strings that contains the name, health card number and birth
	 * date, respectively.
	 */
	private final String[] personalInfo;
	/**
	 * Date and Time that the patient has arrived to the Emergency Room.
	 */
	private Date arrivalTime;
	/**
	 * Array of numbers that contains the different vital signs to be recorded
	 * by the nurse. Contains temperature, systolic blood pressure, diastolic
	 * blood pressure, and the heart rate respectively.
	 */
	private Number[] vitalSigns;
	/**
	 * Boolean value used to determine whether a patient has a record in the
	 * emergency room.
	 */
	private boolean firstTimer;
	/**
	 * A map of all visit records of the patient, sorted by arrival time. This
	 * collection includes current and previous visit records.
	 */
	private TreeMap<Date, VisitRecords> arriveTimeToVisitRecords;
	/**
	 * A map of all prescriptions of the patient, sorted by arrival time. This
	 * collection includes current and previous prescriptions.
	 */
	private TreeMap<Date, String> prescriptions;

	/**
	 * Constructs a Patient Object given the patient's name, health card number
	 * and date of birth.
	 * 
	 * @param name
	 *            The name of the Patient.
	 * @param healthCardNumber
	 *            The patient's health card number.
	 * @param DOB
	 *            The patient's date of birth.
	 */
	public Patient(String name, String healthCardNumber, String DOB) {
		personalInfo = new String[3];
		personalInfo[0] = name;
		personalInfo[1] = healthCardNumber;
		personalInfo[2] = DOB;
		vitalSigns = new Number[4];
		firstTimer = true;
		arriveTimeToVisitRecords = new TreeMap<Date, VisitRecords>();
		prescriptions = new TreeMap<Date, String>();
	}

	/**
	 * Returns the name of the patient.
	 * 
	 * @return The name of the patient.
	 */
	public String getName() {
		return personalInfo[0];
	}

	/**
	 * Returns the patient's health card number.
	 * 
	 * @return The patient's health card number.
	 */
	public String getHealthCardNumber() {
		return personalInfo[1];
	}

	/**
	 * Returns the patient's date of birth.
	 * 
	 * @return The patient's date of birth.
	 */
	public String getDOB() {
		return personalInfo[2];
	}

	/**
	 * Returns the patient's arrival time at the Emergency Room for the current
	 * visit.
	 * 
	 * @return The patient's arrival time at the Emergency Room for the current
	 *         visit.
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the patient's arrival time at the Emergency Room for the current
	 * visit.
	 * 
	 * @param arrivalTime
	 *            The patient's arrival time at the Emergency Room for the
	 *            current visit.
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Returns the map of prescriptions sorted by the time the prescription was
	 * issued.
	 * 
	 * @return The map of prescriptions sorted by the time the prescription was
	 *         issued.
	 */
	public TreeMap<Date, String> getPrescription() {
		return prescriptions;
	}

	/**
	 * Adds a prescription associated with the time it was issued to the map of
	 * prescriptions.
	 * 
	 * @param date
	 *            The date the prescription was issued.
	 * @param prescription
	 *            The prescription issued by the Physician to the Patient.
	 */
	public void addPrescription(Date date, String prescription) {
		prescriptions.put(date, prescription);
	}

	/**
	 * Returns the Patient's temperature.
	 * 
	 * @return the Patient's temperature.
	 */
	public Number getTemp() {
		return vitalSigns[0];
	}

	/**
	 * Sets the Patient's temperature.
	 * 
	 * @param Temp
	 *            the Patient's temperature.
	 */
	public void setTemp(double Temp) {
		vitalSigns[0] = Temp;
	}

	/**
	 * Returns the Patient's blood pressure in Systolic.
	 * 
	 * @return the Patient's blood pressure in Systolic.
	 */
	public Number getSystolicBloodPressure() {
		return vitalSigns[1];
	}

	/**
	 * Sets the Patient's blood pressure in Systolic.
	 * 
	 * @param systolic
	 *            the Patient's blood pressure in Systolic.
	 */
	public void setSystolicBloodPressure(double systolic) {
		vitalSigns[1] = systolic;
	}

	/**
	 * Returns the Patient's blood pressure in Diastolic.
	 * 
	 * @return the Patient's blood pressure in Diastolic.
	 */
	public Number getDiastolicBloodPressure() {
		return vitalSigns[2];
	}

	/**
	 * Sets the Patient's blood pressure in Diastolic.
	 * 
	 * @param diastolic
	 *            the Patient's blood pressure in Diastolic.
	 */
	public void setDiastolicBloodPressure(double diastolic) {
		vitalSigns[2] = diastolic;
	}

	/**
	 * Returns the Patient's heart rate.
	 * 
	 * @return the Patient's heart rate.
	 */
	public Number getHeartRate() {
		return vitalSigns[3];
	}

	/**
	 * Sets the Patient's heart rate.
	 * 
	 * @param hr
	 *            the Patient's heart rate.
	 */
	public void setHeartRate(int hr) {
		vitalSigns[3] = hr;
	}

	/**
	 * Returns True if Patient is a first timer in the Emergency Room.
	 * 
	 * @return True if Patient is a first timer in the Emergency Room.
	 */
	public boolean getFirstTimer() {
		return firstTimer;
	}

	/**
	 * Sets if a Patient is a first timer or not given ft.
	 * 
	 * @param ft
	 *            true if first-timer, false otherwise.
	 */
	public void setFirstTimer(boolean ft) {
		firstTimer = ft;
	}

	/**
	 * Returns the map of a patient's visit records sorted by the time the
	 * patient was at the Emergency Room for that particular visit.
	 * 
	 * @return the map of a patient's visit records sorted by the time the
	 *         patient was at the Emergency Room for that particular visit.
	 */
	public TreeMap<Date, VisitRecords> getArriveTimeToVisitRecords() {
		return arriveTimeToVisitRecords;
	}

	/**
	 * Adds a visit record to the map of getArriveTimeToVisitRecords.
	 * 
	 * @param d
	 *            Time of the visit record being added.
	 * @param v
	 *            The visit record being added.
	 */
	public void addVisitRecord(Date d, VisitRecords v) {
		arriveTimeToVisitRecords.put(d, v);
	}

}
