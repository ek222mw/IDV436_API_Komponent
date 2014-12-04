package com.example.labb2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Columns;

public class CountryDataSource {

	 // Database fields
	  private SQLiteDatabase database;
	  private CountryDbHelper dbHelper;
	  private String[] allColumns = { CountryDbHelper.COLUMN_ID,
			  CountryDbHelper.COLUMN_TASK,CountryDbHelper.COLUMN_YEAR };
	  private String SortOrder;

	  public CountryDataSource(Context context) {
	    dbHelper = new CountryDbHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Country createTask(String task, String year) {
	    ContentValues values = new ContentValues();
	   
	    values.put(CountryDbHelper.COLUMN_TASK, task);
	    values.put(CountryDbHelper.COLUMN_YEAR, year);
	   
	    long insertId = database.insert(CountryDbHelper.TASKS_TABLE_NAME, null, values);
	    Cursor cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
	        allColumns, CountryDbHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Country newTask = cursorToTask(cursor);
	    cursor.close();
	    return newTask;
	  }
	  
	 

	  public void deleteTask(Country task) {
	    long id = task.getId();
	    System.out.println("Task deleted with id: " + id);
	    database.delete(CountryDbHelper.TASKS_TABLE_NAME, CountryDbHelper.COLUMN_ID
	    		+ " = " + id, null);
	  }
	  
	  public Country getTask(long taskId) {
		  String restrict = CountryDbHelper.COLUMN_ID + "=" + taskId;
		  Cursor cursor = database.query(true, CountryDbHelper.TASKS_TABLE_NAME, allColumns, restrict, 
		    		                        null, null, null, null, null);
		  if (cursor != null && cursor.getCount() > 0) {
			  cursor.moveToFirst();
			  Country task = cursorToTask(cursor);
			  return task;
		  }
		  // Make sure to close the cursor
		  cursor.close();
		  return null;
	  }
	  
	  public boolean updateTask(Country country) {
		  ContentValues args = new ContentValues();
		  args.put(CountryDbHelper.COLUMN_TASK, country.getTask());
		  args.put(CountryDbHelper.COLUMN_YEAR, country.getYear());

		  String restrict = CountryDbHelper.COLUMN_ID + "=" + country.getId();
		  return database.update(CountryDbHelper.TASKS_TABLE_NAME, args, restrict , null) > 0;
	  } 

	  public List<Country> getAllTasks(String SortOrder) {
	    List<Country> tasks = new ArrayList<Country>();
	    this.SortOrder = SortOrder;
	    Cursor cursor = database.query(CountryDbHelper.TASKS_TABLE_NAME,
	        allColumns, null, null, null, null,CountryDbHelper.COLUMN_TASK+ this.SortOrder );

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Country task = cursorToTask(cursor);
	      tasks.add(task);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return tasks;
	  }

	  private Country cursorToTask(Cursor cursor) {
		  Country task = new Country();
		  task.setId(cursor.getLong(0));
		  task.setTask(cursor.getString(1));
		  task.setYear(cursor.getString(2));
		  return task;
	  }
	  
		 
	  }
	  
	  
	
	
