package com.example.triage;

import java.util.ArrayList;
import java.util.Date;
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

public class VitalSignsActivity extends Activity {
	private ArrayList<String> vSigns=new ArrayList<String>();
	private ListView vitSigns;
	private ArrayAdapter<String> a;
	private Button addMeasurement, setTime;
	private Patient p;
	private TextView name;
	private boolean lastVREC;
	private boolean isPhysician;
	private User u;
	private VisitRecords v;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vital_signs);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		
		setTime=(Button) findViewById(R.id.setTime);
		
		name=(TextView)findViewById(R.id.patientName2);
		p=(Patient)getIntent().getSerializableExtra("Patient");
		name.setText(p.getName());
		Intent i=getIntent();
		v=(VisitRecords) i.getSerializableExtra("VisitRecords");
		
		isPhysician=i.getBooleanExtra("isPhysician", true);
		
		if(isPhysician){
			u=(Physician)i.getSerializableExtra("Physician");
		}else{
			u=(Nurse)i.getSerializableExtra("Nurse");
		}
		
		
		addMeasurement=(Button) findViewById(R.id.addMeasurement1);
		lastVREC=i.getBooleanExtra("lastVREC", true);
		if(isPhysician||!(lastVREC))
			addMeasurement.setEnabled(false);
		
		setTime.setEnabled(lastVREC);
		setTime.setEnabled(isPhysician);
		
		if(!(MainActivity.getPatient_list().get(p.getHealthCardNumber()).getArriveTimeToVisitRecords().get(v.getArrival_time()).getTimeofMeasurementstovitalSigns().isEmpty())){
			for(Entry<Date, Number[]> entry: MainActivity.getPatient_list().get(p.getHealthCardNumber()).getArriveTimeToVisitRecords().get(v.getArrival_time()).getTimeofMeasurementstovitalSigns().entrySet()){
				vSigns.add(entry.getKey().toString());
			}
		}
		
		
		vitSigns=(ListView) findViewById(R.id.vitSigns);
		a=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vSigns);
		vitSigns.setAdapter(a);
		vitSigns.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				p=(Patient)getIntent().getSerializableExtra("Patient");
				String s=(String) vitSigns.getAdapter().getItem(position);
				Number[] m=null;
				VisitRecords v=(VisitRecords) getIntent().getSerializableExtra("VisitRecords");
				for(Entry<Date, Number[]> entry:MainActivity.getPatient_list().get(p.getHealthCardNumber()).getArriveTimeToVisitRecords().get(v.getArrival_time()).getTimeofMeasurementstovitalSigns().entrySet()){
					if(s.equals(entry.getKey().toString()))
						m=entry.getValue();
				}
				
				String[]m2=new String[4];
				m2[0]=m[0].toString();
				m2[1]=m[1].toString();
				m2[2]=m[2].toString();
				m2[3]=m[3].toString();
				
				Intent intent=new Intent(VitalSignsActivity.this,ViewMeasurementsActivity.class);
				intent.putExtra("Measurements", m2);
				intent.putExtra("Patient", p);
				intent.putExtra("VisitRecords", MainActivity.getPatient_list().get(p.getHealthCardNumber()).getArriveTimeToVisitRecords().get(v.getArrival_time()));
				intent.putExtra("lastVREC", lastVREC);
				if(isPhysician){
					intent.putExtra("Physician", u);
				}else{
					intent.putExtra("Nurse", u);
				}
				intent.putExtra("isPhysician",isPhysician);
				startActivity(intent);
				
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vital_signs, menu);
		return true;
	}

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
	
	public void addVSMeasurement(View view){
		VisitRecords v=(VisitRecords) getIntent().getSerializableExtra("VisitRecords");
		p=(Patient) getIntent().getSerializableExtra("Patient");
		Intent intent=new Intent(this, VSMeasurementActivity.class);
		intent.putExtra("Patient", p);
		intent.putExtra("VisitRecords", v);
		intent.putExtra("lastVREC", lastVREC);
		intent.putExtra("Nurse",u);
		startActivity(intent);
	}
	
	public void setTimeSeen(View view){
		Intent intent=new Intent(this, SetTimeActivity.class);
		intent.putExtra("Nurse", u);
		intent.putExtra("lastVREC", lastVREC);
		intent.putExtra("VisitRecords", (VisitRecords)getIntent().getSerializableExtra("VisitRecords"));
		intent.putExtra("isPhysician", false);
		intent.putExtra("Patient", p);
		startActivity(intent);
	}
	
	public void back(View view){
		Intent intent=new Intent(this,PatientRecordsActivity.class);
		p=(Patient)getIntent().getSerializableExtra("Patient");
		intent.putExtra("Patient", p);
		if(isPhysician){
			intent.putExtra("Physician", u);
		}else{
			intent.putExtra("Nurse", u);
		}
		intent.putExtra("isPhysician", isPhysician);
		startActivity(intent);
	}
}
