package com.example.labb1_all;

import com.example.labb1_all.R;

import android.app.Activity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity_BMI extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
        
        setContentView(R.layout.activity_bmi);
        
        final Button button = (Button) findViewById(R.id.Button_Send);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	EditText et_Length = (EditText) findViewById(R.id.edit_text);
            	EditText et_Weight = (EditText) findViewById(R.id.edit_text_Weight);
            	
            	TextView tv = (TextView) findViewById(R.id.result);
            	
            	try
            	{
            		if(et_Length.length() == 0)
            		{
            			Toast toast = Toast.makeText(getApplicationContext(), "Length has no input ", Toast.LENGTH_SHORT);
						toast.show();
            		}
            		else if(et_Weight.length() == 0)
            		{
            			Toast toast = Toast.makeText(getApplicationContext(), "Weight has no input ", Toast.LENGTH_SHORT);
						toast.show();
            		}
            		else{
            			
		            	int Length = Integer.valueOf(et_Length.getText().toString());
		            	float meterLength = Length / 100.0f;
		            	
		            	float Weigth = Float.valueOf(et_Weight.getText().toString());
		            	float bmi = Math.round((Weigth / (meterLength*meterLength))*10)/10.0f;
            		
		            	if(Weigth <= 0.0f)
		            	{
			            	Toast toast = Toast.makeText(getApplicationContext(), "Weight was 0kg ", Toast.LENGTH_SHORT);
							toast.show();
		            	}
		            	else if(Length <= 0.0f)
		            	{
		            		Toast toast = Toast.makeText(getApplicationContext(), "Length was 0cm ", Toast.LENGTH_SHORT);
							toast.show();
		            	}
		            	else if(Length > 300.0f)
		            	{
		            		Toast toast = Toast.makeText(getApplicationContext(), "Length was too high", Toast.LENGTH_SHORT);
							toast.show();
		            	}
		            	else
		            	{
		            		tv.setText(Float.toString(bmi));
		            	}
            		
            		
            		}
            		
            		
	            	
	            	
	            	
	            	
            	}
            	catch (Exception e)
            	{
            		
            	}
        		
        		
        		
            }
        });
        
        final Button resetbutton = (Button) findViewById(R.id.Reset);
        resetbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	EditText et_Length = (EditText) findViewById(R.id.edit_text);
            	et_Length.setText("");
            	EditText et_Weight = (EditText) findViewById(R.id.edit_text_Weight);
            	et_Weight.setText("");
            	
            	TextView tv = (TextView) findViewById(R.id.result);
            	
            	float bmi = 0;
        		
        		tv.setText(Float.toString(bmi));
        		
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
}
