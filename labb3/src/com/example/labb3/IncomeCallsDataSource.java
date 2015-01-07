package com.example.labb3;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class IncomeCallsDataSource {

	
	private String[] m_Columns = {IncomeCallsDbHelper.COLUMN_ID,IncomeCallsDbHelper.COLUMN_NUMBER};
	private SQLiteDatabase m_database;
	
	private IncomeCallsDbHelper m_IncomeCallDbHelper;

	
	public IncomeCallsDataSource(Context a_context){
		m_IncomeCallDbHelper = new IncomeCallsDbHelper(a_context);
	}
	
	public void close(){
		m_IncomeCallDbHelper.close();
	}
	
	public void open(){
		m_database = m_IncomeCallDbHelper.getWritableDatabase();
	}
	
	//remove db during devolopment
		public void RemoveDataFromTable(){
			m_database.delete(IncomeCallsDbHelper.CALLS_TABLE_NAME, null, null);
		}
	
	public IncomeCalls createNewNumber(String a_number){
		ContentValues m_values = new ContentValues();
		m_values.put(IncomeCallsDbHelper.COLUMN_NUMBER, a_number);
		long m_newId = m_database.insert(IncomeCallsDbHelper.CALLS_TABLE_NAME, null, m_values);
		Cursor m_cursor = m_database.query(IncomeCallsDbHelper.CALLS_TABLE_NAME,m_Columns, 
						IncomeCallsDbHelper.COLUMN_ID + " = " + m_newId, null,null,null,null);
		m_cursor.moveToFirst();
		IncomeCalls newCalls = cursorDoCalls(m_cursor);
		m_cursor.close();
		return newCalls;
		
	}
	

	private IncomeCalls cursorDoCalls(Cursor a_cursor){
		IncomeCalls m_call = new IncomeCalls();
		m_call.setId(a_cursor.getLong(0));
		m_call.setNumber(a_cursor.getString(1));
		return m_call;
	}
	
	public List<IncomeCalls> GetAllCallNumbers(){
		List<IncomeCalls> m_calls = new ArrayList<IncomeCalls>(); 
		Cursor m_cursor = m_database.query(IncomeCallsDbHelper.CALLS_TABLE_NAME,
				m_Columns, null,null,null,null,IncomeCallsDbHelper.COLUMN_ID + " DESC");
		m_cursor.moveToFirst();
		while(!m_cursor.isAfterLast()){
			IncomeCalls call = cursorDoCalls(m_cursor);
			m_calls.add(call);
			m_cursor.moveToNext();
		}
		m_cursor.close();
		return m_calls;
	}

	
}
