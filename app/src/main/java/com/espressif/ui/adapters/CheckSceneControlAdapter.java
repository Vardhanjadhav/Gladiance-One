package com.espressif.ui.adapters;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.scene.Configuration;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

public class CheckSceneControlAdapter extends RecyclerView.Adapter<CheckSceneControlAdapter.ViewHolder> {
    private List<Configuration> ConfigArrayList;


    public CheckSceneControlAdapter(ArrayList<Configuration> ConfigArrayList) {
        this.ConfigArrayList = ConfigArrayList;

    }



    @NonNull
    @Override
    public CheckSceneControlAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scene_control_card, parent, false);
        return new CheckSceneControlAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CheckSceneControlAdapter.ViewHolder holder, int position) {
        Configuration configuration = ConfigArrayList.get(position);
        holder.deviceNameCheckBox.setChecked(configuration.getgAAProjectSpaceTypePlannedDeviceName() != null);


    }

    @Override
    public int getItemCount() {
        return ConfigArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox deviceNameCheckBox;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);

            deviceNameCheckBox = itemView.findViewById(R.id.DN_checkbox);
        }
    }

}
