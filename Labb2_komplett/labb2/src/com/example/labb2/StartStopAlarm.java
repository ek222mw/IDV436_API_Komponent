package com.example.labb2;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.labb2.R;

public class StartStopAlarm extends Activity {

	private MediaPlayer m_mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
	
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
	            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
	            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
	            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		m_mediaPlayer = MediaPlayer.create(this, R.raw.alarmsong);
		m_mediaPlayer.start();
	}
	
	//Function to stop the alarm
    public void onClick(View view) {
    	Intent intent = new Intent();
		switch (view.getId()) {
	        case R.id.button_stop_alarm:
	        	m_mediaPlayer.stop();
	    		setResult(RESULT_OK, intent);
		        finish();
		        return;
		}
    }
}
