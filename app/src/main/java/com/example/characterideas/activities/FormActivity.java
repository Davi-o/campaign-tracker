package com.example.characterideas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.characterideas.R;
import com.example.characterideas.entities.CharacterEntity;
import com.example.characterideas.repositories.CharacterRepository;
import com.example.characterideas.services.CharacterService;

public class FormActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextOrigin;
    private EditText editTextDevotion;
    private EditText editTextArchetype;
    private EditText editTextResume;
    private Button submitButton;
    private String action;
    private CharacterService characterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_form);

        this.getInputValues();

        if(this.action.equals("edit")){
            this.loadFormData();
        }

        this.submitButton.setOnClickListener(view -> submitFormData(this));

    }

    private void loadFormData() {
    }

    private void submitFormData(Context context) {
        switch (this.action) {
            case "edit":
                this.editCharacter(context);
                break;
            case "create":
                this.createCharacter(context);
                break;
        }

    }

    private void createCharacter(Context context) {
        CharacterEntity character = new CharacterEntity(
                0,
                editTextName.getText().toString(),
                editTextOrigin.getText().toString(),
                editTextDevotion.getText().toString(),
                editTextArchetype.getText().toString(),
                editTextResume.getText().toString()
        );

        CharacterService.insertNewCharacter(context, character);
    }

    private void editCharacter(Context context) {
    }

    private void getInputValues() {
        this.editTextName = findViewById(R.id.editTextName);
        this.editTextOrigin = findViewById(R.id.editTextOrigin);
        this.editTextDevotion = findViewById(R.id.editTextDevotion);
        this.editTextArchetype = findViewById(R.id.editTextArchetype);
        this.editTextResume = findViewById(R.id.editTextResume);
        this.submitButton = findViewById(R.id.submitButton);

        this.getAction();
    }

    private void getAction() {
        this.action = getIntent().getStringExtra("action");;
    }
}