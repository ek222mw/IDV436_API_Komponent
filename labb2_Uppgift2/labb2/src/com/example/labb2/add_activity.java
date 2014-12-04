package com.example.labb2;

import java.util.ArrayList;
import java.util.List;


import com.example.labb2.DataArrayHandler;
import com.example.labb2.PersitentStorage_Countries;
import com.example.labb2.R;
import com.example.labb2.add_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_activity extends Activity {

	private CountryDataSource datasource;
	String[] oldCountry;
	private List<Country> values;
	private ArrayAdapter<Country> listAdapter;
	EditText edit_text_country;
	EditText edit_text_year;
	Button button;
	
	private int rCode;
	private Intent intent;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_country);
		datasource = new CountryDataSource(this);
        datasource.open();
		
		edit_text_year = (EditText)findViewById(R.id.read_year);
		edit_text_country = (EditText)findViewById(R.id.read_country);
		intent = getIntent();
		rCode = setInput();
		button = (Button) findViewById(R.id.add);
		
			button.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            String yearString = edit_text_year.getText().toString();
	        	String countryString = edit_text_country.getText().toString();
	        	
	        	intent.putExtra("country", countryString);
	        	intent.putExtra("year", yearString);
	        	setResult(RESULT_OK, intent);
	        	add_activity.this.finish();
            }
		});
	}
	
	
	
	public int setInput() {
    	//Bundle m = this.getIntent().getExtras();
    	int h = this.getIntent().getIntExtra("RequestCode", 1);
    	
    	if(h == 1) {
    		oldCountry = this.getIntent().getStringArrayExtra("countryToEdit");
    		String country = this.getIntent().getStringExtra("country");
    		String year = this.getIntent().getStringExtra("year");
    		edit_text_country.setText(country);
    		edit_text_year.setText(year);
    	}  
    	else{
    		edit_text_country.setText("");
    		edit_text_year.setText("");
    	}
    	return h;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_country_menu, menu);
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
	
    
    @Override
    protected void onResume() {
      datasource.open();
      super.onResume();
    }

    @Override
    protected void onPause() {
      datasource.close();
      super.onPause();
    }
	
}
