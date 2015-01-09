package com.example.labb3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainCitymap extends Activity {

	ArrayList<String> m_towns = new ArrayList<String>();
	private static Toast m_toastDistance;
	
	private Marker m_marker;
	private GoogleMap m_googleMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_citymap);
		
         m_googleMap = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

         LatLng ZoomInStartPos = new LatLng(57.851972, 14.883574);
	        m_googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ZoomInStartPos, 6));
   
        try {
        	String lines = "";
        	
        	AssetManager m_assetManager = this.getAssets();
        	
			InputStream txtFile = m_assetManager.open("citys.txt");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(txtFile, "UTF-8"));
			
			while((lines = br.readLine()) != null){
				
				m_towns.add(lines);
				
				String[] m_rows = DoSplite(lines);
				String m_latitude = m_rows[0];
				String m_longitude = m_rows[1]; 
				String m_cityName = m_rows[2];
				
				LatLng m_position = new LatLng(Double.parseDouble(m_latitude),Double.parseDouble(m_longitude));
				m_googleMap.addMarker(new MarkerOptions()
		        .title(m_cityName)
		        .position(m_position));
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        m_googleMap.setOnCameraChangeListener(new OnCameraChangeListener() {
            public void onCameraChange(CameraPosition a_cameraPos) {  
            	boolean m_isCitySet = false;
            	double m_nearestCity = 0;
	    		if(m_toastDistance != null){
	    			m_toastDistance.cancel();
	    		}
	    		
	    		if(m_marker != null){
	    			m_marker.remove();
	    		}
	    		
	    		LatLng m_centerMarkerPos = new LatLng(a_cameraPos.target.latitude,a_cameraPos.target.longitude);
	    	
	    		
	    		m_toastDistance = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
	    		for(int i=0;i<m_towns.size();i++){
	    			
	    			String[] m_rows = DoSplite(m_towns.get(i));
					String m_latitude = m_rows[0];
					String m_longitude = m_rows[1]; 
					String m_cityName = m_rows[2];
					LatLng m_CityNow = new LatLng(Double.parseDouble(m_latitude),Double.parseDouble(m_longitude));
					double m_result = CalculationByDistance(m_centerMarkerPos, m_CityNow);
					
					//set city if it's the nearest one.
					if(  m_nearestCity > m_result || m_isCitySet == false){
						if(m_result <= 9){
							
							m_toastDistance.setText(m_cityName);
							
						}else{
							String m_stringResult = String.format("%.0f", m_result); 
							m_toastDistance.setText(m_stringResult+"km "+"until "+ m_cityName);
						}
						
						m_isCitySet = true;
						m_nearestCity = m_result;
					}
	    		}
	    		m_toastDistance.setDuration(9);
	    		m_toastDistance.show();
            }
        });

	}

	private String[] DoSplite(String a_rows){
		String[] m_rows = a_rows.split(",");
		return m_rows;
	}
	
	
	//function that calculates the distance between two points
			//taken from:
			//http://stackoverflow.com/questions/14394366/find-distance-between-two-points-on-map-using-google-map-api-v2
			public double CalculationByDistance(LatLng StartP, LatLng EndP) {
		        int Radius=6371;//radius of earth in Km         
		        double lat1 = StartP.latitude;
		        double lat2 = EndP.latitude;
		        double lon1 = StartP.longitude;
		        double lon2 = EndP.longitude;
		        double dLat = Math.toRadians(lat2-lat1);
		        double dLon = Math.toRadians(lon2-lon1);
		        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		        Math.sin(dLon/2) * Math.sin(dLon/2);
		        double c = 2 * Math.asin(Math.sqrt(a));
		        double valueResult= Radius*c;
		        double km=valueResult/1;
		        DecimalFormat newFormat = new DecimalFormat("####");
		        int kmInDec =  Integer.valueOf(newFormat.format(km));
		        double meter=valueResult%1000;
		        int  meterInDec= Integer.valueOf(newFormat.format(meter));
		        Log.i("Radius Value",""+valueResult+"   KM  "+kmInDec+" Meter   "+meterInDec);
		        return Radius * c;
		     }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_citymap, menu);
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
