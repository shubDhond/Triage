package com.example.triage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class PatientInfoActivity extends Activity {
	
	/**
	 * The text views which display a patient's info.
	 */
	private TextView name;
	private TextView hcn;
	private TextView dob;
	/**
	 * The patient whose information is being displayed.
	 */
	private Patient p;
	/**
	 * The user who is logged in.
	 */
	private User u;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_info);
		
		name=(TextView)findViewById(R.id.s);
		hcn=(TextView)findViewById(R.id.d);
		dob=(TextView)findViewById(R.id.PInfoDOB);
		u=(Physician)getIntent().getSerializableExtra("Physician");
		p=(Patient)getIntent().getSerializableExtra("Patient");
		name.setText(p.getName());
		hcn.setText(p.getHealthCardNumber());
		dob.setText(p.getDOB());
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_info, menu);
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
	 * The method which is called when the back button is clicked. This method will launch the previous activity.
	 * @param view The view which was clicked to call this method.
	 */
	public void Back(View view){
		Intent intent=new Intent(this, PhysicianMenuActivity.class);
		intent.putExtra("Patient", p);
		intent.putExtra("Physician", u);
		startActivity(intent);
	}
}
