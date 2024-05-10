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

import com.espressif.ui.activities.AddDeviceActivity;
import com.espressif.ui.activities.EspMainActivity;
import com.espressif.ui.models.lnstallerlandingpage.Controls;
import com.espressif.ui.models.scene.Configuration;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

public class SceneCheckAdapter extends RecyclerView.Adapter<SceneCheckAdapter.ViewHolder> {
    private List<Controls> ConArrayList;
    private List<Configuration> ConfigArrayList;


    public SceneCheckAdapter(ArrayList<Controls> ConArrayList, ArrayList<Configuration>ConfigArrayList) {
        this.ConArrayList = ConArrayList;
        this.ConfigArrayList = ConfigArrayList;
    }

    @NonNull
    @Override
    public SceneCheckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scene_control_card, parent, false);
        return new SceneCheckAdapter.ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull SceneCheckAdapter.ViewHolder holder, int position) {

        Controls control = ConArrayList.get(position);

       // int positions = 0;
        for (int i=0;i< ConfigArrayList.size(); i++) {

            Configuration configuration = ConfigArrayList.get(i);

            holder.deviceNameTextView.setText(control.getGaaProjectSpaceTypePlannedDeviceName());
            if((configuration.getgAAProjectSpaceTypePlannedDeviceRef().equals(control.getGaaProjectSpaceTypePlannedDeviceRef()))){
                Log.e(TAG, "onBindViewHolder: "+configuration.getgAAProjectSpaceTypePlannedDeviceRef() + " " + control.getGaaProjectSpaceTypePlannedDeviceRef() );
                holder.deviceNameCheckBox.setChecked(true);
            }
        }

        holder.deviceNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean provisionStatus = control.isProvisioned();
                SharedPreferences sharedPreferences13 = view.getContext().getSharedPreferences("my_shared_prefty", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences13.edit();
                Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: " + sharedPreferences13);
                editor2.putBoolean("KEY_USERNAMEw", provisionStatus);
                editor2.apply();

                if (!control.isProvisioned()) {
                    LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
                    Long GaaProjectSpaceTypePlannedDeviceRef = control.getGaaProjectSpaceTypePlannedDeviceRef();
                    SharedPreferences sharedPreferences = inflater.getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: " + GaaProjectSpaceTypePlannedDeviceRef);
                    editor.putLong("KEY_USERNAME", GaaProjectSpaceTypePlannedDeviceRef);
                    editor.apply();



                    Toast.makeText(inflater.getContext(), "This is a toast message" + control.getGaaProjectSpaceTypePlannedDeviceName(), Toast.LENGTH_SHORT).show();
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), AddDeviceActivity.class));
                } else {
                    LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());

                    Long GaaProjectSpaceTypePlannedDeviceRef = control.getGaaProjectSpaceTypePlannedDeviceRef();
                    SharedPreferences sharedPreferences = inflater.getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor3 = sharedPreferences.edit();
                    Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: " + GaaProjectSpaceTypePlannedDeviceRef);
                    editor3.putLong("KEY_USERNAME", GaaProjectSpaceTypePlannedDeviceRef);
                    editor3.apply();


                    String nodeId = control.getNodeId();
                    SharedPreferences sharedPreferences2 = inflater.getContext().getSharedPreferences("my_shared_prefe", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences2.edit();
                    Log.e(TAG, "Node Id: " + nodeId);
                    editor.putString("KEY_USERNAMEs", nodeId);
                    editor.apply();

                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), EspMainActivity.class));
                }
            }

        });

    }



    @Override
    public int getItemCount() {
        return ConArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView deviceNameTextView;
        CheckBox deviceNameCheckBox;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            deviceNameTextView = itemView.findViewById(R.id.Device_name_text_view);
            deviceNameCheckBox = itemView.findViewById(R.id.DN_checkbox);
            deviceNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

        }
    }
}