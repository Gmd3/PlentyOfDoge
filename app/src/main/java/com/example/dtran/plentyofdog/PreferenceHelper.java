package com.example.dtran.plentyofdog;

/**
 * Created by dtran on 14-11-22.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dtran on 14-11-10.
 */
public class PreferenceHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "plentyofdog";

    public PreferenceHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "PREFERENCE CREATING");
        String CREATE_PREFERENCE = "CREATE TABLE Preference( _id INTEGER PRIMARY KEY, Size VARCHAR, HairType VARCHAR, Temperament VARCHAR, UserID INTEGER)";
        db.execSQL(CREATE_PREFERENCE);
        Log.d("DB", "PREFERENCE CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Preference");
        onCreate(db);
    }
    public void addPreference(Preference preference){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Size", preference.size);
        values.put("HairType", preference.hairtype);
        values.put("Temperament", preference.temperament);
        values.put("UserID", preference.userid);


        db.insert("Preference", null, values);
        Log.d("DB","Preference Added");
        db.close();
    }
    public Preference getPreference(int userID){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Preference WHERE UserID = " + userID, null);
        if(cursor != null)
            cursor.moveToFirst();
        Preference preference = new Preference(
                cursor.getInt(0)
                ,cursor.getString(1)
                ,cursor.getString(2)
                ,cursor.getString(3)

        );
        cursor.close();
        return preference;
    }
    public int updatePreference(Preference preference){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserID", preference.userid);
        values.put("Size", preference.size);
        values.put("HairType", preference.hairtype);
        values.put("Temperament", preference.temperament);

        return db.update("Preference", values, "UserID = ?", new String[]{String.valueOf(preference.userid)});
    }
    public void deletePreference(Preference preference){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Preference", "UserID = ?", new String[]{String.valueOf(preference.userid)});
    }
}
