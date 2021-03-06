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
    public class OwnerHelper extends SQLiteOpenHelper
    {
        private static final int DATABASE_VERSION = 7;
        private static final String DATABASE_NAME = "plentyofdog";

        public OwnerHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_OWNER_TABLE = "CREATE TABLE Owner (_id INTEGER PRIMARY KEY, FirstName VARCHAR NOT NULL, LastName VARCHAR NOT NULL, Experience VARCHAR NOT NULL, Age INTEGER NOT NULL, Gender VARCHAR NOT NULL, Email VARCHAR NOT NULL, Phone INTEGER, Area VARCHAR, DateCreated DATETIME, LastEdited DATETIME);";
            db.execSQL(CREATE_OWNER_TABLE);
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
                    ,cursor.getString(7)
                    ,cursor.getString(8)
                    ,cursor.getString(9)
                    ,cursor.getString(10)
            );
            cursor.close();
            return owner;
        }
        public Owner getOwner(String username){
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM Owner WHERE Email = '" + username + "'", null);
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
                    ,cursor.getString(7)
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
                    owner.id = (cursor.getInt(0));
                    owner.firstName = (cursor.getString(1));
                    owner.lastName = (cursor.getString(2));
                    owner.experience = (cursor.getString(3));
                    owner.age = (cursor.getInt(4));
                    owner.gender = (cursor.getString(5));
                    owner.email = (cursor.getString(6));
                    owner.phone = (cursor.getString(7));
                    owner.area = (cursor.getString(8));
                    owner.dateCreated = (cursor.getString(9));
                    owner.lastEdited = (cursor.getString(10));
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
            int count = cursor.getCount();
            cursor.close();
            return count;
        }
        public boolean userExist(String email){
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM Owner WHERE Email = '" + email + "'";
            Cursor cs = db.rawQuery(selectQuery,null);

            if(cs.getCount() == 0) {
                return false;
            } else {
                return true;
            }
        }
        public boolean updateOwner(Owner owner){
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
            return db.update("Owner", values, "_id = ?", new String[]{String.valueOf(owner.id)}) > 0;
        }
        public void deleteOwner(Owner owner){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("Owner", "_id = ?", new String[]{String.valueOf(owner.id)});
        }

        public int getLastOwnerID(){
            SQLiteDatabase db = this.getWritableDatabase();
            String selectQuery = "SELECT * FROM Owner";
            Cursor cursor = db.rawQuery(selectQuery,null);
            cursor.moveToLast();
            return cursor.getInt(0);
        }
    }
