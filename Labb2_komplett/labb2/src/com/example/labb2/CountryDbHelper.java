package com.example.labb2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CountryDbHelper extends SQLiteOpenHelper {

	//Table and columns
    public static final String TABLE_COUNTRIES = "countries";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_NAME = "name";

    
    private static final String DATABASE_NAME = "countries.db";
    private static final int DATABASE_VERSION = 1;

    // DB create statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COUNTRIES + "(" + COLUMN_YEAR + " int not null, "
            + COLUMN_NAME + " text not null);";

    public CountryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Take away comment if you want to clear the database every time you start the application, not recommended.
        //clearDatabase(context);
    }

    
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(CountryDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        onCreate(db);
    }
    
    static public void clearDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
