package com.example.characterideas.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.characterideas.R;
import com.example.characterideas.activities.adapters.CharacterAdapter;
import com.example.characterideas.databinding.ActivityMainBinding;
import com.example.characterideas.entities.CharacterEntity;
import com.example.characterideas.services.CharacterService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListView charactersListView;
    private List<CharacterEntity> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            intent.putExtra("action", "create");
            startActivity(intent);
        });

        charactersListView =findViewById(R.id.charactersList);

        loadCharactersOnScreen();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadCharactersOnScreen();
    }

    private void loadCharactersOnScreen() {
        characterList = CharacterService.getAllCharacters(this);
        CharacterAdapter adapter = new CharacterAdapter(this, characterList);
        charactersListView.setAdapter(adapter);
        configureListView();
    }

    private void configureListView() {

        charactersListView.setOnItemClickListener((parent, view, position, id) -> {
            CharacterEntity selectedCharacter = characterList.get(position);

            Intent intent = new Intent(MainActivity.this, FormActivity.class);

            intent.putExtra("action", "edit");

            intent.putExtra("characterId", selectedCharacter.getId());
            intent.putExtra("characterName", selectedCharacter.getName());
            intent.putExtra("characterOrigin", selectedCharacter.getOrigin());
            intent.putExtra("characterDevotion", selectedCharacter.getDevotion());
            intent.putExtra("characterArchetype", selectedCharacter.getArchetype());
            intent.putExtra("characterResume", selectedCharacter.getResume());

            startActivity(intent);
        });

        charactersListView.setOnItemLongClickListener((parent, view, position, id) -> {
            CharacterEntity selectedCharacter = characterList.get(position);
            deleteSelectedCharacter(selectedCharacter);
            return true;
        });
    }

    private void deleteSelectedCharacter(CharacterEntity selectedCharacter) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setIcon(android.R.drawable.ic_delete);
        alert.setTitle(R.string.deleteAttention);
        alert.setMessage(R.string.deleteMessage);

        alert.setNeutralButton(R.string.cancel, null);
        alert.setPositiveButton(R.string.justDoIt, (DialogInterface dialogInterface, int position) -> {
            CharacterService.deleteCharacter(MainActivity.this, selectedCharacter);
            loadCharactersOnScreen();
        });

        alert.show();
    }

}