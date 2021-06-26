package com.example.characterideas.entities;

import androidx.annotation.Nullable;

public class CharacterEntity {
    private int id;
    private String name;
    private String origin;
    private String devotion;
    private String archetype;
    private String resume;
    private int campaignId;

    public CharacterEntity(int id, String name, String origin, String devotion, String archetype, String resume, int campaignId) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.devotion = devotion;
        this.archetype = archetype;
        this.resume = resume;
        this.campaignId = campaignId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDevotion() {
        return devotion;
    }

    public String getArchetype() {
        return archetype;
    }

    public String getResume() {
        return resume;
    }

    public int getCampaignId() {
        return campaignId;
    }
}
