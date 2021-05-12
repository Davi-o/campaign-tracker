package com.example.characterideas.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.characterideas.database.Connection;

import java.util.List;

public interface CRUDRepository<T> {
    List<T> readAll();
    T readById();
    void create(T entity, Context context);
    void update(T entity,Context context);
    void delete(T entity,Context context);

    default Connection getConnection(Context context) {
        return new Connection(context);
    };

    default SQLiteDatabase writableDatabase(Context context) {
        return this.getConnection(context).getWritableDatabase();
    };

    default SQLiteDatabase readableDatabase(Context context) {
        return this.getConnection(context).getReadableDatabase();
    }
}
