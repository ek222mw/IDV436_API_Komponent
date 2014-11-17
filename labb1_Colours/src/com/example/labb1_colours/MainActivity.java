package com.example.labb1_colours;

import android.app.Activity;
import android.graphics.Color;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        setContentView(R.layout.activity_main);
        
        final Button button = (Button) findViewById(R.id.Button_Send);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	EditText et_red = (EditText) findViewById(R.id.edit_text_red);
            	EditText et_green = (EditText) findViewById(R.id.edit_text_green);
            	EditText et_blue = (EditText) findViewById(R.id.edit_text_blue);
            	
            	TextView tv = (TextView) findViewById(R.id.result);
            	
            	int inputRed = Integer.valueOf(et_red.getText().toString());
            	int inputGreen = Integer.valueOf(et_green.getText().toString());
            	int inputBlue = Integer.valueOf(et_blue.getText().toString());
            	
            	
            	//int color = Color.rgb(inputRed, inputGreen, inputBlue);
            	
            	
            	
            	tv.setBackgroundColor(Color.rgb(inputRed, inputGreen, inputBlue));
            	
            	
        		
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
