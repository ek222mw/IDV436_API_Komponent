package com.example.labb2.menuSettings;

import com.example.labb2.R;
import com.example.labb2.R.xml;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceFragment;

@SuppressLint("NewApi")
public class SettingsTextSize extends PreferenceFragment {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the location_preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences_textsize);
    }

}