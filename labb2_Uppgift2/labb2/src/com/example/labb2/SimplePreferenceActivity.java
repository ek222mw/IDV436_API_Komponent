package com.example.labb2;

import java.util.List;
import com.example.labb2.R;
import android.preference.PreferenceActivity;

public class SimplePreferenceActivity extends PreferenceActivity {
	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.preference_headers, target);
	}
	
	@Override
	protected boolean isValidFragment (String fragmentName) {
	  return (SimplePreferenceFragment.class.getName().equals(fragmentName));
	}
}
