package com.example.triage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author c4dhonds
 * 
 */
public class PatientRecordsActivity extends Activity {
	/**
	 * A String list of dates to show in the list view.
	 */
	private ArrayList<String> vRecs = new ArrayList<String>();
	/**
	 * The list view which displays the dates of each visit record for a
	 * patient.
	 */
	private ListView visitRecords;
	/**
	 * The array adapter which takes the vRecs list to display in a list view.
	 */
	private ArrayAdapter<String> a;
	/**
	 * The text view which displays the patients name.
	 */
	private TextView name;
	/**
	 * The patient for which to load the visit records.
	 */
	private Patient p;
	/**
	 * The add visit record button which is only enabled if a Nurse is logged
	 * in.
	 */
	private Button add;
	/**
	 * The nurse or physician logged in.
	 */
	private User u;
	/**
	 * The boolean which determines whether a physician or nurse is logged in.
	 */
	private boolean isPhysician;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_records);
		getActionBar().setDisplayHomeAsUpEnabled(false);

		add = (Button) findViewById(R.id.addVREC);

		Intent i = getIntent();

		isPhysician = i.getBooleanExtra("isPhysician", true);
		if (isPhysician) {
			add.setEnabled(false);
			u = (Physician) i.getSerializableExtra("Physician");
		} else {
			u = (Nurse) i.getSerializableExtra("Nurse");
		}

		p = (Patient) i.getSerializableExtra("Patient");
		name = (TextView) findViewById(R.id.patientName);
		name.setText(p.getName());
		if (!(p.getFirstTimer())) {
			for (Entry<Date, VisitRecords> entry : p
					.getArriveTimeToVisitRecords().entrySet()) {
				vRecs.add(entry.getKey().toString());
			}
		}

		Toast.makeText(this, p.getName(), Toast.LENGTH_LONG).show();
		
		visitRecords = (ListView) findViewById(R.id.visitRecords);
		a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				vRecs);
		visitRecords.setAdapter(a);

		visitRecords.setOnItemClickListener(new OnItemClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * android.widget.AdapterView.OnItemClickListener#onItemClick(android
			 * .widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String s = (String) visitRecords.getAdapter().getItem(position);
				VisitRecords v = null;
				for (Map.Entry<Date, VisitRecords> entry : MainActivity.getPatient_list().get(p.getHealthCardNumber())
						.getArriveTimeToVisitRecords().entrySet()) {
					String t = entry.getKey().toString();
					if (s.equals(entry.getKey().toString()))
						v = entry.getValue();
				}

				Intent intent = new Intent(PatientRecordsActivity.this,
						VitalSignsActivity.class);
				intent.putExtra("VisitRecords", v);
				boolean lastVREC = false;
				if (v == MainActivity.getPatient_list().get(p.getHealthCardNumber()).getArriveTimeToVisitRecords().get(
						MainActivity.getPatient_list().get(p.getHealthCardNumber()).getArriveTimeToVisitRecords().lastKey()))
					lastVREC = true;
				else
					lastVREC = false;
				intent.putExtra("Patient", MainActivity.getPatient_list().get(p.getHealthCardNumber()));
				intent.putExtra("lastVREC", lastVREC);
				if (isPhysician) {
					intent.putExtra("Physician", u);
				} else {
					intent.putExtra("Nurse", u);
				}
				intent.putExtra("isPhysician", isPhysician);
				startActivity(intent);

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_records, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
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
	 * The method which is called when the add visit record button is clicked.
	 * This adds a visit record for the particular patient.
	 * 
	 * @param view
	 *            The view which was clicked to call this method.
	 */
	public void addVisitRecords(View view) {
		((Nurse) u).createVR(p.getHealthCardNumber());
		String s = MainActivity.getPatient_list().get(p.getHealthCardNumber())
				.getArriveTimeToVisitRecords().lastKey().toString();
		vRecs.add(s);
		a.notifyDataSetChanged();
	}

	

	/**
	 * Launches the previous activity.
	 * 
	 * @param view
	 *            The button clicked which launches the previous activity.
	 */
	public void back(View view) {
		Intent intent;
		if (isPhysician) {
			intent = new Intent(this, PhysicianMenuActivity.class);
		} else {
			intent = new Intent(this, PatientSearchActivity.class);
		}
		intent.putExtra("Patient", p);
		if (isPhysician) {
			intent.putExtra("Physician", u);
		} else {
			intent.putExtra("Nurse", u);
		}
		intent.putExtra("isPhysician", isPhysician);
		startActivity(intent);
	}
}
