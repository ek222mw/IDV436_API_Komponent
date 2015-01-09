package com.example.labb3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class IncomeCallsDbHelper extends SQLiteOpenHelper {

	public static final String CALLS_TABLE_NAME = "calls";
	
	public static final String COLUMN_CALLEDNUMBER = "number";
	
	public static final String COLUMN_ID = "_id";

	private static final String DATABASE_NAME = "calls.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table " + CALLS_TABLE_NAME
			+ " (" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_CALLEDNUMBER + " text not null);";
	
	public IncomeCallsDbHelper(Context a_context){
		super(a_context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(IncomeCallsDbHelper.class.getName(), "Upgrading database from version " 
	    		+ oldVersion + " to " + newVersion 
	    		+ ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + CALLS_TABLE_NAME);
        onCreate(db);
	}

	
}
