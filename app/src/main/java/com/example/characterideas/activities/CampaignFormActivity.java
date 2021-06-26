package com.example.characterideas.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.characterideas.R;
import com.example.characterideas.entities.CampaignEntity;

import com.example.characterideas.services.CampaignService;

public class CampaignFormActivity extends AppCompatActivity {

    private CampaignEntity campaign;
    private EditText etCampaignName;
    private EditText etCampaignType;
    private EditText etCampaignSystem;
    private EditText etCampaignResume;
    private Button submitButton;
    private String action;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.campaign_form);

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
        this.campaign = this.getCampaign();

        etCampaignName.setText(campaign.getName());
        etCampaignType.setText(campaign.getType());
        etCampaignSystem.setText(campaign.getSystem());
        etCampaignResume.setText(campaign.getResume());
    }

    private CampaignEntity getCampaign() {
        return new CampaignEntity(
                getIntent().getIntExtra("campaignId",0),
                getIntent().getStringExtra("campaignName"),
                getIntent().getStringExtra("campaignType"),
                getIntent().getStringExtra("campaignSystem"),
                getIntent().getStringExtra("campaignResume")
        );
    }

    private void submitFormData(Context context) {
        this.getCharacterNewInfo();
        switch (this.action) {
            case "edit":
                this.editCharacter(context);
                break;
            case "create":
                this.createCampaign(context);
                break;
        }
        finish();
    }

    private void createCampaign(Context context) {
        CampaignService.insertNewCampaign(context, this.campaign);
    }

    private void editCharacter(Context context) {
        CampaignService.editSelectedCampaign(context, this.campaign);
    }

    private void getCharacterNewInfo() {
        if (this.action.equals("edit")) {
            this.campaign = new CampaignEntity(
                    this.campaign.getId(),
                    etCampaignName.getText().toString(),
                    etCampaignType.getText().toString(),
                    etCampaignSystem.getText().toString(),
                    etCampaignResume.getText().toString()
            );
        } else if (this.action.equals("create"))  {
            this.campaign = new CampaignEntity(
                    0,
                    etCampaignName.getText().toString(),
                    etCampaignType.getText().toString(),
                    etCampaignSystem.getText().toString(),
                    etCampaignResume.getText().toString()
                    );
        }
    }

    private void setInputFields() {
        this.etCampaignName = findViewById(R.id.campaignName);
        this.etCampaignType = findViewById(R.id.campaignType);
        this.etCampaignSystem = findViewById(R.id.campaignSystem);
        this.etCampaignResume = findViewById(R.id.campaignResume);
        this.submitButton = findViewById(R.id.submitCampaign);

        this.getAction();
    }

    private void getAction() {
        this.action = getIntent().getStringExtra("action");
    }
}
