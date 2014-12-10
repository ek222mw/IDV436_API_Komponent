package com.example.labb2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Columns;
import android.util.Log;

public class CountryDataSource {

	  // DB fields
    private SQLiteDatabase m_sqlDB;
    private CountryDbHelper m_CountrydbHelper;
    private String[] m_Columns = { CountryDbHelper.COLUMN_YEAR, CountryDbHelper.COLUMN_NAME};

   
    public CountryDataSource(Context a_context) {
        m_CountrydbHelper = new CountryDbHelper(a_context);
    }

    
    public void Isopen() throws SQLException {
        m_sqlDB = m_CountrydbHelper.getWritableDatabase();
    }

    public void close() {
        m_CountrydbHelper.close();
    }
    
    public void doSaveCountry(int a_year, String a_name) {
    	ContentValues m_Contvalues = new ContentValues();
        m_Contvalues.put(CountryDbHelper.COLUMN_YEAR, a_year);
        m_Contvalues.put(CountryDbHelper.COLUMN_NAME, a_name);
        
     

        Cursor cursor = m_sqlDB.query(CountryDbHelper.TABLE_COUNTRIES,
                m_Columns, CountryDbHelper.COLUMN_YEAR + " = '" + a_year + "'"  + " AND "
                + CountryDbHelper.COLUMN_NAME + " = '" + a_name + "'", null, null, null, CountryDbHelper.COLUMN_YEAR);
        
        //Insert note into database.
        long m_insertContent = m_sqlDB.insert(CountryDbHelper.TABLE_COUNTRIES, null,
                m_Contvalues);

        cursor.moveToLast();
        cursor.close();
        
    }
    
    public List<String> getAllCountries(String orderBy) {
    	
    	List<String> m_list = new ArrayList<String>();
    	Cursor cursor;
    	if(orderBy.equals("name")) {
    		cursor = m_sqlDB.query(CountryDbHelper.TABLE_COUNTRIES, m_Columns, null, null, null, null, CountryDbHelper.COLUMN_NAME);
    	}
    	else if(orderBy.equals("nameDESC"))
    	{
    		cursor = m_sqlDB.query(CountryDbHelper.TABLE_COUNTRIES, m_Columns, null, null, null, null, CountryDbHelper.COLUMN_NAME+" DESC");
    	}
    	else if(orderBy.equals("yearDESC"))
    	{
    		cursor = m_sqlDB.query(CountryDbHelper.TABLE_COUNTRIES, m_Columns, null, null, null, null, CountryDbHelper.COLUMN_YEAR+" DESC");
    	}
    	else {
    		cursor = m_sqlDB.query(CountryDbHelper.TABLE_COUNTRIES, m_Columns, null, null, null, null, CountryDbHelper.COLUMN_YEAR);
    	}
    	cursor.moveToFirst();
    	while(!cursor.isAfterLast()) {
    		m_list.add(cursor.getInt(0) + "  " + cursor.getString(1));
    		cursor.moveToNext();
    	}
    	cursor.close();
		return m_list;
    }
    
    public void DeleteCountry(int a_year, String a_country) {
    	String[] m_whereArguments = new String[2];
    	m_whereArguments[0] = a_year+"";
    	m_whereArguments[1] = a_country;
    	m_sqlDB.delete(CountryDbHelper.TABLE_COUNTRIES, CountryDbHelper.COLUMN_YEAR + " = ? AND " + 
    			CountryDbHelper.COLUMN_NAME + " = ? ", m_whereArguments);
    }
    
    public void updateDataBase(String a_YearBefore, String a_CountryBefore, String a_newYear, String a_newCountry) {
    	
    	int m_newYear = Integer.parseInt(a_newYear);
    	int m_YearBefore = Integer.parseInt(a_YearBefore);
    	
    	
    	String[] m_whereArguments = new String[2];
    	m_whereArguments[0] = m_YearBefore+"";
    	m_whereArguments[1] = a_CountryBefore;
    	
    	ContentValues values = new ContentValues();
    	values.put(CountryDbHelper.COLUMN_YEAR, m_newYear);
    	values.put(CountryDbHelper.COLUMN_NAME, a_newCountry);
    	
    	m_sqlDB.update(CountryDbHelper.TABLE_COUNTRIES, values, CountryDbHelper.COLUMN_YEAR + " = ? AND " + 
    			CountryDbHelper.COLUMN_NAME + " = ? ", m_whereArguments);
    }
		 
	  }
	  
	  
	
	
