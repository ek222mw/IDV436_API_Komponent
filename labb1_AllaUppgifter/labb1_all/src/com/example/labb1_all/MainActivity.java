package com.example.labb1_all;

import java.util.ArrayList;

import com.example.labb1_all.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ListActivity {

	ArrayList<Intent> m_intentsList;
	ArrayList<String> m_list;
	
	ListView m_lv;
	ArrayAdapter<String> m_adapter;
	Intent m_randomIntent, m_bmiIntent, m_colorIntent, m_countriesIntent, m_weather;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        
        m_lv = (ListView) findViewById(android.R.id.list);
		
	    m_list = new ArrayList<String>();
	    m_intentsList = new ArrayList<Intent>();
        
        // activities to run
	    Intent m_colorIntent = new Intent(this, MainActivity_Colours.class);
        Intent m_countriesIntent = new Intent(this, MainActivity_Countries.class);
        Intent m_weather = new Intent(this, VaxjoWeather.class);
        Intent m_randomIntent = new Intent(this, MainActivity_Random.class);
        Intent m_bmiIntent = new Intent(this, MainActivity_BMI.class);
        
        String[] m_name = {"RandomNumber", "BMI calc", "Color",
	    		"Countries", "Weather"};
	    
        
	    Intent[] m_intent = {m_randomIntent, m_bmiIntent, m_colorIntent,
	    		m_countriesIntent, m_weather};
	    
	    for(int i = 0; i < m_name.length; i++) {
 		   m_list.add(i,m_name[i]);
 		   m_intentsList.add(i, m_intent[i]);
 	   	}
	    
	    m_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, m_list);
	    m_lv.setAdapter(m_adapter);
	    
 	   	m_adapter.notifyDataSetChanged(); 	   	
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    protected void onListItemClick(ListView a_lv, View a_v, int a_pos, long a_id) { 
		//get clicked item		
		startActivity(m_intentsList.get(a_pos));
	}
    
}
