package com.example.triage;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UnseenPatientsActivity extends Activity {
	/**
	 * The list view displaying all unseen patients.
	 */
	private ListView allUnseen;
	/**
	 * The lists holding the string representations of patients yet to be seen by a physician.
	 */
	private ArrayList<String> unseenPatients, unseenPatientNames;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unseen_patients);
		
		allUnseen=(ListView) findViewById(R.id.unseenPatients);
		unseenPatients=new ArrayList<String>();
		updateUnseenList();
		unseenPatientNames=new ArrayList<String>();
		
		for(int i=0;i<unseenPatients.size();i++){
			unseenPatientNames.add(MainActivity.getPatient_list().get(unseenPatients.get(i)).getName());
		}
		
		ArrayAdapter<String> a=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, unseenPatientNames);
		allUnseen.setAdapter(a);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.unseen_patients, menu);
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
	 * Updates the list of patients yet to be seen by a physician by iterating through the list of all patients
	 * then checks the latest visit record of each patient to see if the patient has been seen by a doctor. If the patient 
	 * has not been seen it will add the patient's health card number to the list of unseen. 
	 */
	public void updateUnseenList(){
			for(Map.Entry<String, Patient> entry:MainActivity.getPatient_list().entrySet()){
				if(!entry.getValue().getArriveTimeToVisitRecords().isEmpty()){
					if(!(entry.getValue().getArriveTimeToVisitRecords().lastEntry().getValue().isSeen()))
						unseenPatients.add(entry.getKey());
				}else{
					unseenPatients.add(entry.getKey());
				}
			}
		
	}
	
	
	/**
	 * This method launches the previous activity once the back button is clicked.
	 * @param view The view which is clicked to call this method.
	 */
	public void Back(View view){
		Intent intent=new Intent(this, PatientSearchActivity.class);
		intent.putExtra("Nurse", (Nurse)getIntent().getSerializableExtra("Nurse"));
		intent.putExtra("isPhysician", false);
		startActivity(intent);
	}
}
