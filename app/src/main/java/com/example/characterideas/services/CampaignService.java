package com.example.characterideas.services;

import android.content.Context;

import com.example.characterideas.entities.CampaignEntity;
import com.example.characterideas.entities.CharacterEntity;
import com.example.characterideas.repositories.CampaignRepository;
import com.example.characterideas.repositories.CharacterRepository;

import java.util.List;

public class CampaignService {

    public static List<CampaignEntity> getAllCampaigns(Context context) {
        return new CampaignRepository(context).readAll();
    }

    public static void insertNewCampaign(Context context, CampaignEntity campaign) {
        new CampaignRepository(context).create(campaign);
    }

    public static void editSelectedCampaign(Context context, CampaignEntity campaign) {
        new CampaignRepository(context).update(campaign);
    }

    public static void deleteCampaign(Context context, CampaignEntity selectedCampaign) {
        new CampaignRepository(context).delete(selectedCampaign);
    }

}
