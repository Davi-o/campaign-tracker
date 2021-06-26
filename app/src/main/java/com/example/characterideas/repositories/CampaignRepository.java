package com.example.characterideas.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.characterideas.database.DatabaseConnection;
import com.example.characterideas.entities.CampaignEntity;
import com.example.characterideas.entities.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

public class CampaignRepository extends DatabaseConnection implements CRUDRepository<CampaignEntity> {
    public CampaignRepository(Context context) {
        super(context);
    }

    @Override
    public List<CampaignEntity> readAll() {
        Cursor campaign = readableDatabase(getContext()).rawQuery(
                "select id, name, type, system, resume from campaign",
                null
        );
        return formatResponse(campaign);
    }

    private List<CampaignEntity> formatResponse(Cursor campaigns) {
        List<CampaignEntity> campaignList = new ArrayList<>();

        if(campaigns.getCount() > 0) {
            campaigns.moveToFirst();
            do{
                CampaignEntity campaign = new CampaignEntity(
                        campaigns.getInt(0),
                        campaigns.getString(1),
                        campaigns.getString(2),
                        campaigns.getString(3),
                        campaigns.getString(4)
                );
                campaignList.add(campaign);
            } while (campaigns.moveToNext());
        }

        campaigns.close();
        return campaignList;
    }

    @Override
    public CampaignEntity readById() {
        return null;
    }

    @Override
    public void create(CampaignEntity campaign) {
        ContentValues values = getContentValues(campaign);
        writableDatabase(getContext()).insert("campaign", null, values);
    }

    private ContentValues getContentValues(CampaignEntity campaign) {
        ContentValues values = new ContentValues();

        if (campaign.getId() != 0) {
            values.put("id", campaign.getId());
        }
        values.put("name", campaign.getName());
        values.put("type", campaign.getType());
        values.put("system", campaign.getSystem());
        values.put("resume", campaign.getResume());

        return values;
    }

    @Override
    public void update(CampaignEntity campaign) {
        ContentValues values = getContentValues(campaign);
        writableDatabase(getContext()).update(
                "campaign" ,
                values,
                "id = ?",
                new String[]{String.valueOf(campaign.getId())});
    }

    @Override
    public void delete(CampaignEntity campaign) {
        writableDatabase(getContext()).delete(
                "campaign",
                "id = ?",
                new String[]{String.valueOf(campaign.getId())});
    }
}
