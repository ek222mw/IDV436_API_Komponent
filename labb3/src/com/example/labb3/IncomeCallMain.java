package com.example.labb3;



import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IncomeCallMain extends ListActivity {
	private IncomeCallsDataSource m_callsDatasource;
	private List m_callList;
	private ListView m_numberList;
	private ArrayAdapter<String> m_adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_calls);
		if(TelephoneCheck()){
			GetCallList();
		}else{
			TextView noTelephony = (TextView)findViewById(R.id.textView1);
			noTelephony.setVisibility(View.VISIBLE);
		}
	}
	
	public void GetCallList(){
		m_callsDatasource = new IncomeCallsDataSource(this);
		m_callsDatasource.open();
		
		m_callList = m_callsDatasource.GetAllCallNumbers();
		m_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,m_callList);
		
		m_numberList = (ListView)findViewById(R.id.number_list);
		m_numberList.setAdapter(m_adapter);
		
		registerForContextMenu(m_numberList);
	}
	 @Override  
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
    	super.onCreateContextMenu(menu, v, menuInfo);  
    	if (v.getId()==R.id.number_list) {
    		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
    		menu.setHeaderTitle(m_callList.get(info.position).toString());
    		menu.add(0, 0, 0, "Call");
    		menu.add(0, 1, 0, "Message");
	    }
    }
	 @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
      if (item.getItemId() == 0) 
      { 
    	  //phonecall
    	  String m_str = m_callList.get(info.position).toString();
    	  String m_toCall="tel:"+m_str;
    	  startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(m_toCall)));
      }else if(item.getItemId() == 1)
      {
    	  
    	  //sms
    	  	String m_str = m_callList.get(info.position).toString();
        	Intent m_Intent =new Intent(Intent.ACTION_SEND);
        	m_Intent.setType("text/plain");
        	m_Intent.putExtra(Intent.EXTRA_TEXT, m_str);
        	m_Intent.putExtra(Intent.EXTRA_PHONE_NUMBER,m_str);
        	m_Intent.putExtra("sms_body", m_str);
        	startActivity(Intent.createChooser(m_Intent, "Share"));
      }
      return true;
    }
	private class ItemClick implements OnItemClickListener {
        @Override
		public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
        {
        	CharSequence m_charSeq = ((TextView) view).getText(); 
        	Intent m_Intent = new Intent(Intent.ACTION_SEND);
        	m_Intent.setType("text/plain");
        	m_Intent.putExtra(Intent.EXTRA_TEXT, m_charSeq);
        	m_Intent.putExtra(Intent.EXTRA_PHONE_NUMBER,m_charSeq);
        	m_Intent.putExtra("sms_body", m_charSeq);
        	startActivity(Intent.createChooser(m_Intent, "Share"));
       }
    }
	
	private boolean TelephoneCheck(){
		PackageManager pm = getPackageManager();
		boolean telephonySupported =
		pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
		boolean gsmSupported =
		pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA);
		boolean cdmaSupported =
		pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM);
		if(telephonySupported || cdmaSupported || gsmSupported){
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_calls, menu);
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
