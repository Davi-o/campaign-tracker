package com.example.characterideas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class Database extends SQLiteOpenHelper {

    private static final String NAME = "CharacterIdeas";
    private static final int VERSION = 1;
    private Context context;

    public Database(Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists character " +
            "(" +
                "id integer not null primary key autoincrement," +
                "name integer(90) not null," +
                "origin integer(60) not null," +
                "devotion varchar(30) not null," +
                "archetype varchar(30) not null," +
                "resume varchar(255) not null" +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
