package com.example.triage;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

/**
 * @author c4dhonds
 *
 */

public class SetTimeActivity extends Activity {
	/**
	 * The time picker to set the time a patient was seen by a physician.
	 */
	private TimePicker time;
	/**
	 * The date picker to set the date a patient was seen by a physician.
	 */
	private DatePicker date;
	/**
	 * The nurse who is logged in.
	 */
	private Nurse u;
	/**
	 * The visit record for which the isSeen boolean is being set.
	 */
	private VisitRecords v;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_time);
		
		u=(Nurse)getIntent().getSerializableExtra("Nurse");
		time=(TimePicker)findViewById(R.id.timeSeen);
		date=(DatePicker)findViewById(R.id.dateSeen);
		v=(VisitRecords) getIntent().getSerializableExtra("VisitRecords");
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_time, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/** This method sets when a patient was seen by a doctor when the set button is clicked.
	 * @param view The view which is clicked to call this method.
	 */
	public void setTimeSeenByDoctor(View view){
		Date date2=new Date(date.getYear(), date.getMonth(), date.getDayOfMonth(), time.getCurrentHour(), time.getCurrentMinute());
		if (!MainActivity.getPatient_list().get(v.getHealth_card_number()).getArriveTimeToVisitRecords().isEmpty()){
			u.setSeenByDoctor(date2, v);
		}
		Intent intent=new Intent(this, VitalSignsActivity.class);
		intent.putExtra("Nurse", u);
		intent.putExtra("VisitRecords", v);
		intent.putExtra("lastVREC", getIntent().getBooleanExtra("lastVREC", false));
		intent.putExtra("isPhysician", false);
		Patient p=(Patient) getIntent().getSerializableExtra("Patient");
		intent.putExtra("Patient",p);
		startActivity(intent);
	}
	
	/**
	 * This method launches the previous activity once the back button is clicked.
	 * @param view The view which is clicked to call this method.
	 */
	public void Back(View view){
		Intent intent=new Intent(this, VitalSignsActivity.class);
		intent.putExtra("Nurse", u);
		intent.putExtra("VisitRecords", (VisitRecords)getIntent().getSerializableExtra("VisitRecords"));
		intent.putExtra("lastVREC", getIntent().getBooleanExtra("lastVREC", false));
		intent.putExtra("isPhysician", false);
		Patient p=(Patient) getIntent().getSerializableExtra("Patient");
		intent.putExtra("Patient",p);
		startActivity(intent);
	}
}
