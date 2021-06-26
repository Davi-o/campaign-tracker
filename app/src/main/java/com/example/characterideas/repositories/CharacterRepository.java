package com.example.characterideas.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.characterideas.database.DatabaseConnection;
import com.example.characterideas.entities.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

public class CharacterRepository extends DatabaseConnection implements CRUDRepository<CharacterEntity>  {

    public CharacterRepository(Context context) {
        super(context);
    }

    @Override
    public List<CharacterEntity> readAll() {
        Cursor characters = readableDatabase(getContext()).rawQuery(
                "select id, name, origin, devotion, archetype, resume, campaign from character",
                null
        );
        return formatResponse(characters);
    }

    private List<CharacterEntity> formatResponse(Cursor characters) {
        List<CharacterEntity> characterList = new ArrayList<>();

        if(characters.getCount() > 0) {
            characters.moveToFirst();
            do{
                CharacterEntity character = new CharacterEntity(
                        characters.getInt(0),
                        characters.getString(1),
                        characters.getString(2),
                        characters.getString(3),
                        characters.getString(4),
                        characters.getString(5),
                        characters.getInt(6)
                );
                characterList.add(character);
            } while (characters.moveToNext());
        }

        characters.close();
        return characterList;
    }

    @Override
    public CharacterEntity readById() {
        return null;
    }

    @Override
    public void create(CharacterEntity character) {
        ContentValues values = getContentValues(character);
        writableDatabase(getContext()).insert("character", null, values);
    }

    private ContentValues getContentValues(CharacterEntity character) {
        ContentValues values = new ContentValues();

        if (character.getId() != 0) {
            values.put("id", character.getId());
        }
        values.put("name", character.getName());
        values.put("origin", character.getOrigin());
        values.put("devotion", character.getDevotion());
        values.put("archetype", character.getArchetype());
        values.put("resume", character.getResume());
        values.put("campaign", character.getCampaignId());

        return values;
    }

    @Override
    public void update(CharacterEntity character) {
        ContentValues values = getContentValues(character);
        writableDatabase(getContext()).update("character" , values,"id = ?", new String[]{String.valueOf(character.getId())});
    }

    @Override
    public void delete(CharacterEntity character) {
        writableDatabase(getContext()).delete("character","id = ?", new String[]{String.valueOf(character.getId())});
    }

}
