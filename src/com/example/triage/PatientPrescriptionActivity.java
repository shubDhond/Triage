package com.example.triage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PatientPrescriptionActivity extends Activity {
	/**
	 * The text views which display the name of the patient and the errors if any.
	 */
	private TextView name, error;
	/**
	 * The edit texts which take the input for the prescription.
	 */
	private EditText itemName, prescription;
	/**
	 * The patient for which the prescription is being written.
	 */
	private Patient p;
	/**
	 * The physician who is logged in.
	 */
	private Physician u;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_prescription);
		Intent i=getIntent();
		
		u=(Physician)i.getSerializableExtra("Physician");
		System.out.println(u.toString());
		
		name=(TextView) findViewById(R.id.PrecPatientName3);
		error=(TextView)findViewById(R.id.prescErrors);
		itemName=(EditText) findViewById(R.id.PrecItemName);
		prescription=(EditText) findViewById(R.id.WritePrescription);
		
		p=(Patient)i.getSerializableExtra("Patient");
		name.setText(p.getName());
	}
	
	/**
	 * The method which is called when the submit prescription button is called. This method takes the inputs and checks for errors then adds the prescription to a particular patient.
	 * @param view The view which was clicked to call this activity.
	 */
	public void submitPresc(View view){
		if(itemName.getText().toString().equals("")||
				prescription.getText().toString().equals(""))
			error.setText("Please Fill In Both Required Fields Above");
		else{
			String s="";
			s+=itemName.getText().toString();
			s+="\n\n";
			s+=prescription.getText().toString();
			u.recordPrescription(p, s);
			
			Intent intent=new Intent(this, AllPrescriptionsActivity.class);
			intent.putExtra("Patient", p);
			intent.putExtra("Physician", u);
			startActivity(intent);
		}
	}
	
	/**
	 * The method which is called when the back button is clicked. This method launches the previous activity.
	 * @param view The view which was clicked to call this activity.
	 */
	public void Back(View view){
		Intent intent=new Intent(this, AllPrescriptionsActivity.class);
		intent.putExtra("Patient", p);
		intent.putExtra("Physician", u);
		startActivity(intent);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_prescription, menu);
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
}
