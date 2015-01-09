package com.example.triage;

import java.util.Date;
import java.util.Map;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link DBPGraphFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link DBPGraphFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class DBPGraphFragment extends Fragment {
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		RelativeLayout rl=(RelativeLayout)inflater.inflate(R.layout.fragment_dbpgraph, container, false);
		
		GraphView graph=(GraphView)rl.findViewById(R.id.dbpGraph);
		VisitRecords v;
		Patient p;
		LineGraphSeries<DataPoint> series;
		Bundle bundle=this.getArguments();
		if(bundle!=null){
			v=(VisitRecords)bundle.getSerializable("VisitRecords");
			p=(Patient)bundle.getSerializable("Patient");
	
			int s=v.getTimeofMeasurementstovitalSigns().size();
			DataPoint[] dp=new DataPoint[s];
			int i=0;
			for(Map.Entry<Date, Number[]> e:v.getTimeofMeasurementstovitalSigns().entrySet()){
				Number[] m=e.getValue();
				dp[i]=new DataPoint(i,(Double)m[2]);
				i++;
			}
			series=new LineGraphSeries<DataPoint>(dp);
			series.setDrawDataPoints(true);
			series.setDataPointsRadius(10);
			graph.addSeries(series);
		}
		// Inflate the layout for this fragment
		return rl;
	}

	
}
