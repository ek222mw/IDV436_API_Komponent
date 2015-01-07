package com.example.labb3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.labb3.IncomeCallMain;


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
import android.widget.ListView;


public class MainActivity extends ListActivity {

	
	private List<String> activities = new ArrayList<String>();
	private Map<String,Class> name2class = new HashMap<String,Class>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //setContentView(R.layout.activity_main_list);
        setup_activities();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activities));
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClick());
    }
    private class OnItemClick implements OnItemClickListener{
    	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
    		String activity_name = activities.get(position);
    		Class activity_class = name2class.get(activity_name);
    		
    		Intent intent = new Intent(MainActivity.this,activity_class);
    		MainActivity.this.startActivity(intent);
    		
    	}
    }
    private void setup_activities(){
    	addActivity("Exercise 2 Call History", IncomeCallMain.class);
    	//addActivity("Exercise 3 City Map", MainCityMap.class);
    }
    private void addActivity(String name, Class activity){
    	activities.add(name);
    	name2class.put(name,activity);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    
}
