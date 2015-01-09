package com.example.triage;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Represents the main activity that launches the application into the login
 * screen.
 * 
 * @author g3hatemk
 * 
 */
public class MainActivity extends Activity {


	/**
	 * A map of nurses, with usernames as keys mapped to passwords.
	 */
	private static HashMap<String, String> nurse_list;
	/**
	 * A map of physicians, with usernames as keys mapped to passwords.
	 */
	private static HashMap<String, String> physician_list;
	/**
	 * A map of patients, sorted by their health card number.
	 */
	private static TreeMap<String, Patient> patient_list;
	/**
	 * A text view that takes in a username input from the application user.
	 */
	private EditText username;
	/**
	 * A text view that takes in a password input from the application user.
	 */
	private EditText password;
	/**
	 * A radio button to indicate the application user is a nurse.
	 */
	private RadioButton isPhysicianCheck;
	/**
	 * A radio button to indicate the application user is a physician.
	 */
	private RadioButton isNurseCheck;
	/**
	 * A boolean value that is true if the login attempt is for a nurse.
	 */
	private boolean isNurse;
	/**
	 * A boolean value that is true if the login attempt is for a physician.
	 */
	private boolean isPhysician;
	/**
	 * A text view that displays an error if the login attempt was unsuccessful.
	 */
	private TextView loginErrors;

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText) findViewById(R.id.userName);
		password = (EditText) findViewById(R.id.passWord);
		isPhysicianCheck = (RadioButton) findViewById(R.id.isPhysician);
		isNurseCheck = (RadioButton) findViewById(R.id.isNurse);
		loginErrors = (TextView) findViewById(R.id.loginErrors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	 * Reads from passwords.txt and patient_records.txt to generate the
	 * necessary objects. Attempts to login to the application, by checking the
	 * user-password combination.
	 * 
	 * @param view
	 *            The button which is clicked when a user attempts to login.
	 * @throws IOException
	 *             Thrown if an I/O operation could not be executed. Most
	 *             likely, the file is not found.
	 */
	public void launchPatientSearch(View view) throws IOException {
		AssetManager ps = this.getAssets();
		InputStream is = ps.open("passwords.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		physician_list = new HashMap<String, String>();
		nurse_list = new HashMap<String, String>();
		String patrickv1;
		while ((patrickv1 = br.readLine()) != null) {
			String[] pt_cs = patrickv1.split(",");
			if (pt_cs[2].equals("physician")) {
				physician_list.put(pt_cs[0], pt_cs[1]);
			} else {
				nurse_list.put(pt_cs[0], pt_cs[1]);
			}

		}
		br.close();
		is.close();
		isr.close();

		isPhysician = isPhysicianCheck.isChecked();
		isNurse = isNurseCheck.isChecked();

		String uname = username.getText().toString();
		uname = uname.toLowerCase();
		String pass = password.getText().toString();

		loginErrors.setText("");

		if (isPhysician) {
			try {
				if (!(physician_list.containsKey(uname)))
					throw new InvalidUsernameException();
				else if (!(physician_list.get(uname).equals(pass)))
					throw new InvalidPasswordException();
			} catch (InvalidUsernameException e) {
				loginErrors.setText("Invalid Username or Password");
				return;
			} catch (InvalidPasswordException e) {
				loginErrors.setText("Invalid Username or Password");
				return;
			}

		} else if (isNurse) {
			try {
				if (!(nurse_list.containsKey(uname)))
					throw new InvalidUsernameException();
				else if (!(nurse_list.get(uname).equals(pass)))
					throw new InvalidPasswordException();
			} catch (InvalidUsernameException e) {
				loginErrors.setText("Invalid Username or Password");
				return;
			} catch (InvalidPasswordException e) {
				loginErrors.setText("Invalid Username or Password");
				return;
			}

		}

		loadData();

		Intent intent = new Intent(this, PatientSearchActivity.class);
		intent.putExtra("isPhysician", isPhysician);
		if (isPhysician) {
			Physician phy = new Physician(username.getText().toString(),
					password.getText().toString());
			intent.putExtra("Physician", phy);
		} else {
			Nurse nurse = new Nurse(username.getText().toString(), password
					.getText().toString());
			intent.putExtra("Nurse", nurse);
		}

		startActivity(intent);

	}

	public static TreeMap<String, Patient> getPatient_list() {
		return patient_list;
	}

	/**
	 * Reads from the visitRecordsfile.txt to load previously saved data.
	 * 
	 * @throws IOException
	 *             Thrown if an I/O operation could not be executed. Most
	 *             likely, the file is not found.
	 */
	public void loadData() throws IOException {
		AssetManager patients = this.getAssets();
		InputStream iS = patients.open("patient_records.txt");
		InputStreamReader iSR = new InputStreamReader(iS);
		BufferedReader bR = new BufferedReader(iSR);
		patient_list = new TreeMap<String, Patient>();
		String patrickv2;
		while ((patrickv2 = bR.readLine()) != null) {
			String[] pt_cs = patrickv2.split(",");
			Patient patrick = new Patient(pt_cs[1], pt_cs[0], pt_cs[2]);
			patient_list.put(pt_cs[0], patrick);
		}
		bR.close();
		iSR.close();
		iS.close();
		
		String fileName = "SavedData.ser";
		FileInputStream fis=null;
		ObjectInputStream in=null;
		
		try{
			fis=openFileInput(fileName);
			in=new ObjectInputStream(fis);
			
			while(true){
				Patient p=(Patient)in.readObject();
				patient_list.put(p.getHealthCardNumber(),p);
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		finally{
			if(in!=null)			
				in.close();
		}
	}

	
}
