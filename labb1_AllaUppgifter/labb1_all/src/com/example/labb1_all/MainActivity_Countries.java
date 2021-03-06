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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity_Countries extends Activity {

		private ListView list;
		private static ArrayAdapter<String> adapter;
		private static ArrayList<String> arrayList;
	
	 	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.activity_countries);
	        
	        
	        
	        list = (ListView) findViewById(R.id.listViewCountry_Year);
	        arrayList = DataArrayHandler.GetArrayList();
	        
	        System.out.println(arrayList);
	        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
	        
	        // Here, you set the data in your ListView
	        if(arrayList == null){
	        	
	        }
	        else{
	        	list.setAdapter(adapter);
	        	DataArrayHandler.SetArrayList(arrayList);
	        }
	        
	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.menu_countries, menu);
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
	            Intent productIntent = new Intent(this,add_activity.class);
	            startActivity(productIntent);
	            return true;
	        }
	        
	        return super.onOptionsItemSelected(item);
	    }

}
