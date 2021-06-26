package com.example.characterideas.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.characterideas.R;
import com.example.characterideas.activities.adapters.CampaignAdapter;
import com.example.characterideas.activities.adapters.CharacterAdapter;
import com.example.characterideas.databinding.ActivityMainBinding;
import com.example.characterideas.entities.CampaignEntity;
import com.example.characterideas.entities.CharacterEntity;
import com.example.characterideas.services.CampaignService;
import com.example.characterideas.services.CharacterService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListView listView;
    private Spinner campaignFilters;
    private List<CharacterEntity> characterList;
    private List<CampaignEntity> campaignList;
    private final String CHARACTER_VIEW = "view_character";
    private final String CAMPAIGN_VIEW = "view_campaign";
    private String actualView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fab.setVisibility(View.INVISIBLE);
        binding.showMenu.setVisibility(View.INVISIBLE);

        binding.showMenu.setOnClickListener(view -> recreate());

        binding.characterTab.setOnClickListener(view -> {
            this.actualView = CHARACTER_VIEW;
            loadCharactersOnScreen();
            hideMainButtons();
            setFloatingActionButtonAction(CHARACTER_VIEW);
        });

        binding.campaignTab.setOnClickListener(view -> {
            this.actualView = CAMPAIGN_VIEW;
            loadCampaignsOnScreen();
            hideMainButtons();
            setFloatingActionButtonAction(CAMPAIGN_VIEW);
        });

        listView =findViewById(R.id.charactersList);
        loadFilters();
    }

    private void loadFilters() {
        //@todo filter characters by campaigns
        //campaignFilters = findViewById(R.id.campaignSpinner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.actualView != null) {
            switch (this.actualView) {
                case CAMPAIGN_VIEW:
                    loadCampaignsOnScreen();
                    break;
                case CHARACTER_VIEW:
                    loadCharactersOnScreen();
                    break;
            }
        }
    }

    private void setFloatingActionButtonAction(String which) {
        switch (which) {
            case CHARACTER_VIEW:
                binding.fab.setOnClickListener(view -> {
                    Intent intent = new Intent(MainActivity.this, CharacterFormActivity.class);
                    intent.putExtra("action", "create");
                    startActivity(intent);
                });
                break;
            case CAMPAIGN_VIEW:
                binding.fab.setOnClickListener(view -> {
                    Intent intent = new Intent(MainActivity.this, CampaignFormActivity.class);
                    intent.putExtra("action", "create");
                    startActivity(intent);
                });
                break;
        }
    }

    private void hideMainButtons() {
        binding.showMenu.setVisibility(View.VISIBLE);
        binding.fab.setVisibility(View.VISIBLE);
        binding.characterTab.setVisibility(View.INVISIBLE);
        binding.campaignTab.setVisibility(View.INVISIBLE);
        findViewById(R.id.tvCampaign).setVisibility(View.INVISIBLE);
        findViewById(R.id.tvCharacter).setVisibility(View.INVISIBLE);
    }

    private void loadCampaignsOnScreen() {
        campaignList = CampaignService.getAllCampaigns(this);
        CampaignAdapter adapter = new CampaignAdapter(this, campaignList);
        listView.setAdapter(adapter);
        configureCampaignList();
    }

    private void configureCampaignList() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CampaignEntity selectedCampaign = campaignList.get(position);

            Intent intent = new Intent(MainActivity.this, CampaignFormActivity.class);

            intent.putExtra("action", "edit");

            intent.putExtra("campaignId", selectedCampaign.getId());
            intent.putExtra("campaignName", selectedCampaign.getName());
            intent.putExtra("campaignType", selectedCampaign.getType());
            intent.putExtra("campaignSystem", selectedCampaign.getSystem());
            intent.putExtra("campaignResume", selectedCampaign.getResume());

            startActivity(intent);
        });


        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            CampaignEntity selectedCampaign = campaignList.get(position);
            deleteSelectedCampaign(selectedCampaign);
            return true;
        });
    }

    private void deleteSelectedCampaign(CampaignEntity selectedCampaign) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(android.R.drawable.ic_delete);
        alert.setTitle(R.string.deleteAttention);
        alert.setMessage(R.string.deleteCampaignMessage);

        alert.setNeutralButton(R.string.cancel, null);
        alert.setPositiveButton(R.string.justDoIt, (DialogInterface dialogInterface, int position) -> {
            CampaignService.deleteCampaign(MainActivity.this, selectedCampaign);
            loadCampaignsOnScreen();
        });

        alert.show();
    }

    private void loadCharactersOnScreen() {
        characterList = CharacterService.getAllCharacters(this);
        CharacterAdapter adapter = new CharacterAdapter(this, characterList);
        listView.setAdapter(adapter);
        configureCharacterList();
    }

    private void configureCharacterList() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            CharacterEntity selectedCharacter = characterList.get(position);

            Intent intent = new Intent(MainActivity.this, CharacterFormActivity.class);

            intent.putExtra("action", "edit");

            intent.putExtra("characterId", selectedCharacter.getId());
            intent.putExtra("characterName", selectedCharacter.getName());
            intent.putExtra("characterOrigin", selectedCharacter.getOrigin());
            intent.putExtra("characterDevotion", selectedCharacter.getDevotion());
            intent.putExtra("characterArchetype", selectedCharacter.getArchetype());
            intent.putExtra("characterResume", selectedCharacter.getResume());

            startActivity(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            CharacterEntity selectedCharacter = characterList.get(position);
            deleteSelectedCharacter(selectedCharacter);
            return true;
        });
    }

    private void deleteSelectedCharacter(CharacterEntity selectedCharacter) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(android.R.drawable.ic_delete);
        alert.setTitle(R.string.deleteAttention);
        alert.setMessage(R.string.deleteCharacterMessage);

        alert.setNeutralButton(R.string.cancel, null);
        alert.setPositiveButton(R.string.justDoIt, (DialogInterface dialogInterface, int position) -> {
            CharacterService.deleteCharacter(MainActivity.this, selectedCharacter);
            loadCharactersOnScreen();
        });

        alert.show();
    }

}