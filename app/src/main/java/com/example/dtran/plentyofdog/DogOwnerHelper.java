package com.example.dtran.plentyofdog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        String CREATE_DOG_OWNER = "CREATE TABLE DogOwner( _id INTEGER PRIMARY KEY, DogID INTEGER NOT NULL, OwnerID INTEGER NOT NULL, DateCreated DATE NOT NULL, LastEdited DATE, Status VARCHAR NOT NULL)";
        db.execSQL(CREATE_DOG_OWNER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DogOwner");
        onCreate(db);
    }

    /**
     * Adds the dog owner
     * @param dogOwner
     */
    public void addDogOwner(DogOwner dogOwner){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OwnerID", dogOwner.ownerID);
        values.put("DogID", dogOwner.dogID);
        values.put("DateCreated", dogOwner.dateCreated);
        values.put("LastEdited", dogOwner.lastEditted);
        values.put("Status", dogOwner.status);

        db.insert("DogOwner", null, values);
        db.close();
    }

    /**
     * Gets the dog owner with the id
     * @param id
     * @return
     */
    public DogOwner getDogOwner(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM DogOwner WHERE _id = " + id, null);
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

    /**
     * Gets the dog owner form the dog id
     * @param id
     * @return
     */
    public DogOwner getDogOwnerFromDogID(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM DogOwner WHERE DogID = " + id, null);
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

    /**
     * Gets all the dog owners
     * @return
     */
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

    /**
     * Gets the number of dogs
     * @return
     */
    public int getDogOwnerCount(){
        String selectQuery = "SELECT * FROM DogOwner";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.close();
        return cursor.getCount();
    }

    /**
     * Updates the dog owner with the dog objects
     * @param dogOwner
     * @return
     */
    public int updateDogOwner(DogOwner dogOwner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("OwnerID", dogOwner.ownerID);
        values.put("DogID", dogOwner.dogID);
        values.put("DateCreated", dogOwner.dateCreated);
        values.put("LastEdited", dogOwner.lastEditted);
        values.put("Status", dogOwner.status);

        return db.update("DogOwner", values, "_id = ?", new String[]{String.valueOf(dogOwner.id)});
    }

    /**
     * Delets the dog owner wit hthe dog owner object
     * @param dogOwner
     */
    public void deleteDogOwner(DogOwner dogOwner){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DogOwner", "_id = ?", new String[]{String.valueOf(dogOwner.id)});
    }

    /**
     * Deletes the dog owner with the dogID
     * @param dogID
     */
    public void deleteDogOwner(int dogID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DogOwner", "DogID = ?", new String[]{String.valueOf(dogID)});
    }

    /**
     * Retreieves all the dogs
     * @param ownerID
     * @return
     */
    public ArrayList<Integer> getMyDogs(int ownerID){
        ArrayList<Integer> MyDogs = new ArrayList<Integer>();
        String selectQuery = "SELECT * FROM DogOwner WHERE OwnerID = '" + ownerID + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                MyDogs.add(cursor.getInt(1));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return MyDogs;
    }

    /**
     * Gets the dogs other than the one with the owner ID
     * @param ownerID
     * @return
     */
    public ArrayList<Integer> getOtherDogs(int ownerID){
        ArrayList<Integer> MyDogs = new ArrayList<Integer>();
        String selectQuery = "SELECT * FROM DogOwner WHERE OwnerID != '" + ownerID + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                MyDogs.add(cursor.getInt(1));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return MyDogs;
    }
}
