package com.example.triage;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link MeasurementTextFragment.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the
 * {@link MeasurementTextFragment#newInstance} factory method to create an
 * instance of this fragment.
 * 
 */
public class MeasurementTextFragment extends Fragment {
	private TextView showTemper, showSBP, showDBP, showHR;
	private User u;
	private boolean isPhysician;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		RelativeLayout rl=(RelativeLayout)inflater.inflate(R.layout.fragment_measurement_text, container,
				false);
		
		showTemper=(TextView) rl.findViewById(R.id.showTemper);
		showSBP=(TextView) rl.findViewById(R.id.showSBP);
		showDBP=(TextView) rl.findViewById(R.id.showDBP);
		showHR=(TextView) rl.findViewById(R.id.showHR);
		
		Bundle bundle=this.getArguments();
		if(bundle!=null){
			String[]m=(String[])bundle.getSerializable("measurements");
			showTemper.setText(""+m[0]);
			showSBP.setText(""+m[1]);
			showDBP.setText(""+m[2]);
			showHR.setText(""+m[3]);
			}
		
		// Inflate the layout for this fragment
		return rl;
	}

	  
}
