package com.example.characterideas.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.characterideas.R;
import com.example.characterideas.entities.CharacterEntity;

import java.util.List;

public class CharacterAdapter extends BaseAdapter {

    private List<CharacterEntity> characterList;
    private Context context;
    private LayoutInflater inflater;

    public CharacterAdapter(Context context, List<CharacterEntity> characterList) {
        this.characterList = characterList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return characterList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.characterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.characterList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CharacterView characterView;

        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.character_info_list, null);

            characterView = new CharacterView();

            characterView.textViewName = convertView.findViewById(R.id.name);
            characterView.textViewOrigin = convertView.findViewById(R.id.origin);
            characterView.textViewDevotion = convertView.findViewById(R.id.devotion);
            characterView.textViewArchetype = convertView.findViewById(R.id.archetype);
            characterView.textViewResume = convertView.findViewById(R.id.resume);

            characterView.linearLayout = convertView.findViewById(R.id.characterInfo);

            convertView.setTag(characterView);
        } else {
            characterView = (CharacterView) convertView.getTag();
        }

        CharacterEntity character = this.characterList.get(position);
        characterView.textViewName.setText(character.getName());
        characterView.textViewOrigin.setText(character.getOrigin());
        characterView.textViewDevotion.setText(character.getDevotion());
        characterView.textViewArchetype.setText(character.getArchetype());
        characterView.textViewResume.setText(character.getResume());

        return convertView;
    }

    private class CharacterView {
        TextView textViewName;
        TextView textViewOrigin;
        TextView textViewDevotion;
        TextView textViewArchetype;
        TextView textViewResume;
        LinearLayout linearLayout;
    }
}
