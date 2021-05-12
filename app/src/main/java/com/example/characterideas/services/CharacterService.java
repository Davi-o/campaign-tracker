package com.example.characterideas.services;

import android.content.Context;

import com.example.characterideas.entities.CharacterEntity;
import com.example.characterideas.repositories.CharacterRepository;

public final class CharacterService {

    public static void insertNewCharacter(Context context, CharacterEntity character) {
        new CharacterRepository().create(character,context);
    }
}
