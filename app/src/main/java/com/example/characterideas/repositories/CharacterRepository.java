package com.example.characterideas.repositories;

import android.content.ContentValues;
import android.content.Context;

import com.example.characterideas.entities.CharacterEntity;

import java.util.List;

public class CharacterRepository implements CRUDRepository<CharacterEntity> {

    @Override
    public List<CharacterEntity> readAll() {
        return null;
    }

    @Override
    public CharacterEntity readById() {
        return null;
    }

    @Override
    public void create(CharacterEntity character, Context context) {
        ContentValues values = new ContentValues();

        values.put("name", character.getName());
        values.put("origin", character.getOrigin());
        values.put("devotion", character.getDevotion());
        values.put("archetype", character.getArchetype());
        values.put("resume", character.getResume());

        writableDatabase(context).insert("character", null, values);
    }

    @Override
    public void update(CharacterEntity character, Context context) {

    }

    @Override
    public void delete(CharacterEntity character, Context context) {

    }

}
