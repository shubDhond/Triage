package com.example.triage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewPrescriptionActivity extends Activity {
	/**
	 * The text views which display the patients name and a particular prescription.
	 */
	private TextView name, prescription;
	/**
	 * The patient fo which the prescription is being displayed.
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
		setContentView(R.layout.activity_view_prescription);
		
		name=(TextView) findViewById(R.id.PrescPatientName2);
		prescription=(TextView) findViewById(R.id.viewPrescription);
		
		Intent i=getIntent();
		u=(Physician)i.getSerializableExtra("Physician");
		p=(Patient) i.getSerializableExtra("Patient");
		name.setText(p.getName());
		String s=i.getStringExtra("Prescription");
		prescription.setText(s);
	}
	
	/**The method which is called when the back button is clicked. This method launches the previous activity.
	 * @param view The view which was clicked to call this method.
	 */
	public void Back(View view){
		Intent intent=new Intent(this, AllPrescriptionsActivity.class);
		intent.putExtra("Patient",p );
		intent.putExtra("Physician", u);
		startActivity(intent);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_prescription, menu);
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
