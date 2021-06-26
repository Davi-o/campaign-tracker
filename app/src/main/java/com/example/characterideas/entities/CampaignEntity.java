package com.example.characterideas.entities;

public class CampaignEntity {
    private int id;
    private String name;
    private String type;
    private String system;
    private String resume;

    public CampaignEntity(int id, String name, String type, String system, String resume) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.system = system;
        this.resume = resume;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSystem() {
        return system;
    }

    public String getResume() {
        return resume;
    }

}
