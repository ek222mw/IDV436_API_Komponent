package com.example.labb2;

import com.example.labb2.R;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SimplePreferenceFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_background_color);
	}
}
