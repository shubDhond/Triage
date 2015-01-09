package com.example.triage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VSMeasurementActivity extends Activity {
	private EditText temp,diastolic,systolic,heartRate;
	private TextView measurementError,name;
	private Patient p;
	private User u;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vsmeasurement);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		
		p=(Patient)getIntent().getSerializableExtra("Patient");
		temp=(EditText) findViewById(R.id.temp);
		systolic=(EditText) findViewById(R.id.systolic);
		diastolic=(EditText) findViewById(R.id.diastolic);
		heartRate=(EditText) findViewById(R.id.heartRate);
		measurementError=(TextView) findViewById(R.id.measurementError);
		name=(TextView) findViewById(R.id.patientName3);
		name.setText(((Patient)getIntent().getSerializableExtra("Patient")).getName());
		u=(Nurse)getIntent().getSerializableExtra("Nurse");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vsmeasurement, menu);
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
	
	public void submitMeasurement(View view){
		try{
			if(temp.getText().equals("")||systolic.getText().toString().equals("")||
					diastolic.getText().toString().equals("")||heartRate.getText().toString().equals(""))
				throw new InvalidMeasurementException();
		}catch(InvalidMeasurementException e){
			measurementError.setText("Invalid Measurement Entered");
			return;
		}
		
		Number[]measurements=new Number[4];
		measurements[0]=Double.parseDouble(temp.getText().toString());
		measurements[1]=Double.parseDouble(systolic.getText().toString());
		measurements[2]=Double.parseDouble(diastolic.getText().toString());
		measurements[3]=Integer.parseInt(heartRate.getText().toString());
		//needs to be fixed.
		((Nurse)u).UpdateVisitRecord(MainActivity.getPatient_list().get(p.getHealthCardNumber()), measurements);
		
		
		Intent intent=new Intent(this,VitalSignsActivity.class);
		VisitRecords v=p.getArriveTimeToVisitRecords().get(p.getArriveTimeToVisitRecords().lastKey());
		intent.putExtra("VisitRecords", v);
		intent.putExtra("Patient", p);
		intent.putExtra("Nurse", u);
		intent.putExtra("isPhysician", false);
		Toast.makeText(this, "Measurements Recorded", Toast.LENGTH_LONG).show();
		startActivity(intent);
	}
	
	public void back(View view){
		VisitRecords v=(VisitRecords)getIntent().getSerializableExtra("VisitRecords");
		p=(Patient)getIntent().getSerializableExtra("Patient");
		boolean l=getIntent().getBooleanExtra("lastVREC", true);
		Intent intent=new Intent(this, VitalSignsActivity.class);
		intent.putExtra("lastVREC", l);
		intent.putExtra("Patient", p);
		intent.putExtra("VisitRecords", v);
		intent.putExtra("Nurse", u);
		intent.putExtra("isPhysician", false);
		startActivity(intent);
	}
}
