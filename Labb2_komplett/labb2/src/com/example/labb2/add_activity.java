package com.example.labb2;

import java.util.ArrayList;
import java.util.List;




import com.example.labb2.PersitentStorage_Countries;
import com.example.labb2.R;
import com.example.labb2.add_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_activity extends Activity {

	String[] m_list;
	private EditText m_name, m_year;
	String m_names;
	String m_years;
	String[] m_CountryOld;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_country);		
		m_name = (EditText) findViewById(R.id.editTextCountry);
        m_year = (EditText) findViewById(R.id.editTextYear);
        InputSet();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_country_menu, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		int m_id = item.getItemId();
		
		if (m_id == R.id.action_main) {
            Intent productIntent = new Intent(this,PersitentStorage_Countries.class);
            startActivity(productIntent);
            return true;
        }
		
		
		return super.onOptionsItemSelected(item);
	}
	
    public void onClick(View view) {
    	Intent addNewCountryIntent = new Intent();
		switch (view.getId()) {
	        case R.id.buttonCountryFinish:
	    		m_names = m_name.getText().toString();
	    		m_years = m_year.getText().toString();
	    		int requestCode = this.getIntent().getIntExtra("RequestCode", 1);
	    		
	    		// Add new country
	    		if(requestCode == 1) {
	    			if(checkInput()) {
		    			m_list = new String[] {m_years, m_names};
		            	addNewCountryIntent.putExtra("MESSAGE", m_list);
		        		setResult(RESULT_OK, addNewCountryIntent);
		        		finish();
		    		} else {
		    			openDialog("Wrong input", "Change year to larger then 1920 and less equal to 2014 or name of country cannot be empty.");
		    		}
	    			
	    		// Update country
	    		} else if (requestCode == 2) {
	    			if(checkInput()) {
	    		    	Intent IntentEditCountry = new Intent();
		    			m_list = new String[] {m_years, m_names};
		    			IntentEditCountry.putExtra("MESSAGE", m_list);
		    			IntentEditCountry.putExtra("oldCountry", m_CountryOld);
		  
		        		setResult(RESULT_OK, IntentEditCountry);
		        		finish();
		    		} else {
		    			openDialog("Wrong input", "Change year to larger then 1920 and less equal to 2014 or name of country cannot be empty.");
		    		}
	    		}	    		
	            return;
		    case R.id.buttonCountryCancel:
		    	setResult(RESULT_CANCELED, addNewCountryIntent);
				finish();
		        return;
		}
    	
    }
    
    public boolean checkInput() {
    	try
    	{
	    	int year = Integer.parseInt(m_year.getText().toString());
	    	
	    	if(m_names.length() == 0 || year < 1920 || year > 2014 ) {
	    		return false;
	    	} else {
	    		return true;
	    	}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return false;
    }
    
    
    public void InputSet() {
    	
    	int h = this.getIntent().getIntExtra("RequestCode", 1);
    	if(h == 2) {
    		m_CountryOld = this.getIntent().getStringArrayExtra("countryToEdit");
	    	m_name.setText(m_CountryOld[1]);
	    	m_year.setText(m_CountryOld[0]);
    	}    	
    }
    
   
    public void openDialog(String title, String message) {
    	new AlertDialog.Builder(this)
    	    .setTitle(title)
    	    .setMessage(message)
    	    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
    	        public void onClick(DialogInterface dialog, int which) { 
    	            dialog.cancel();	        }
    	    })
    	    .show();
    }
	
}
