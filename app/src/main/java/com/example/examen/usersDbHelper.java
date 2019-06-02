package com.example.examen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class usersDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";

    public usersDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + itemsSQL.usersEntry.TABLE_NAME + " ("
                + itemsSQL.usersEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + itemsSQL.usersEntry.FIRST_NAME + " TEXT NOT NULL,"
                + itemsSQL.usersEntry.LAST_NAME + " TEXT NOT NULL,"
                + itemsSQL.usersEntry.EMAIL + " TEXT NOT NULL,"
                + itemsSQL.usersEntry.AVATAR_URI + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+itemsSQL.usersEntry.TABLE_NAME);
        onCreate(db);
    }
}
