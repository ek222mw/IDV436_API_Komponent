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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;


public class AlarmClock extends Activity {

	
	BroadcastReceiver m_BrReceiver;
    PendingIntent m_pendIntent;
    AlarmManager m_alarmManager;
    SharedPreferences m_sharedPrefs;
    String m_dialogCloseStr = "Close";
	static TextView m_CurrentTime;
	int m_TimePeriod = 5000;
	TimePicker m_timePicker;
	int m_alarmHr;
	int m_alarmMin;
	boolean m_isAlarmOn = false;
	private final String m_alarmManagerAction = "alarmManagerAction";
	
	
   

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_stop_alarm);
		m_CurrentTime = (TextView) findViewById(R.id.textViewTimeNow);
		m_sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		m_timePicker = (TimePicker) findViewById(R.id.timePicker);
		m_timePicker.setIs24HourView(true);			

		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				Message message = new Message();
				TimeMessageHandler.sendMessage(message);
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, m_TimePeriod);
	}
	
	//When click button set alarm.
	public void onClick(View view) {
		switch (view.getId()) {
	        case R.id.buttonSetAlarm:
	        	
	        	AlarmSet();
	    		break;
	        
		}
		
		
    }
	
	
	
	// Displays the current time on display.
	public static void showCurrentTime() {
		final Calendar m_calendar = Calendar.getInstance();
		final int m_hour = m_calendar.get(Calendar.HOUR_OF_DAY);
		
		final int m_sec = m_calendar.get(Calendar.SECOND);		
		
		final int m_min = m_calendar.get(Calendar.MINUTE);
		
		// set time right now into textview
		m_CurrentTime.setText(new StringBuilder()
				.append(DoSetOneNrFormat(m_hour))
				.append(".").append(DoSetOneNrFormat(m_min))
				.append(".").append(DoSetOneNrFormat(m_sec)));
	}
	
	//If number is < 10 add 0 before for correct format
	private static String DoSetOneNrFormat(int a_time) {
		if (a_time > 9)
		   return String.valueOf(a_time);
		else
		   return "0" + String.valueOf(a_time);
	}
	
	//Sets alarm, if time has passed for today, set date tomorrow instead
	public void AlarmSet() {
		Calendar m_CalendarNow = Calendar.getInstance();
		Date m_TimeNow = m_CalendarNow.getTime();
		
		//Gets the alarm time from the timepicker.
		m_alarmHr  = m_timePicker.getCurrentHour();
    	m_alarmMin = m_timePicker.getCurrentMinute();
		
		Calendar m_Calendar = Calendar.getInstance();
        m_Calendar.set(Calendar.HOUR_OF_DAY, m_alarmHr);
        m_Calendar.set(Calendar.MINUTE, m_alarmMin);
        m_Calendar.set(Calendar.SECOND, 00);//Set alarm seconds to zero
        
		long m_time = m_Calendar.getTimeInMillis();
		Date alarmDate = m_Calendar.getTime();		
		
		
		
		
		if(alarmDate.after(m_TimeNow)) {
			
			setAlarm(m_time);
			
			if(m_isAlarmOn) {
				openDialog("Notefication", "The alarm is changed to \n" + m_Calendar.getTime(), m_dialogCloseStr);
			} else {
				openDialog("Notefication", "Alarm set to \n" + m_Calendar.getTime(), m_dialogCloseStr);
			}
			m_isAlarmOn = true;
		} 
		else if(m_TimeNow.equals(alarmDate) || m_TimeNow.after(alarmDate)) {
			//If time has passed, set alarm the next day instead.
			
			
			m_Calendar.add(Calendar.DATE, 1);//add one day to the calendar
			m_time = m_Calendar.getTimeInMillis();
		
			setAlarm(m_time);
			
			if(m_isAlarmOn) {
				openDialog("Notefication", "The alarm time was changed to \n" + m_Calendar.getTime(), m_dialogCloseStr);
			} else {
				openDialog("Notefication", "Alarm time set to \n" + m_Calendar.getTime(), m_dialogCloseStr);
			}
			m_isAlarmOn = true;
		}
	}
	
    //Set alarm time
    protected void setAlarm(long a_alarmTime) {		
    	IntentFilter m_intentFilter = new IntentFilter();
        m_intentFilter.addAction(m_alarmManagerAction);
        if (this.m_BrReceiver == null) {
            this.m_BrReceiver = new BroadcastReceiver() {
				@Override
                public void onReceive(Context context, Intent intent) {
                    Intent m_alarmIntent = new Intent(context, StartStopAlarm.class);
                    ((Activity)context).startActivityForResult(m_alarmIntent, 4);
                }
            };
            this.registerReceiver(this.m_BrReceiver, m_intentFilter);
            m_pendIntent = PendingIntent.getBroadcast(this, 0, new Intent(m_alarmManagerAction),0 );
            
        }
        m_alarmManager = (AlarmManager)(this.getSystemService(Context.ALARM_SERVICE));
        m_alarmManager.set(AlarmManager.RTC_WAKEUP, a_alarmTime, m_pendIntent);
    }
	
    //Opens a dialog with information
    public void openDialog(String title, String message, String button) {
    	new AlertDialog.Builder(this).setTitle(title).setMessage(message)
    	    .setNegativeButton(button, new DialogInterface.OnClickListener() {
    	        public void onClick(DialogInterface dialogInterface, int which) { 
    	        	dialogInterface.cancel();
    	        }
    	    }).show();
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {   
		if(requestCode == 4) {
			if(resultCode == RESULT_OK) {
				m_isAlarmOn = false;
				openDialog("ALARM", "Alarm is now off.", m_dialogCloseStr);
			}
		}
	}
    
    static Handler TimeMessageHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			showCurrentTime();
		}
	};
	
	
    
   
}
