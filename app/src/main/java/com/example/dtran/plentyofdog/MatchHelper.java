package com.example.dtran.plentyofdog;

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
public class MatchHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "plentyofdog";

    public MatchHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "MATCH CREATING");
        String CREATE_MATCH_TABLE = "CREATE TABLE Match( _id INTEGER PRIMARY KEY, UserID INTEGER NOT NULL, DogID INTEGER NOT NULL, Matched BOOLEAN NOT NULL, DateMatched DATE NOT NULL)";
        db.execSQL(CREATE_MATCH_TABLE);
        Log.d("DB", "MATCH CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Match");
        onCreate(db);
    }
    public void addMatch(Match match){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("UserID", match.userID);
        values.put("DogID", match.dogID);
        values.put("Matched", match.matched);
        values.put("DateMatched", match.dateMatched);

        db.insert("Match", null, values);
        db.close();
    }
    public Match getMatch(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Match WHERE _id = " + id, null);
        if(cursor != null)
            cursor.moveToFirst();
        Match match = new Match(
                cursor.getInt(1)
                ,cursor.getInt(2)
                ,cursor.getInt(3)
                ,cursor.getString(4)

        );
        cursor.close();
        return match;
    }
    public Boolean existUserDogID(int userID, int dogID){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Match WHERE UserID = " + userID + " AND DogID = " + dogID, null);
        if(cursor != null)
            cursor.moveToFirst();
        int count = cursor.getCount();
        cursor.close();
        Log.d("EXISTUSERDOGID COUNT", "" + count);
        if(count == 0)
            return false;
        return true;
    }
    public List<Match> getAllMatches(){
        List<Match> matchlist = new ArrayList<Match>();
        String selectQuery = "SELECT * FROM Match";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                Match match = new Match(
                        cursor.getInt(0)
                        ,cursor.getInt(1)
                        ,cursor.getInt(2)
                        ,cursor.getString(3));
                matchlist.add(match);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return matchlist;
    }
    public List<Match> getAllMatchesDogID(int dogID){
        List<Match> matchlist = new ArrayList<Match>();
        String selectQuery = "SELECT * FROM Match WHERE dogID = " + dogID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                Match match = new Match(
                        cursor.getInt(1)
                        ,cursor.getInt(2)
                        ,cursor.getInt(3)
                        ,cursor.getString(4));
                matchlist.add(match);
            }while(cursor.moveToNext());
        }

        cursor.close();
        return matchlist;
    }
    public int getMatchCount(){
        String selectQuery = "SELECT * FROM Match";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public int updateMatch(Match match){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserID", match.userID);
        values.put("DogID", match.dogID);
        values.put("DateMatched", match.dateMatched);
        values.put("Matched", match.matched);

        return db.update("Match", values, "_id = ?", new String[]{String.valueOf(match.id)});
    }
    public void deleteDog(Dog dog){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Dog", "_id = ?", new String[]{String.valueOf(dog.id)});
    }
}
