package com.example.labb2;

import java.util.ArrayList;
import java.util.List;

import com.example.labb2.add_activity;
import com.example.labb2.DataArrayHandler;
import com.example.labb2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class PersitentStorage_Countries extends Activity {

	private CountryDataSource datasource;
	private List<Country> values;
	private ArrayAdapter<Country> listAdapter;
	private add_activity add_activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_persitent_storage__countries);

		datasource = new CountryDataSource(this);
		datasource.open();

		// get all tasks
		values = datasource.getAllTasks();

		// fill ListView with elements
		ListView list = (ListView) findViewById(R.id.list);
		listAdapter = new ArrayAdapter<Country>(this,
				android.R.layout.simple_list_item_1, values);
		list.setAdapter(listAdapter);
		registerForContextMenu(list);
		// listAdapter.notifyDataSetChanged();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.persitent_storage__countries, menu);
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
		// A new activity starts when the Add button is pressed in the action
		// bar
		if (id == R.id.action_add) {
			Intent productIntent = new Intent(this, add_activity.class);
			startActivityForResult(productIntent, 0);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		if (v.getId() == R.id.list) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			menu.setHeaderTitle(values.get(info.position).toString());
			menu.add(0, 0, 0, "Delete");
			menu.add(0, 1, 0, "Edit");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		if (item.getItemId() == 0) { // delete task
			Country country = values.get(info.position);
			datasource.deleteTask(country);
			listAdapter.remove(country);

		} else if (item.getItemId() == 1) {
			Country country = values.get(info.position);
			Intent productIntent = new Intent(this, add_activity.class);
			productIntent.putExtra("id", country.getId());
			productIntent.putExtra("country", country.getTask());
			productIntent.putExtra("year", country.getYear());
			startActivityForResult(productIntent, 1);
			add_activity = new add_activity();

			return true;
			// datasource.updateTask(country);
			// listAdapter.notifyDataSetChanged();
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		datasource.open();
		long id = data.getLongExtra("id", -1);
		String c = data.getStringExtra("country");
		String y = data.getStringExtra("year");
		
		if (requestCode == 1 && resultCode == RESULT_OK) {
			datasource.updateTask(new Country(id, c, y));
		} else {
			listAdapter.add(datasource.createTask(c, y));
		}

        listAdapter.notifyDataSetChanged();
	}

}
