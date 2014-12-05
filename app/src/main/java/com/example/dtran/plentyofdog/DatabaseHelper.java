package com.example.dtran.plentyofdog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The controller for the dog model
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "plentyofdog";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the tables

        String CREATE_DOG_TABLE = "CREATE TABLE Dog ( _id INTEGER PRIMARY KEY, Name VARCHAR NOT NULL, Breed VARCHAR NOT NULL, Age INTEGER NOT NULL, Gender VARCHAR NOT NULL, Size VARCHAR NOT NULL, Training VARCHAR NOT NULL, ActivityLevel VARCHAR NOT NULL, Description VARCHAR NOT NULL NOT NULL, Area VARCHAR NOT NULL, DateCreated DATE NOT NULL, LastEdited DATE, image BLOB)";
        db.execSQL(CREATE_DOG_TABLE);
        String CREATE_DOG_OWNER = "CREATE TABLE DogOwner( _id INTEGER PRIMARY KEY, DogID INTEGER NOT NULL, OwnerID INTEGER NOT NULL, DateCreated DATE NOT NULL, LastEdited DATE, Status VARCHAR NOT NULL)";
        db.execSQL(CREATE_DOG_OWNER);
        String CREATE_MATCH_TABLE = "CREATE TABLE Match( _id INTEGER PRIMARY KEY, UserID INTEGER NOT NULL, DogID INTEGER NOT NULL, Matched BOOLEAN NOT NULL, DateMatched DATE NOT NULL)";
        db.execSQL(CREATE_MATCH_TABLE);
        String CREATE_OWNER_TABLE = "CREATE TABLE Owner (_id INTEGER PRIMARY KEY, FirstName VARCHAR NOT NULL, LastName VARCHAR NOT NULL, Experience VARCHAR NOT NULL, Age INTEGER NOT NULL, Gender VARCHAR NOT NULL, Email VARCHAR NOT NULL, Phone INTEGER, Area VARCHAR, DateCreated DATETIME, LastEdited DATETIME);";
        db.execSQL(CREATE_OWNER_TABLE);
        String CREATE_PET_NOTE = "CREATE TABLE PetNote( _id INTEGER PRIMARY KEY, DogID INTEGER NOT NULL, Title VARCHAR NOT NULL, Description VARCHAR NOT NULL)";
        db.execSQL(CREATE_PET_NOTE);
        String CREATE_USER_TABLE = "CREATE TABLE User( _id INTEGER PRIMARY KEY, Username VARCHAR NOT NULL, Password VARCHAR NOT NULL, OwnerID INTEGER NOT NULL)";
        db.execSQL(CREATE_USER_TABLE);
        String CREATE_PREFERENCE = "CREATE TABLE Preference( _id INTEGER PRIMARY KEY, Size VARCHAR, HairType VARCHAR, Temperament VARCHAR, Username VARCHAR NOT NULL)";
        db.execSQL(CREATE_PREFERENCE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Owner");
        db.execSQL("DROP TABLE IF EXISTS DogBreed");
        db.execSQL("DROP TABLE IF EXISTS Dog");
        db.execSQL("DROP TABLE IF EXISTS DogOwner");
        db.execSQL("DROP TABLE IF EXISTS Match");
        db.execSQL("DROP TABLE IF EXISTS PetNote");
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Preference");
        onCreate(db);
    }


}