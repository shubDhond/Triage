package com.example.triage;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;

/**
 * Represents a visit record for a particular visit to the Emergency Room
 * 
 * @author c3teohyi
 * 
 */
public class VisitRecords implements Serializable {

	/**
	 * Auto-generated ID to pass objects through different activities.
	 */
	private static final long serialVersionUID = 8644378733431611008L;

	/**
	 * Health card number of a particular patient that corresponds to his visit
	 * records.
	 **/
	private String health_card_number;

	/** Time arrived at ER for current visit. **/
	private Date arrival_time;

	/**
	 * Maps the time the vital signs were taken to its corresponding vital
	 * signs, sorted from earliest to latest.
	 **/
	private TreeMap<Date, Number[]> timeofMeasurementstovitalSigns;

	/**
	 * Time the Patient was seen by the Physician.
	 */
	private Date timeSeen;

	/**
	 * True if Patient has been seen by the Physician.
	 */
	private boolean isSeen;

	/**
	 * Constructs a Visit Records object given a Patient's health card number,
	 * the Patient's arrival time at the Emergency Room for the current visit
	 * and an empty Map.
	 * 
	 * @param health_card_number
	 *            the Patient's health card number.
	 * @param arrival_time
	 *            the Time the Patient arrived at the Emergency Room for the
	 *            current visit.
	 * @param emptyMap
	 *            an empty Visit Records Map.
	 */
	public VisitRecords(String health_card_number, Date arrival_time,
			TreeMap<Date, Number[]> emptyMap) {
		super();
		this.health_card_number = health_card_number;
		this.arrival_time = arrival_time;
		timeofMeasurementstovitalSigns = emptyMap;
		isSeen=false;
	}

	/**
	 * Returns the Patient's health card number associated with the current
	 * visit records.
	 * 
	 * @return the Patient's health card number associated with the current
	 *         visit records.
	 */
	public String getHealth_card_number() {
		return health_card_number;
	}

	/**
	 * Sets the Patient's health card number associated with the current visit
	 * records.
	 * 
	 * @param health_card_number
	 *            the Patient's health card number.
	 */
	public void setHealth_card_number(String health_card_number) {
		this.health_card_number = health_card_number;
	}

	/**
	 * Returns the time the Patient arrived at the Emergency Room for the
	 * current visit.
	 * 
	 * @return the time the Patient arrived at the Emergency Room for the
	 *         current visit.
	 */
	public Date getArrival_time() {
		return arrival_time;
	}

	/**
	 * Sets the time the Patient arrived at the Emergency Room for the current
	 * visit.
	 * 
	 * @param arrival_time
	 *            the time the Patient arrived at the Emergency Room for the
	 *            current visit.
	 */
	public void setArrival_time(Date arrival_time) {
		this.arrival_time = arrival_time;
	}

	/**
	 * Return the map that consists of the different vital signs measurements
	 * sorted by the time these measurements were taken.
	 * 
	 * @return the map that consists of the different vital signs measurements
	 *         sorted by the time these measurements were taken.
	 */
	public TreeMap<Date, Number[]> getTimeofMeasurementstovitalSigns() {
		return timeofMeasurementstovitalSigns;
	}

	/**
	 * Sets the map that consists of the different vital signs measurements
	 * sorted by the time these measurements were taken.
	 * 
	 * @param timeofMeasurementstovitalSigns
	 *            the map that consists of the different vital signs
	 *            measurements sorted by the time these measurements were taken.
	 */
	public void setTimeofMeasurementstovitalSigns(
			TreeMap<Date, Number[]> timeofMeasurementstovitalSigns) {
		this.timeofMeasurementstovitalSigns = timeofMeasurementstovitalSigns;
	}

	/**
	 * Sets the time the Patient was seen by the Physician for the current
	 * visit.
	 * 
	 * @param d
	 *            the time the Patient was seen by the Physician for the 
	 *            current visit.
	 */
	public void setTimeSeen(Date d) {
		timeSeen = d;
		if(isSeen==false)
			isSeen=true;
	}

	/**
	 * Returns the time the Patient was seen by the Physician for the current
	 * visit.
	 * 
	 * @return the time the Patient was seen by the Physician for the current
	 *         visit.
	 */
	public Date getTimeSeen() {
		return timeSeen;
	}

	/**
	 * Returns True if Patient has been seen by the Physician for the current
	 * visit.
	 * 
	 * @return True if Patient has been seen by the Physician for the current
	 *         visit.
	 */
	public boolean isSeen() {
		return isSeen;
	}
}
