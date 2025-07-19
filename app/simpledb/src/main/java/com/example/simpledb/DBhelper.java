package com.example.simpledb;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_1 = "username";
    private static final String COLUMN_2 = "password";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_1 + " TEXT NOT NULL, "
            + COLUMN_2 + " TEXT NOT NULL"
            + ");";

    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String Username, String Password)
    {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(COLUMN_1,Username);
    cv.put(COLUMN_2,Password);
    long result = db.insert(TABLE_NAME,null,cv);

    if(result==-1)
    {
        return false;
    }
    else {
        return true;
    }

    }


    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(COLUMN_1));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(COLUMN_2));
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));

                users.add(new User(username, password,id));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }
}
