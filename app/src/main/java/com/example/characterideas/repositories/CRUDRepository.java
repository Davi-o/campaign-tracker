package com.example.characterideas.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public interface CRUDRepository<T> {
    List<T> readAll();
    T readById();
    void create(T entity);
    void update(T entity);
    void delete(T entity);
}
