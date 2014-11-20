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
public class UserHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plentyofdog";

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "DB CREATING");
        String CREATE_USER_TABLE = "CREATE TABLE User( _id INTEGER PRIMARY KEY, Username VARCHAR NOT NULL, Password VARCHAR NOT NULL, OwnerID INTEGER NOT NULL)";
        db.execSQL(CREATE_USER_TABLE);
        Log.d("DB", "DB CREATED");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Username", user.username);
        values.put("Password", user.password);
        values.put("OwnerID", user.ownerId);


        db.insert("User", null, values);
        db.close();
    }
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE _id = " + id, null);
        if(cursor != null)
            cursor.moveToFirst();
        User user = new User(
                cursor.getString(0)
                ,cursor.getString(1)
                ,cursor.getInt(2)
        );
        cursor.close();
        return user;
    }
    public User getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE Username = " + email, null);
        if(cursor != null)
            cursor.moveToFirst();
        User user = new User(
                cursor.getString(0)
                ,cursor.getString(1)
                ,cursor.getInt(2)
        );
        cursor.close();
        return user;
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM User";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                User user = new User(
                        cursor.getString(0)
                        ,cursor.getString(1)
                        ,cursor.getInt(2));

                userList.add(user);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }
    public int getUserCount(){
        String selectQuery = "SELECT * FROM User";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public int updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", user.username);
        values.put("Password", user.password);
        values.put("OwnerID", user.ownerId);


        return db.update("User", values, "_id = ?", new String[]{String.valueOf(user.id)});
    }
    public boolean userExist(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM User WHERE Username = " + username;
        Cursor cs = db.rawQuery(selectQuery,null);

        if(cs.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("User", "_id = ?", new String[]{String.valueOf(user.id)});
    }
}
