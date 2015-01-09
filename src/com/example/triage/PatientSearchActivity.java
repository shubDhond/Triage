package com.example.triage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PatientSearchActivity extends Activity {
	/**
	 * The button to launch the activity showing the unseen patient list. This button is disabled if the physician is logged in.
	 */
	private Button launchNotSeenPatientList;
	/**
	 * The user, either a physician or nurse, using the application.
	 */
	private User u;
	
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_search);
		
		
		
		launchNotSeenPatientList=(Button) findViewById(R.id.launchUnseenPatientList);
		
		
		boolean isPhysician=getIntent().getBooleanExtra("isPhysician", true);
		
		if(isPhysician)
			u=(Physician)getIntent().getSerializableExtra("Physician");
		else
			u=(Nurse) getIntent().getSerializableExtra("Nurse");
		
		launchNotSeenPatientList.setEnabled(!(isPhysician));
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_search, menu);
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
	 * Searches for the patient using the health card number if the health card number is in map of all patients.
	 * If the health card number is not in the system it will display an error. But if the health card number exists, the patient is obtained and 
	 * depending on the user logged in the next activity is started. If the physician is logged in there is a specific menu which is loaded.
	 * @param view The view that was clicked to call this method.
	 */
	public void searchForPatient(View view){
			EditText hcnInput=(EditText) findViewById(R.id.hcnInput);
			String hcn=hcnInput.getText().toString();
			
			try{
				if(hcn.length()!=6)
					throw new InvalidHCNException();
				else if(!(MainActivity.getPatient_list().containsKey(hcn)))
					throw new UnauthorizedException();
			}catch(InvalidHCNException e){
				TextView searchError=(TextView) findViewById(R.id.searchErrors);
				searchError.setText("Invalid Health Card Number Error");
				return;
			}catch (UnauthorizedException e){
				TextView searchError=(TextView) findViewById(R.id.searchErrors);
				searchError.setText("Unauthorized Patient Access");
				return;
			}
			Patient p=MainActivity.getPatient_list().get(hcn);
			boolean isPhysician= getIntent().getBooleanExtra("isPhysician", true);
			 if(isPhysician){
				 Intent intent=new Intent(this, PhysicianMenuActivity.class);
				 intent.putExtra("Patient", p);
				 intent.putExtra("Physician", u);
				 startActivity(intent);
			 }
			 else{
				 Intent intent=new Intent(this, PatientRecordsActivity.class);
				 intent.putExtra("Patient", p);
				 intent.putExtra("Nurse", u);
				 intent.putExtra("isPhysician", isPhysician);
				 startActivity(intent);
			 }
	}
	
	/**
	 * Launches the activity which displays the list of patients yet to be seen by a physician. This method is called when the Launch Unseen Patient List button is clicked.
	 * @param view The view which was clicked to call this method. 
	 */
	public void launchUnseenPatientList(View view){
		Intent intent=new Intent(this, UnseenPatientsActivity.class);
		intent.putExtra("Nurse", u);
		startActivity(intent);
	}
	
	public void saveData(View view) {
		String fileName = "SavedData.ser";
		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
			fos = openFileOutput(fileName, Application.MODE_PRIVATE);
			out = new ObjectOutputStream(fos);
			
			for(Map.Entry<String, Patient> i:MainActivity.getPatient_list().entrySet())
				out.writeObject(i.getValue());
			
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
}
