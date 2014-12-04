package com.example.labb1_all;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.labb1_all.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class add_activity extends Activity {
	
	private EditText editTxt_Country;
	private EditText editTxt_Year;
	private Button m_btn;
	//private ListView list;
	private static ArrayAdapter<String> m_adapter;
	private ArrayList<String> m_arrayList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_country);
		
        editTxt_Country = (EditText) findViewById(R.id.addCountry);
        editTxt_Year = (EditText) findViewById(R.id.addYear);
        m_btn = (Button) findViewById(R.id.add);
        
        m_arrayList = DataArrayHandler.GetArrayList();
        if(m_arrayList == null){
        	m_arrayList = new ArrayList<String>();
        }
      
        
        m_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, m_arrayList);
        
       
        

        m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
	                
	                
	                if(editTxt_Country.length() == 0)
	                {
	                	Toast toast = Toast.makeText(getApplicationContext(), "Country input has no input", Toast.LENGTH_SHORT);
						toast.show();
	                }
	                
	                else if (editTxt_Year.length() == 0)
	                {
	                	Toast toast = Toast.makeText(getApplicationContext(), "Year input has no input", Toast.LENGTH_SHORT);
						toast.show();
	                }
	                
	                else
	                {
	                	if(editTxt_Year.length() < 4)
	                	{
	                		Toast toast = Toast.makeText(getApplicationContext(), "Year has less than 4 number input", Toast.LENGTH_SHORT);
	    					toast.show();
	                	}
	                	else
	                	{
		                	m_arrayList.add(editTxt_Country.getText().toString());
			                m_arrayList.add(editTxt_Year.getText().toString());
			                DataArrayHandler.SetArrayList(m_arrayList);
			                m_adapter.notifyDataSetChanged();
			                
			                Intent intent = new Intent(add_activity.this, MainActivity_Countries.class);
			        	    startActivity(intent);
	                	}
	                }
                }
                catch(Exception e)
                {
                	
                }
               
                
            }
        });
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
		if (id == R.id.action_add) {
            Intent productIntent = new Intent(this,MainActivity_Countries.class);
            startActivity(productIntent);
            return true;
        }
		
		
		return super.onOptionsItemSelected(item);
	}

	
	
}
