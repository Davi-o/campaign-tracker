package com.example.characterideas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnection extends Database {

    public DatabaseConnection(Context context) {
        super(context);
    }

    public Database getConnection(Context context) {
        return new DatabaseConnection(context);
    }

    public SQLiteDatabase writableDatabase(Context context) {
        return this.getConnection(context).getWritableDatabase();
    }

    public SQLiteDatabase readableDatabase(Context context) {
        return this.getConnection(context).getReadableDatabase();
    }
}
