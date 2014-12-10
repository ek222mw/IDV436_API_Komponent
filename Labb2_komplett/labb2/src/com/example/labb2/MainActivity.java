package com.example.labb2;

import java.util.ArrayList;

import com.example.labb2.R;

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
	Intent m_CountriesIntent, m_AlarmIntent;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        
        m_lv = (ListView) findViewById(android.R.id.list);
		
	    m_list = new ArrayList<String>();
	    m_intentsList = new ArrayList<Intent>();
        
        // activities to run
        Intent m_CountriesIntent = new Intent(this, PersitentStorage_Countries.class);
        Intent m_AlarmIntent = new Intent(this, AlarmClock.class);
        
        String[] m_name = {"Countries", "AlarmClock"};
	    
        
	    Intent[] m_intent = {m_CountriesIntent, m_AlarmIntent};
	    
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
