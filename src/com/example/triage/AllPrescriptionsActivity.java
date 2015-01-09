package com.example.triage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
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
import android.widget.ListView;
import android.widget.TextView;


/**
 * @author c4dhonds
 *
 */
public class AllPrescriptionsActivity extends Activity {
	/**
	 * The text view which displays the patients name.
	 */
	private TextView name;
	/**
	 * The list view which shows the dates corresponding to all the prescriptions of a patient.
	 */
	private ListView allPresc;
	/**
	 * The patient for which the prescriptions are being displayed.
	 */
	private Patient p;
	/**
	 * The list holding the string representations of the dates corresponding to the prescriptions for a patient.
	 */
	private ArrayList<String> prescDate;
	/**
	 * The physician logged in.
	 */
	private Physician u;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_prescriptions);
		
		u=(Physician)getIntent().getSerializableExtra("Physician");
		System.out.println(u.toString());
		
		prescDate=new ArrayList<String>();
		p=(Patient) getIntent().getSerializableExtra("Patient");
		name=(TextView)findViewById(R.id.PrescPatientName);
		name.setText(p.getName());
		if(!(p.getPrescription().isEmpty())){
			for(Entry<Date, String> entry:p.getPrescription().entrySet()){
				prescDate.add(entry.getKey().toString());
			}
		}
		
		allPresc=(ListView) findViewById(R.id.allPresc);
		ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, prescDate);
		allPresc.setAdapter(a);
		a.notifyDataSetChanged();
		allPresc.setOnItemClickListener(new OnItemClickListener(){

			/* (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String s=(String) allPresc.getAdapter().getItem(position);
				String v="jjjjjj";
				
				for(Map.Entry<Date, String> entry:p.getPrescription().entrySet()){
					if(s.equals(entry.getKey().toString()))
						v=entry.getValue();
				}
				Intent intent=new Intent(AllPrescriptionsActivity.this,
						ViewPrescriptionActivity.class );
				intent.putExtra("Patient", p);
				intent.putExtra("Prescription", v);
				intent.putExtra("Physician",u);
				startActivity(intent);
				
			}});
		
		
	}
	
	/**
	 * The method which is called when the add prescription button is called. This method will launch the activity allowing a physician to record a new prescription.
	 * @param view The view which was clicked to call this method.
	 */
	public void addPresc(View view){
		Intent intent=new Intent(this, PatientPrescriptionActivity.class);
		intent.putExtra("Patient", p);
		intent.putExtra("Physician", u);
		startActivity(intent);
	}
	
	/**
	 * The method which is called when the back button is clicked. This method launches the previous activity.
	 * @param view The view which was clicked to call this method.
	 */
	public void Back(View view){
		Intent intent=new Intent(this, PhysicianMenuActivity.class);
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
		getMenuInflater().inflate(R.menu.all_prescriptions, menu);
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
