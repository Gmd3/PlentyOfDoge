package com.example.dtran.plentyofdog;

import android.provider.BaseColumns;

/**
 * Created by dtran on 2014-10-17.
 */
public final class DogOwner {

    public DogOwner() {
    }

    /* Inner class that defines the table contents */
    public static abstract class DogOwnerEntry implements BaseColumns {
        public static final String TABLE_NAME = "owner";
        public static final String COLUMN_DOGID = "dogid";
        public static final String COLUMN_OWNERID = "ownerid";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_DATE_CREATED = "datacreated";
        public static final String COLUMN_LAST_EDITTED = "lasteddited";
    }
}