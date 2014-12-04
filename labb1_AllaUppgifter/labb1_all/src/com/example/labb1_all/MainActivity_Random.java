package com.example.labb1_all;

import com.example.labb1_all.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.Toast;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;



public class MainActivity_Random extends Activity {

	private TextView display;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        
    }
    
    
   


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random, menu);
       

        setContentView(R.layout.activity_random);

        final Button button = (Button) findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	TextView tv = (TextView) findViewById(R.id.randomInt);
            	double d;
        		double randNumber = Math.random();
        		d = randNumber * 100;

        		int randomInt = (int)d + 1;
        		
        		tv.setText(Integer.toString(randomInt));
        		
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