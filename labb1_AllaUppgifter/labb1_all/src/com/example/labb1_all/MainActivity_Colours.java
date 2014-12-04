package com.example.labb1_all;

import com.example.labb1_all.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.SensorManager;

import android.view.OrientationEventListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_Colours extends Activity {

	public int color;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_colours);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_colours, menu);
		// Checks the orientation of the screen

		final Button button = (Button) findViewById(R.id.Button_Send);
		tv = (TextView) findViewById(R.id.result);
		
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				EditText et_red = (EditText) findViewById(R.id.edit_text_red);
				EditText et_green = (EditText) findViewById(R.id.edit_text_green);
				EditText et_blue = (EditText) findViewById(R.id.edit_text_blue);

				
				try {
					
					if(et_red.length() == 0)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Color red has no input", Toast.LENGTH_SHORT);
						toast.show();
					}
					else if(et_green.length() == 0)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Color green has no input", Toast.LENGTH_SHORT);
						toast.show();
					}
					
					else if(et_blue.length() == 0)
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Color blue has no input", Toast.LENGTH_SHORT);
						toast.show();
					}
					else
					{
						int inputRed = Integer.valueOf(et_red.getText().toString());
						int inputGreen = Integer.valueOf(et_green.getText()
								.toString());
						int inputBlue = Integer.valueOf(et_blue.getText()
								.toString());
					
						if (inputRed < 0) {
							
							Toast toast = Toast.makeText(getApplicationContext(), "Color red input was lower then 0", Toast.LENGTH_SHORT);
							toast.show();
						}
						else if (inputRed > 255) {
							
							Toast toast = Toast.makeText(getApplicationContext(), "Color red input was higher then 255", Toast.LENGTH_SHORT);
							toast.show();
						}
						else if (inputGreen < 0) {
							
							Toast toast = Toast.makeText(getApplicationContext(), "Color green input was lower then 0", Toast.LENGTH_SHORT);
							toast.show();
						}
						else if (inputGreen > 255) {
							
							Toast toast = Toast.makeText(getApplicationContext(), "Color green input was higher then 255", Toast.LENGTH_SHORT);
							toast.show();
						}
						else if (inputBlue < 0) {
							
							Toast toast = Toast.makeText(getApplicationContext(), "Color blue input was lower then 0", Toast.LENGTH_SHORT);
							toast.show();
						}
						else if (inputBlue > 255) {
							
							Toast toast = Toast.makeText(getApplicationContext(), "Color blue input was higher then 255", Toast.LENGTH_SHORT);
							toast.show();
						}
						else{
							
							color = Color.rgb(inputRed, inputGreen, inputBlue);
							
							tv.setBackgroundColor(color);
						
						}
					
					
					}
					
					
				} catch (Exception e) {

					
					
					
				}
				
				
			}
		});

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
	
	
	public void onSaveInstanceState(Bundle savedInstanceState){
		
		super.onSaveInstanceState(savedInstanceState);
		
		savedInstanceState.putInt("Color", color);
	}
	
	public void onRestoreInstanceState(Bundle savedInstanceState){
		
		super.onRestoreInstanceState(savedInstanceState);
		
		int saveInt = savedInstanceState.getInt("Color");
		tv.setBackgroundColor(saveInt);
		
	}
	
}
