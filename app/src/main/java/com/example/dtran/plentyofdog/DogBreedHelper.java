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
public class DogBreedHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plentyofdog";

    public DogBreedHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "DB CREATING");
        String CREATE_DOG_BREED = "CREATE TABLE DogBreed( _id INTEGER PRIMARY KEY, BreedName VARCHAR NOT NULL, HairType VARCHAR NOT NULL, Temperment VARCHAR NOT NULL, Shedding VARCHAR NOT NULL, Size VARCHAR NOT NULL)";
        db.execSQL(CREATE_DOG_BREED);
        Log.d("DB", "DB CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DogBreed");
        onCreate(db);
    }
    public void addDogBreed(DogBreed dogbreed){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("BreedName", dogbreed.breedname);
        values.put("HairType", dogbreed.hairtype);
        values.put("Temperment", dogbreed.temperment);
        values.put("Shedding", dogbreed.shedding);
        values.put("Size", dogbreed.size);


        db.insert("DogBreed", null, values);
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
    public List<DogBreed> getAllDogBreeds(){
        List<DogBreed> dogBreedList = new ArrayList<DogBreed>();
        String selectQuery = "SELECT * FROM DogBreed";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                DogBreed dogbreed = new DogBreed(
                        cursor.getString(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getString(3)
                        ,cursor.getString(4));
                dogBreedList.add(dogbreed);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dogBreedList;
    }
    public int getDogBreedCount(){
        String selectQuery = "SELECT * FROM DogBreed";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public int updateDogBreed(DogBreed dogBreed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BreedName", dogBreed.breedname);
        values.put("HairType", dogBreed.hairtype);
        values.put("Temperment", dogBreed.temperment);
        values.put("Shedding", dogBreed.shedding);
        values.put("Size", dogBreed.size);


        return db.update("DogBreed", values, "_id = ?", new String[]{String.valueOf(dogBreed.id)});
    }
    public void deleteDogBreed(DogBreed dogBreed){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DogBreed", "_id = ?", new String[]{String.valueOf(dogBreed.id)});
    }
}
