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
 *
 * This is basically the Database helper for a User object
 */
public class UserHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "plentyofdog";
    SQLiteDatabase db;

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "USER CREATING");
        String CREATE_USER_TABLE = "CREATE TABLE User( _id INTEGER PRIMARY KEY, Username VARCHAR NOT NULL, Password VARCHAR NOT NULL, OwnerID INTEGER NOT NULL)";
        db.execSQL(CREATE_USER_TABLE);
        this.db = db;
        Log.d("DB", "USER CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }
    public void addUser(User user){
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Username", user.username);
        values.put("Password", user.password);
        values.put("OwnerID", user.ownerId);



        db.insert("User", null, values);
        Log.d("DB:", "------------------USER ADDED");
        db.close();
    }
    public User getUser(int id){
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE _id = " + id, null);
        if(cursor != null)
            cursor.moveToFirst();
        User user = new User(
                cursor.getString(1)
                ,cursor.getString(2)
                ,cursor.getInt(3)
        );
        cursor.close();
        return user;
    }
    public User getUser(String email){
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE Username = '" + email + "'",  null);
        if(cursor != null)
            cursor.moveToFirst();
        User user = new User(
                cursor.getInt(0)
                ,cursor.getString(1)
                ,cursor.getString(2)
                ,cursor.getInt(3)
        );
        cursor.close();
        return user;
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM User";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                User user = new User(
                        cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getInt(3));

                userList.add(user);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }
    public int getUserCount(){
        String selectQuery = "SELECT * FROM User";

        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int updateUser(User user){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", user.username);
        values.put("Password", user.password);
        values.put("OwnerID", user.ownerId);

        Log.d("User Update", " DONE");
        return db.update("User", values, "OwnerID = ?", new String[]{String.valueOf(user.ownerId)});
    }
    public boolean userExist(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM User WHERE Username = '" + username + "'";
        Cursor cs = db.rawQuery(selectQuery,null);

        if(cs.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("User", "_id = ?", new String[]{String.valueOf(user.id)});
    }

    public int getOwnerID(String userName){
        User user = getUser(userName);
        return user.ownerId;
    }
}
