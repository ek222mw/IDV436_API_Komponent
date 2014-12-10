package com.example.labb2;

import java.util.ArrayList;
import java.util.List;

import com.example.labb2.add_activity;

import com.example.labb2.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



	@SuppressLint("ResourceAsColor")
	public class PersitentStorage_Countries extends Activity {
		ArrayList<String> m_list;
		ListView m_lv;
		ArrayAdapter<String> m_arrayAdapter;
		CountryDataSource m_cDataSource;
		String m_orderBy;
		private int m_clickedItem;
		SharedPreferences m_sharedPrefs;
		View m_colorLayout;
		String m_color;
		String m_textSize;
		TextView m_tvSize;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			m_sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
			setContentView(R.layout.activity_persitent_storage__countries);
			
			m_lv = (ListView) findViewById(R.id.listViewCountries);
			registerForContextMenu(m_lv);

			m_cDataSource = new CountryDataSource(this);
			m_cDataSource.Isopen();
			
			m_orderBy = m_sharedPrefs.getString("sortBy", "year");
			m_list = (ArrayList<String>) m_cDataSource.getAllCountries(m_orderBy);
			
			m_lv.setAdapter(m_arrayAdapter = new ArrayAdapter<String>(this, R.layout.layout_row, m_list) {
			    @Override
			    public View getView(int position, View row, ViewGroup parent) {
			    	String data = getItem(position);
			    	row = getLayoutInflater().inflate(R.layout.layout_row, parent, false);
			    	m_tvSize = (TextView) row.findViewById(R.id.label);
			    	m_textSize = m_sharedPrefs.getString("textSizePref", "1");
					
			    	if(m_textSize.equals("1")) {
						m_tvSize.setTextSize(11);
					} else if(m_textSize.equals("2")) {
						m_tvSize.setTextSize(20);
					} else if(m_textSize.equals("3")) {
						m_tvSize.setTextSize(27);
					}		    	
			    	m_tvSize.setText(data);
			        return m_tvSize;
			    }		    
			});

			m_colorLayout = (RelativeLayout)findViewById(R.id.laidout);		
			m_color = m_sharedPrefs.getString("colorPref", "1");
			BackgroundColorSet(m_color);
		}
		
		

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.persitent_storage__countries, menu);
			return true;
		}
		
		@Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	        	case R.id.action_settings:
	        		Intent settings = new Intent(this, com.example.labb2.menuSettings.ActivitySettings.class);
	        		startActivity(settings);
	        		return true;
	            case R.id.order_by_year:
	            	m_orderBy = "year";
	            	saveSortOrderBy(m_orderBy);
	                SortOrderBy(m_orderBy);
	                return true;
	                
	           case R.id.order_by_year_DESC:
	            	m_orderBy = "yearDESC";
	            	saveSortOrderBy(m_orderBy);
	                SortOrderBy(m_orderBy);
	                return true;
	                
	            case R.id.order_by_name:
	            	m_orderBy = "name";
	            	saveSortOrderBy(m_orderBy);
	            	SortOrderBy(m_orderBy);
	                return true;
	                
	            case R.id.order_by_name_DESC:
	            	m_orderBy = "nameDESC";
	            	saveSortOrderBy(m_orderBy);
	            	SortOrderBy(m_orderBy);
	                return true;
	                
	            case R.id.action_add:
	            	Intent addNewCountryIntent = new Intent(this, add_activity.class);
	    			startActivityForResult(addNewCountryIntent, 1);
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
		
		
		@Override
		public void onCreateContextMenu(ContextMenu a_menu, View a_view,
				ContextMenuInfo a_menuInfo) {
			// TODO Auto-generated method stub
			super.onCreateContextMenu(a_menu, a_view, a_menuInfo);
			AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) a_menuInfo;
			m_clickedItem = acmi.position;
			String m_item = m_arrayAdapter.getItem(m_clickedItem);
			a_menu.setHeaderTitle("Edit or delete " + m_item);
			a_menu.add(1,1,1,"Edit");
			a_menu.add(1,2,2, "Delete");
		}
		
		@Override
		public boolean onContextItemSelected(MenuItem a_item) {
			// TODO Auto-generated method stub
			String m_title = (String) a_item.getTitle();
			String[] rString = m_arrayAdapter.getItem(m_clickedItem).split("  ");
			
			int m_year = Integer.parseInt(rString[0]);
			
			String m_country = rString[1];
			if(m_title.equals("Edit")) {
				editCountryYear(m_year,m_country);
			} else if(m_title.equals("Delete")){
				m_cDataSource.DeleteCountry(m_year,m_country);
				SortOrderBy(m_orderBy);
			}
			return true;
		}
		
		
		public void editCountryYear(int a_year, String a_country) {
			String[] m_values = new String[2];
			m_values[0] = a_year+"";
	    	m_values[1] = a_country;
			Intent addNewCountryIntent = new Intent(this, add_activity.class);
			addNewCountryIntent.putExtra("countryToEdit", m_values);
			addNewCountryIntent.putExtra("RequestCode", 2);
			startActivityForResult(addNewCountryIntent, 2);
			SortOrderBy(m_orderBy);
		}
		
		
		public void SortOrderBy(String a_orderBy) {
			m_list.clear();
	        m_list.addAll(m_cDataSource.getAllCountries(a_orderBy));
	        m_arrayAdapter.notifyDataSetChanged();
		}
		
		@SuppressLint("NewApi")
		public void saveSortOrderBy(String orderBy) {
			SharedPreferences.Editor edit = m_sharedPrefs.edit();
			m_sharedPrefs.edit().putString("sortBy", orderBy).commit();
		}
		
		@Override
		protected void onSaveInstanceState(Bundle a_outState) {
			// TODO Auto-generated method stub
			a_outState.putStringArrayList("countries", m_list);
			a_outState.putString("colorPref", m_color);
			a_outState.putString("textSizePref", m_textSize);
			saveSortOrderBy(m_orderBy);
			super.onSaveInstanceState(a_outState);
		}

		@Override
		public void onRestoreInstanceState(Bundle savedInstanceState) {
			m_list = savedInstanceState.getStringArrayList("countries");
			m_textSize = savedInstanceState.getString("textSizePref");
			m_color = savedInstanceState.getString("colorPref");
			BackgroundColorSet(m_color);
			super.onRestoreInstanceState(savedInstanceState);
		}
		
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			m_color = m_sharedPrefs.getString("colorPref", m_color);
			BackgroundColorSet(m_color);
			m_orderBy = m_sharedPrefs.getString("sortBy", m_orderBy);		
			m_textSize = m_sharedPrefs.getString("textSizePref", m_textSize);
			m_arrayAdapter.notifyDataSetChanged();
			super.onResume();
		}
		
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			saveSortOrderBy(m_orderBy);
			super.onPause();
		}

		
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			   
			// Add new country
			if(requestCode == 1) {
				if(resultCode == RESULT_OK) {
					String[] message = data.getStringArrayExtra("MESSAGE");
					m_list.add(message[0] + "  " + message[1]);
					m_cDataSource.doSaveCountry(Integer.parseInt(message[0]), message[1]);
	    			SortOrderBy(m_orderBy);

				} else if(resultCode == RESULT_CANCELED) {

				}
				
			// Edit country
			} else if(requestCode == 2) {
				if(resultCode == RESULT_OK) {
					String[] message = data.getStringArrayExtra("MESSAGE");
					String[] oldCountry = data.getStringArrayExtra("oldCountry");
					m_cDataSource.updateDataBase(oldCountry[0], oldCountry[1], message[0], message[1]);
	    			SortOrderBy(m_orderBy);

				} else if(resultCode == RESULT_CANCELED) {

				}
			}
		}

		public void BackgroundColorSet(String color) {
			if(color.equals("1")) {
				m_colorLayout.setBackgroundColor(Color.WHITE);
			} else if(color.equals("2")) {
				m_colorLayout.setBackgroundColor(Color.MAGENTA);
			} else if(color.equals("3")) {
				m_colorLayout.setBackgroundColor(Color.RED);
			} else if(color.equals("4")) {
				m_colorLayout.setBackgroundColor(Color.GREEN);
			} else if(color.equals("5")) {
				m_colorLayout.setBackgroundColor(Color.BLUE);
			} else {
				m_colorLayout.setBackgroundColor(Color.WHITE);
			}
		}
	}
		