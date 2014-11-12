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
public class PetNoteHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plentyofdog";

    public PetNoteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "DB CREATING");
        String CREATE_PET_NOTE = "CREATE TABLE PetNote( _id INTEGER PRIMARY KEY, DogID INTEGER NOT NULL, Title VARCHAR NOT NULL, Description VARCHAR NOT NULL)";
        db.execSQL(CREATE_PET_NOTE);
        Log.d("DB", "DB CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PetNote");
        onCreate(db);
    }
    public void addPetNote(PetNote note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("DogID", note.dogID);
        values.put("Title", note.title);
        values.put("Description", note.description);


        db.insert("PetNote", null, values);
        db.close();
    }
    public PetNote getPetNote(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM PetNote WHERE _id = " + id, null);
        if(cursor != null)
            cursor.moveToFirst();
        PetNote petNote = new PetNote(
                cursor.getInt(0)
                ,cursor.getString(1)
                ,cursor.getString(2)

        );
        cursor.close();
        return petNote;
    }
    public List<PetNote> getAllNotes(){
        List<PetNote> noteList = new ArrayList<PetNote>();
        String selectQuery = "SELECT * FROM PetNote";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                PetNote note = new PetNote(
                        cursor.getInt(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        );
                noteList.add(note);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return noteList;
    }
    public int getNoteCount(){
        String selectQuery = "SELECT * FROM PetNote";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public int updateDog(PetNote note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DogID", note.dogID);
        values.put("Title", note.title);
        values.put("Description", note.description);

        return db.update("PetNote", values, "_id = ?", new String[]{String.valueOf(note.id)});
    }
    public void deleteNote(PetNote note){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("PetNote", "_id = ?", new String[]{String.valueOf(note.id)});
    }
}
