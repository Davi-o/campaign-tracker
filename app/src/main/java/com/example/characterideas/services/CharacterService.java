package com.example.characterideas.services;

import android.content.Context;

import com.example.characterideas.activities.MainActivity;
import com.example.characterideas.entities.CampaignEntity;
import com.example.characterideas.entities.CharacterEntity;
import com.example.characterideas.repositories.CharacterRepository;

import java.util.List;

public final class CharacterService {

    public static void insertNewCharacter(Context context, CharacterEntity character) {
        new CharacterRepository(context).create(character);
    }

    public static List<CharacterEntity> getAllCharacters(Context context) {
        return new CharacterRepository(context).readAll();
    }

    public static void deleteCharacter(Context context, CharacterEntity selectedCharacter) {
        new CharacterRepository(context).delete(selectedCharacter);
    }

    public static void editSelectedCharacter(Context context, CharacterEntity character) {
        new CharacterRepository(context).update(character);
    }
}
