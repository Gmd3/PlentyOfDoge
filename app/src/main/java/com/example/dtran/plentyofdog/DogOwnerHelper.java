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
public class DogOwnerHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "plentyofdog";

    public DogOwnerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "DOGOWNER CREATING");
        String CREATE_DOG_OWNER = "CREATE TABLE DogOwner( _id INTEGER PRIMARY KEY, DogID INTEGER NOT NULL, OwnerID INTEGER NOT NULL, DateCreated DATE NOT NULL, LastEditted DATE, Status VARCHAR NOT NULL)";
        db.execSQL(CREATE_DOG_OWNER);
        Log.d("DB", "DOGOWNER CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DogOwner");
        onCreate(db);
    }
    public void addDogOwner(DogOwner dogOwner){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OwnerID", dogOwner.ownerID);
        values.put("DogID", dogOwner.dogID);
        values.put("DateCreated", dogOwner.dateCreated);
        values.put("LastEditted", dogOwner.lastEditted);
        values.put("Status", dogOwner.status);


        db.insert("DogOwner", null, values);
        db.close();
    }
    public DogOwner getDogOwner(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM DogOWner WHERE _id = " + id, null);
        if(cursor != null)
            cursor.moveToFirst();
        DogOwner dogOwner = new DogOwner(
                cursor.getInt(0)
                ,cursor.getInt(1)
                ,cursor.getInt(2)
                ,cursor.getString(3)
                ,cursor.getString(4)
                ,cursor.getString(5)
        );
        cursor.close();
        return dogOwner;
    }
    public List<DogOwner> getAllDogOwners(){
        List<DogOwner> dogOwnerList = new ArrayList<DogOwner>();
        String selectQuery = "SELECT * FROM DogOwner";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                DogOwner dogOwner = new DogOwner(
                        cursor.getInt(0)
                        ,cursor.getInt(1)
                        ,cursor.getInt(2)
                        ,cursor.getString(3)
                        ,cursor.getString(4)
                        ,cursor.getString(5));
                dogOwnerList.add(dogOwner);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dogOwnerList;
    }
    public int getDogOwnerCount(){
        String selectQuery = "SELECT * FROM DogOwner";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public int updateDogOwner(DogOwner dogOwner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("OwnerID", dogOwner.ownerID);
        values.put("DogID", dogOwner.dogID);
        values.put("DateCreated", dogOwner.dateCreated);
        values.put("LastEditted", dogOwner.lastEditted);
        values.put("Status", dogOwner.status);

        return db.update("DogOWner", values, "_id = ?", new String[]{String.valueOf(dogOwner.id)});
    }
    public void deleteDogOwner(DogOwner dogOwner){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DogOwner", "_id = ?", new String[]{String.valueOf(dogOwner.id)});
    }
}
