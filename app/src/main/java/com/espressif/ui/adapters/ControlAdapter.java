package com.espressif.ui.adapters;

import android.service.controls.Control;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.lnstallerlandingpage.Controls;
import com.espressif.ui.models.lnstallerlandingpage.InstallerControl;
import com.espressif.wifi_provisioning.R;

import java.util.List;


public class ControlAdapter extends RecyclerView.Adapter<ControlAdapter.ViewHolder> {
    private List<Controls> controls;

    public ControlAdapter(List<Controls> controls) {
        this.controls = controls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_control_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Controls control = controls.get(position);
        holder.deviceNameTextView.setText(control.getGaaProjectSpaceTypePlannedDeviceName());
    }

    @Override
    public int getItemCount() {
        return controls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView deviceNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceNameTextView = itemView.findViewById(R.id.tv_device_name);
        }
    }
}