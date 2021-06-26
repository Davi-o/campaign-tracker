package com.example.characterideas.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.characterideas.R;
import com.example.characterideas.entities.CampaignEntity;
import com.example.characterideas.entities.CharacterEntity;

import java.util.List;

public class CampaignAdapter extends BaseAdapter {

    private List<CampaignEntity> campaignList;
    private Context context;
    private LayoutInflater inflater;

    public CampaignAdapter(Context context, List<CampaignEntity> campaignList) {
        this.campaignList = campaignList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return campaignList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.campaignList.get(position);
    }

    @Override
    public long getItemId(int position) { return this.campaignList.get(position).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CampaignView campaignView;

        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.campaing_info_list, null);

            campaignView = new CampaignView();

            campaignView.textViewCampaignName = convertView.findViewById(R.id.campaignName);
            campaignView.textViewCampaignType = convertView.findViewById(R.id.campaignType);
            campaignView.textViewCampaignSystem = convertView.findViewById(R.id.campaignSystem);
            campaignView.textViewCampaignResume = convertView.findViewById(R.id.campaignResume);

            campaignView.linearLayout = convertView.findViewById(R.id.campaignInfo);

            convertView.setTag(campaignView);
        } else {
            campaignView = (CampaignView) convertView.getTag();
        }

        CampaignEntity campaign = this.campaignList.get(position);
        campaignView.textViewCampaignName.setText(campaign.getName());
        campaignView.textViewCampaignType.setText(campaign.getType());
        campaignView.textViewCampaignSystem.setText(campaign.getSystem());
        campaignView.textViewCampaignResume.setText(campaign.getResume());

        return convertView;
    }

    private class CampaignView {
        TextView textViewCampaignName;
        TextView textViewCampaignType;
        TextView textViewCampaignSystem;
        TextView textViewCampaignResume;
        LinearLayout linearLayout;
    }
}
