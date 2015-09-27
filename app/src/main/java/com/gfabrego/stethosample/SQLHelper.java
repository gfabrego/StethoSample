package com.gfabrego.stethosample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StethoDemo.db";
    private static final String CREATE_DB =
            "CREATE TABLE demotable (_id INTEGER PRIMARY KEY, democolumn TEXT)";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);

        // Insert dummies
        sqLiteDatabase.execSQL("INSERT INTO demotable (_id, democolumn) values (1, 'Test 1');");
        sqLiteDatabase.execSQL("INSERT INTO demotable (_id, democolumn) values (2, 'Test 2');");
        sqLiteDatabase.execSQL("INSERT INTO demotable (_id, democolumn) values (3, 'Test 3');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
