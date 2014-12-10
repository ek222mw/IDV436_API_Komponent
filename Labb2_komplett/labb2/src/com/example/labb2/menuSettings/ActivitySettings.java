package com.example.labb2.menuSettings;

import java.util.List;

import com.example.labb2.R;
import com.example.labb2.R.xml;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;

public class ActivitySettings extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onBuildHeaders(List<Header> target) {
		// TODO Auto-generated method stub
		loadHeadersFromResource(R.xml.preference_headers, target);
	}
	
	@Override//Have to do this cause of some changes in api 19
    protected boolean isValidFragment(String fragmentName) {
    	  return BackgroundColorFragment.class.getName().equals(fragmentName) 
    		  || SettingsTextSize.class.getName().equals(fragmentName);
	}

}