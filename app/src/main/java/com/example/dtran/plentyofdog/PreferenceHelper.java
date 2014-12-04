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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dtran on 14-11-10.
 */
public class PreferenceHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "plentyofdog";
    SQLiteDatabase db;
    public PreferenceHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "PREFERENCE CREATING");
        String CREATE_PREFERENCE = "CREATE TABLE Preference( _id INTEGER PRIMARY KEY, Size VARCHAR, HairType VARCHAR, Temperament VARCHAR, Username VARCHAR)";
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
        values.put("Username", preference.username);


        db.insert("Preference", null, values);
        Log.d("DB","Preference Added");
        db.close();
    }
    public boolean preferenceExist(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM Preference WHERE Username = '" + username + "'";
        Cursor cs = db.rawQuery(selectQuery,null);
        Log.d("DB", "" + cs.getCount());
        if(cs.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }
    public List<Preference> getAllPreferences(){
        List<Preference> prefList = new ArrayList<Preference>();
        String selectQuery = "SELECT * FROM Preference";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                Preference pref = new Preference(
                        cursor.getInt(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getString(3)
                        ,cursor.getString(4));
                prefList.add(pref);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return prefList;
    }
    public Preference getPreference(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Preference WHERE Username = '" + username+"'", null);
        if(cursor != null)
            cursor.moveToFirst();
        Preference preference = new Preference(
                cursor.getInt(0)
                ,cursor.getString(1)
                ,cursor.getString(2)
                ,cursor.getString(3)
                ,cursor.getString(4)

        );
        cursor.close();
        Log.d("asdfOfficial Size : ", "" + preference.size);
        Log.d("asdfOfficial HairType : ", "" + preference.hairtype);
        Log.d("asdfOfficial Temperament : ", "" + preference.temperament);
        Log.d("asdfOfficial Username : ", "" + preference.username);
        return preference;
    }
    public int countPreference(){
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM Preference", null);
        if(cursor != null)
            cursor.moveToFirst();
        count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int updatePreference(Preference pref){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Size", pref.size);
        values.put("HairType", pref.hairtype);
        values.put("Temperament", pref.temperament);
        values.put("Username", pref.username);

        Log.d("Preference Update", " DONE");
        return db.update("Preference", values, "Username = ?", new String[]{String.valueOf(pref.username)});
    }

    public void deletePreference(Preference preference){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Preference", "Username = ?", new String[]{String.valueOf(preference.username)});
    }
}
