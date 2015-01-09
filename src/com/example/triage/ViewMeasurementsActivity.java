package com.example.triage;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewMeasurementsActivity extends Activity {
	private String temper, sbp, dbp, hr;
	private static User u;
	private static boolean isPhysician;
	private static String [] m;
	private static VisitRecords v;
	private static boolean l;
	private static Patient p;
	ActionBar.Tab measure,temperGraph,sbpGraph,dbpGraph,hrGraph;
	Fragment measureFragment=new MeasurementTextFragment();
	Fragment temperGraphFragment=new TemperGraphFragment();
	Fragment sbpGraphFragment=new SBPGraphFragment();
	Fragment dbpGraphFragment=new DBPGraphFragment();
	Fragment hrGraphFragment=new HRGraphFragment();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_measurements);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		
		isPhysician=getIntent().getBooleanExtra("isPhysician", true);
		l=getIntent().getBooleanExtra("lastVREC", true);
		p=(Patient) getIntent().getSerializableExtra("Patient");
		v=(VisitRecords) getIntent().getSerializableExtra("VisitRecords");
		if(isPhysician){
			u=(Physician)getIntent().getSerializableExtra("Physician");
		}else{
			u=(Nurse)getIntent().getSerializableExtra("Nurse");
		}
		
		v=(VisitRecords)getIntent().getSerializableExtra("VisitRecords");
		
		m=(String[]) getIntent().getSerializableExtra("Measurements");
		temper=m[0];
		sbp=m[1];
		dbp=m[2];
		hr=m[3];
		
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		measure=actionBar.newTab().setText("M");
		temperGraph=actionBar.newTab().setText("Temp");
		sbpGraph=actionBar.newTab().setText("SBP");
		dbpGraph=actionBar.newTab().setText("DBP");
		hrGraph=actionBar.newTab().setText("HR");
		
		measure.setTabListener(new TabListener(measureFragment));
		temperGraph.setTabListener(new TabListener(temperGraphFragment));
		sbpGraph.setTabListener(new TabListener(sbpGraphFragment));
		dbpGraph.setTabListener(new TabListener(dbpGraphFragment));
		hrGraph.setTabListener(new TabListener(hrGraphFragment));
		
		actionBar.addTab(measure);
		actionBar.addTab(temperGraph);
		actionBar.addTab(sbpGraph);
		actionBar.addTab(dbpGraph);
		actionBar.addTab(hrGraph);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_measurements, menu);
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
	
	public void back(View view){
		Intent intent=new Intent(this,VitalSignsActivity.class);
		intent.putExtra("Patient", p);
		intent.putExtra("VisitRecords",v);
		intent.putExtra("lastVREC", l);
		if(isPhysician){
			intent.putExtra("Physician", u);
		}else{
			intent.putExtra("Nurse", u);
		}
		intent.putExtra("isPhysician", isPhysician);
		startActivity(intent);
	}
	
	public static Bundle getBundle(){
		Bundle bundle=new Bundle();
		bundle.putSerializable("measurements", m);
		bundle.putSerializable("VisitRecords", v);
		bundle.putSerializable("Patient", p);
		bundle.putBoolean("lastVREC", l);
		if(isPhysician){
			bundle.putSerializable("Physician", u);
		}else{
			bundle.putSerializable("Nurse", u);
		}
		bundle.putBoolean("isPhysician", isPhysician);
		return bundle;
	}
}
