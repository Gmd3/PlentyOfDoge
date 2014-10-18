package com.example.dtran.plentyofdog;

import android.provider.BaseColumns;

/**
 * Created by dtran on 2014-10-17.
 * This file is creating the contact schema to create the SQLite3 database
 */
public final class Owner {
    public Owner(){

    }
    /* Inner class that defines the table contents */
    public static abstract class ContactEntry implements BaseColumns{
        public static final String TABLE_NAME = "owner";
        public static final String COLUMN_NAME_CONTACT_ID = "ownerid";
        public static final String COLUMN_FIRSTNAME = "firstname";
        public static final String COLUMN_LASTNAME = "lastname";
        public static final String COLUMN_EXPERIENCE = "experience";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_AREA = "area";
        public static final String COLUMN_DATE_CREATED = "datecreated";
        public static final String COLUMN_LAST_EDITTED = "lasteddited";
    }
}
