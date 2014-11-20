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
public class DogHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plentyofdog";

    public DogHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "DB CREATING");
        String CREATE_DOG_TABLE = "CREATE TABLE Dog ( _id INTEGER PRIMARY KEY, Name VARCHAR NOT NULL, Age INTEGER NOT NULL, Size VARCHAR NOT NULL, Training VARCHAR NOT NULL, ActivityLevel VARCHAR NOT NULL, Description VARCHAR NOT NULL NOT NULL, Area VARCHAR NOT NULL, DateCreated DATE NOT NULL, LastEditted DATE)";
        db.execSQL(CREATE_DOG_TABLE);
        Log.d("DB", "DB CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Dog");
        onCreate(db);
    }
    public void addDog(Dog dog){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", dog.name);
        values.put("Age", dog.age);
        values.put("Size", dog.training);
        values.put("Age", dog.activitylevel);
        values.put("Area", dog.area);
        values.put("DateCreated", dog.datecreated);
        values.put("LastEdited", dog.lasteditted);
        values.put("Description", dog.description);

        db.insert("Dog", null, values);
        db.close();
    }
    public Dog getDog(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Dog WHERE _id = " + id, null);
        if(cursor != null)
            cursor.moveToFirst();
        Dog dog = new Dog(
                cursor.getString(0)
                ,cursor.getString(1)
                ,cursor.getString(2)
                ,cursor.getString(6)
                ,cursor.getString(7)
                ,cursor.getString(8)
        );
        cursor.close();
        return dog;
    }
    public List<Dog> getAllDog(){
        List<Dog> dogList = new ArrayList<Dog>();
        String selectQuery = "SELECT * FROM Dog";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                Dog dog = new Dog(
                        cursor.getString(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getString(6)
                        ,cursor.getString(7)
                        ,cursor.getString(8));
                dogList.add(dog);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dogList;
    }
    public int getDogCount(){
        String selectQuery = "SELECT * FROM Dog";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public int updateDog(Dog dog){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", dog.name);
        values.put("Age", dog.age);
        values.put("Size", dog.training);
        values.put("Age", dog.activitylevel);
        values.put("Area", dog.area);
        values.put("DateCreated", dog.datecreated);
        values.put("LastEdited", dog.lasteditted);
        values.put("Description", dog.description);

        return db.update("Dog", values, "_id = ?", new String[]{String.valueOf(dog.id)});
    }
    public void deleteDog(Dog dog){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Dog", "_id = ?", new String[]{String.valueOf(dog.id)});
    }
}
