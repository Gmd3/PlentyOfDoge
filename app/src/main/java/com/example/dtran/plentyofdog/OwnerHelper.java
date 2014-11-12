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
    public class OwnerHelper extends SQLiteOpenHelper
    {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "plentyofdog";

        public OwnerHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("DB", "DB CREATING");
            String CREATE_OWNER_TABLE = "CREATE TABLE Owner (_id INTEGER PRIMARY KEY, FirstName VARCHAR NOT NULL, LastName VARCHAR NOT NULL, Experience VARCHAR NOT NULL, Age INTEGER NOT NULL, Gender VARCHAR NOT NULL, Email VARCHAR NOT NULL, Phone INTEGER, Area VARCHAR, DateCreated DATETIME, LastEdited DATETIME);";
            db.execSQL(CREATE_OWNER_TABLE);
            Log.d("DB", "DB CREATED");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Owner");
            onCreate(db);
        }
        public void addOwner(Owner owner){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("FirstName", owner.firstName);
            values.put("LastName", owner.lastName);
            values.put("Experience", owner.experience);
            values.put("Age", owner.age);
            values.put("Gender", owner.gender);
            values.put("Email", owner.email);
            values.put("Phone", owner.phone);
            values.put("Area", owner.area);
            values.put("DateCreated", owner.dateCreated);
            values.put("LastEdited", owner.lastEdited);

            db.insert("Owner", null, values);
            db.close();
        }
        public Owner getOwner(int id){
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM Owner WHERE _id = " + id, null);
            if(cursor != null)
                cursor.moveToFirst();
            Owner owner = new Owner(
                    cursor.getInt(0)
                    ,cursor.getString(1)
                    ,cursor.getString(2)
                    ,cursor.getString(3)
                    ,cursor.getInt(4)
                    ,cursor.getString(5)
                    ,cursor.getString(6)
                    ,cursor.getInt(7)
                    ,cursor.getString(8)
                    ,cursor.getString(9)
                    ,cursor.getString(10)
            );
            cursor.close();
            return owner;
        }
        public List<Owner> getAllOwner(){
            List<Owner> ownerList = new ArrayList<Owner>();
            String selectQuery = "SELECT * FROM Owner";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery,null);

            if(cursor.moveToFirst()){
                do {
                    Owner owner = new Owner();
                    owner.setOwnerID(cursor.getInt(0));
                    owner.setFirstName(cursor.getString(1));
                    owner.setLastName(cursor.getString(2));
                    owner.setExperience(cursor.getString(3));
                    owner.setAge(cursor.getInt(4));
                    owner.setGender(cursor.getString(5));
                    owner.setEmail(cursor.getString(6));
                    owner.setPhone(cursor.getInt(7));
                    owner.setArea(cursor.getString(8));
                    owner.setDateCreated(cursor.getString(9));
                    owner.setLastEdited(cursor.getString(10));
                    ownerList.add(owner);
                }while(cursor.moveToNext());
            }
            cursor.close();
            return ownerList;
        }
        public int getOwnerCount(){
            String selectQuery = "SELECT * FROM Owner";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery,null);
            cursor.close();
            return cursor.getCount();
        }
        public int updateOwner(Owner owner){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("FirstName", owner.firstName);
            values.put("LastName", owner.lastName);
            values.put("Experience", owner.experience);
            values.put("Age", owner.age);
            values.put("Gender", owner.gender);
            values.put("Email", owner.email);
            values.put("Phone", owner.phone);
            values.put("Area", owner.area);
            values.put("DateCreated", owner.dateCreated);
            values.put("LastEdited", owner.lastEdited);

            return db.update("Owner", values, "_id = ?", new String[]{String.valueOf(owner.id)});
        }
        public void deleteOwner(Owner owner){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Owner", "_id = ?", new String[]{String.valueOf(owner.id)});
        }
    }
