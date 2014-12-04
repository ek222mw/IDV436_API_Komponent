/**
 * VaxjoWeather.java
 * Created: May 9, 2010
 * Jonas Lundberg, LnU
 */

package com.example.labb1_all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.example.labb1_all.R;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

/**
 * This is a first prototype for a weather app. It is currently 
 * only downloading weather data for Växjö. 
 * 
 * This activity downloads weather data and constructs a WeatherReport,
 * a data structure containing weather data for a number of periods ahead.
 * 
 * The WeatherHandler is a SAX parser for the weather reports 
 * (forecast.xml) produced by www.yr.no. The handler constructs
 * a WeatherReport containing meta data for a given location
 * (e.g. city, country, last updated, next update) and a sequence 
 * of WeatherForecasts.
 * Each WeatherForecast represents a forecast (weather, rain, wind, etc)
 * for a given time period.
 * 
 * The next task is to construct a list based GUI where each row 
 * displays the weather data for a single period.
 * 
 *  
 * @author jlnmsi
 *
 */

public class VaxjoWeather extends ListActivity {
	private InputStream m_input;
	private WeatherReport m_report = null;
	
	List<WeatherForecast> m_list;
	ListView m_listview;
	WeatherVaxjoAdapter m_adapter;
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.activity_weather);
        m_listview = (ListView) findViewById(android.R.id.list);
		
	    m_list = new ArrayList<WeatherForecast>();
	    m_list.clear();
        
        m_adapter = new WeatherVaxjoAdapter(this, m_list);        
        m_listview.setAdapter(m_adapter);
        
        
        try {
        	URL url = new URL("http://www.yr.no/sted/Sverige/Kronoberg/V%E4xj%F6/forecast.xml");
        	AsyncTask task = new WeatherRetriever().execute(url);
        } catch (IOException ioe ) {
    		ioe.printStackTrace();
    	}
        
    }
    
    private void PrintReportToConsole() {
    	if (this.m_report != null) {
        	/* Print location meta data */ 
        	System.out.println(m_report);
        	
        	/* Print forecasts */
    		int count = 0;
    		for (WeatherForecast forecast : m_report) {
    			count++;
    			System.out.println("Forecast "+count);
    			System.out.println( forecast.toString() );
    			
    			m_list.add(forecast);
    		}
    		m_adapter.notifyDataSetChanged();
    		
    	}
    	else {
    		System.out.println("Weather report has not been loaded.");
    	}
    }
    
    private class WeatherRetriever extends AsyncTask<URL, Void, WeatherReport> {
    	protected WeatherReport doInBackground(URL... urls) {
    		try {
    			return WeatherHandler.getWeatherReport(urls[0]);
    		} catch (Exception e) {
    			throw new RuntimeException(e);
    		} 
    	}

    	protected void onProgressUpdate(Void... progress) {

    	}

    	protected void onPostExecute(WeatherReport result) {
    		m_report = result;
    		PrintReportToConsole();
    	}
    }
}