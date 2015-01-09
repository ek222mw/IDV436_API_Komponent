package com.example.labb3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class IncomeCallsReceiver extends BroadcastReceiver {

	
	private IncomeCallsDataSource m_Callsdatasource;
	TelephonyManager m_telephonemangr;
	CallStateListr m_callStateLstr;
	
	@Override
	public void onReceive(Context a_context, Intent a_intent) {
		
		m_Callsdatasource = new IncomeCallsDataSource(a_context);
		m_Callsdatasource.open();
		
		Bundle m_bundle = a_intent.getExtras();
		if(null == m_bundle){
			return;
		}
		
		String m_state = m_bundle.getString(TelephonyManager.EXTRA_STATE);
		if(m_state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){	
			
		        m_telephonemangr = (TelephonyManager) a_context.getSystemService(Context.TELEPHONY_SERVICE);
		        m_callStateLstr = new CallStateListr();
		        m_telephonemangr.listen(m_callStateLstr, PhoneStateListener.LISTEN_CALL_STATE);
		}

	}
	  public class CallStateListr extends PhoneStateListener {
		  	@Override
	        public void onCallStateChanged(int a_state, String a_incomingNumber) {
		  		super.onCallStateChanged(a_state, a_incomingNumber);
		  		if( a_incomingNumber != null){
		  			switch (a_state) {
			            case TelephonyManager.CALL_STATE_RINGING:
			            	m_telephonemangr.listen(m_callStateLstr, PhoneStateListener.LISTEN_NONE);
			            	m_Callsdatasource.createNewNumber(a_incomingNumber);
			                break;
				  		case TelephonyManager.CALL_STATE_OFFHOOK:
				            break;
				  		case TelephonyManager.CALL_STATE_IDLE:
				            break;
			            default:
			            	break;
		  			}
		  		}
		  	}
	  }

	
}
