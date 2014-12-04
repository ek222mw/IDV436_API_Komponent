package com.example.labb2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreference extends Activity {
	
	private int fontColor;
	private int fontSize;
	RelativeLayout BackgroundColor;
	

	@Override
	protected void onCreate(Bundle state){         
		super.onCreate(state);
		setContentView(R.layout.prefs);

		/* Find all text views */
	//boolProp=(TextView)findViewById(R.id.boolprop);
	BackgroundColor=(RelativeLayout)findViewById(R.id.relative);
		//checkbox=(TextView)findViewById(R.id.checkbox);
		//ringtone=(TextView)findViewById(R.id.ringtone);


	}

	@Override
	public void onResume() {  // Activity comes in the foreground
		super.onResume();

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

		// Load simple properties. false/1 are default values used at start-up
		
		int bgColor = prefs.getInt("Colour",Color.BLUE);
	  BackgroundColor.setBackgroundColor(bgColor);

		
	}

	@Override
	protected void onStop(){
		super.onStop();

		// Update simple properties
		SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);
		
		int bgColor = prefs.getInt("Colour",Color.BLUE);

		// Save user preferences. We need an Editor object to make changes. 
		SharedPreferences.Editor edit = prefs.edit();
		
		edit.putInt("Colour", bgColor);

		// Commit your edits!!!
		edit.commit();

//		showToast("Update and Store properties",bgColor);
	}


	/*
	 * Prepare Preference Menus.
	 * 
	 */
/*	private static final int EDIT_ID = Menu.FIRST+2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, EDIT_ID, Menu.NONE, "Edit Settings")
	.setIcon(R.drawable.icon);

		return(super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case EDIT_ID:
			startActivity(new Intent(this, SimplePreferenceActivity.class));
			return(true);
		}

		return(super.onOptionsItemSelected(item));
	}
//	private void showToast(String action,int bgColor) {
//		String msg = action +" Properties\n"
//		+"Read_year = "+bgColor;
//		Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
//	}*/

}
