package com.example.triage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PhysicianMenuActivity extends Activity {
	/**
	 * The physician who is logged in.
	 */
	Physician u;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_physician_menu);
		
		u=(Physician)getIntent().getSerializableExtra("Physician");
		System.out.println(u.toString());
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.physician_menu, menu);
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
	
	/**
	 * The method which is called when the launch patient info button is clicked. This method launches the patient information activity.
	 * @param view The view which was clicked to call this activity.
	 */
	public void launchPatientInfo(View view){
		Patient p=(Patient) getIntent().getSerializableExtra("Patient");
		Intent intent=new Intent(this, PatientInfoActivity.class);
		intent.putExtra("Patient", p);
		intent.putExtra("Physician", u);
		startActivity(intent);
	}
	
	/**
	 * The method which is called when the launch visit records button is clicked. This method launches the visit records activity.
	 * @param view The view which is clicked to call this method.
	 */
	public void launchVisitRecords(View view){
		Patient p=(Patient)getIntent().getSerializableExtra("Patient");
		Intent intent=new Intent(this,PatientRecordsActivity.class);
		intent.putExtra("Patient", p);
		intent.putExtra("isPhysician", true);
		intent.putExtra("Physician", u);
		startActivity(intent);
		
	}
	
	/**The method which is called when the launch patient prescription button is clicked. This method launches the patient prescription activity.
	 * @param view The view which was clicked to call this activity.
	 */
	public void launchPrescription(View view){
		Patient p=(Patient)getIntent().getSerializableExtra("Patient");
		Intent intent=new Intent(this,AllPrescriptionsActivity.class);
		intent.putExtra("Patient", p);
		intent.putExtra("Physician", u);
		startActivity(intent);
	}
	
	/**The method which is called when the back button is clicked. This method launches the previous activity.
	 * @param view The view which was clicked to call this activity.
	 */
	public void Back(View view){
		Intent intent=new Intent(this, PatientSearchActivity.class);
		intent.putExtra("Physician", u);
		intent.putExtra("isPhysician", true);
		startActivity(intent);
	}

}
