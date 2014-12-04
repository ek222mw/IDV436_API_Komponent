package com.example.labb1_all;

import java.util.List;

import com.example.labb1_all.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


	public class WeatherVaxjoAdapter extends ArrayAdapter {
		
		
		private final List<WeatherForecast> m_values;
		private final Context m_context;
		
		public WeatherVaxjoAdapter(Context a_context, List<WeatherForecast> a_list) {
			super(a_context, R.layout.list_weather, a_list);
			this.m_context = a_context;
			this.m_values = a_list;
		}
	 
		@Override
		public View getView(int a_position, View a_convertView, ViewGroup a_parent) {
			LayoutInflater inflater = (LayoutInflater) m_context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
			View rv = inflater.inflate(R.layout.list_weather, a_parent, false);
			TextView tv = (TextView) rv.findViewById(R.id.IDlabel);
			
			TextView tvDate = (TextView) rv.findViewById(R.id.Datelabel);
			TextView tvStopDate = (TextView) rv.findViewById(R.id.Stopdatelabel);
			TextView tvDegree = (TextView) rv.findViewById(R.id.Degreeslabel);
			TextView tvRain = (TextView) rv.findViewById(R.id.Rainlabel);
			TextView tvWind = (TextView) rv.findViewById(R.id.Windlabel);
			ImageView iv = (ImageView) rv.findViewById(R.id.logo);
			
			
			WeatherForecast m_wf = m_values.get(a_position);
			tv.setText(m_wf.getWeatherName());
			tvDate.setText("From: " + m_wf.getStartYYMMDD() + " " + m_wf.getStartHHMM());
			tvStopDate.setText("Until: " + m_wf.getEndYYMMDD() + " " + m_wf.getEndHHMM());
			tvDegree.setText(String.valueOf("Temp: " + m_wf.getTemp()));
			tvWind.setText(String.valueOf("Wind: " + m_wf.getWindSpeed() + "m/s"));	
			tvRain.setText(String.valueOf("Rain: " + m_wf.getRain() + "mm/h"));
				
			
			// Decides which picture to use depending on the WeatherCode we get.
			switch (m_wf.getWeatherCode()) {
		        case 1:
					iv.setImageResource(R.drawable.pic_1);
					break;
			    case 2:
					iv.setImageResource(R.drawable.pic_2);
					break;
			    case 3:
					iv.setImageResource(R.drawable.pic_3);
					break;
			    case 4:
					iv.setImageResource(R.drawable.pic_4);
					break;
			    case 5:
					iv.setImageResource(R.drawable.pic_5);
					break;
			    case 6:
					iv.setImageResource(R.drawable.pic_6);
					break;
			    case 7:
					iv.setImageResource(R.drawable.pic_7);
					break;
			    case 8:
					iv.setImageResource(R.drawable.pic_8);
					break;
			    case 9:
					iv.setImageResource(R.drawable.pic_9);
					break;
			    case 10:
					iv.setImageResource(R.drawable.pic_10);
					break;
			    case 11:
					iv.setImageResource(R.drawable.pic_11);
					break;
			    case 12:
					iv.setImageResource(R.drawable.pic_12);
					break;
			    case 13:
					iv.setImageResource(R.drawable.pic_13);
					break;
			    case 14:
					iv.setImageResource(R.drawable.pic_14);
					break;
			    case 15:
					iv.setImageResource(R.drawable.pic_15);
		            break;
			}
	 
			return rv;
		}
	}


