package com.example.characterideas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.characterideas.R;
import com.example.characterideas.entities.CampaignEntity;
import com.example.characterideas.entities.CharacterEntity;
import com.example.characterideas.services.CampaignService;
import com.example.characterideas.services.CharacterService;

import java.util.ArrayList;
import java.util.List;

public class CharacterFormActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextOrigin;
    private EditText editTextDevotion;
    private EditText editTextArchetype;
    private EditText editTextResume;
    private Spinner spinnerCampaign;
    private Button submitButton;
    private String action;
    private CharacterEntity character;
    private List<CampaignEntity> campaigns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.character_form);

        this.setInputFields();

        if(this.action.equals("edit")){
            this.submitButton.setText(R.string.update);
            this.loadFormData();
        }

        this.submitButton.setOnClickListener(view -> submitFormData(this));

    }

    @Override
    protected void onDestroy() {
        this.submitButton.setText(R.string.add_character);
        super.onDestroy();
    }

    private void loadFormData() {
        this.character = this.getCharacter();

        editTextName.setText(character.getName());
        editTextOrigin.setText(character.getOrigin());
        editTextDevotion.setText(character.getDevotion());
        editTextArchetype.setText(character.getArchetype());
        editTextResume.setText(character.getResume());
        editTextResume.setText(character.getResume());
    }

    private CharacterEntity getCharacter() {
        return new CharacterEntity(
                getIntent().getIntExtra("characterId",0),
                getIntent().getStringExtra("characterName"),
                getIntent().getStringExtra("characterOrigin"),
                getIntent().getStringExtra("characterDevotion"),
                getIntent().getStringExtra("characterArchetype"),
                getIntent().getStringExtra("characterResume"),
                getIntent().getIntExtra("campaignId",1)
        );
    }

    private void submitFormData(Context context) {
        this.getCharacterNewInfo();
        switch (this.action) {
            case "edit":
                this.editCharacter(context);
                break;
            case "create":
                this.createCharacter(context);
                break;
        }
        finish();

    }

    private void createCharacter(Context context) {
        CharacterService.insertNewCharacter(context, this.character);
    }

    private void editCharacter(Context context) {
        CharacterService.editSelectedCharacter(context, this.character);
    }

    private void getCharacterNewInfo() {
        if (this.action.equals("edit")) {
            this.character = new CharacterEntity(
                    this.character.getId(),
                    editTextName.getText().toString(),
                    editTextOrigin.getText().toString(),
                    editTextDevotion.getText().toString(),
                    editTextArchetype.getText().toString(),
                    editTextResume.getText().toString(),
                    1
//                    this.character.getCampaignId()
            );
        } else if (this.action.equals("create"))  {
            this.character = new CharacterEntity(
                    0,
                    editTextName.getText().toString(),
                    editTextOrigin.getText().toString(),
                    editTextDevotion.getText().toString(),
                    editTextArchetype.getText().toString(),
                    editTextResume.getText().toString(),
                    1
            );
        }
    }

    private void setInputFields() {
        this.editTextName = findViewById(R.id.editTextName);
        this.editTextOrigin = findViewById(R.id.editTextOrigin);
        this.editTextDevotion = findViewById(R.id.editTextDevotion);
        this.editTextArchetype = findViewById(R.id.editTextArchetype);
        this.editTextResume = findViewById(R.id.editTextResume);
        this.spinnerCampaign = findViewById(R.id.campaignSpinner);
        this.submitButton = findViewById(R.id.submitButton);

        loadCampaignSpinner();

        this.getAction();
    }

    private void loadCampaignSpinner() {
       campaigns = CampaignService.getAllCampaigns(this);
        ArrayList<String> campaignsNames = new ArrayList<>();

        campaigns.forEach(campaign -> campaignsNames.add(campaign.getName()));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                campaignsNames
        );

//        adapter.setDropDownViewResource(R.layout.character_form);
        spinnerCampaign.setAdapter(adapter);
    }

    private void getAction() {
        this.action = getIntent().getStringExtra("action");
    }
}