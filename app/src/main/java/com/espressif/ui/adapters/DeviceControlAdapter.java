package com.espressif.ui.adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.activities.EspMainActivity;
import com.espressif.ui.models.guestlandingpage.Controls;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class DeviceControlAdapter extends RecyclerView.Adapter<DeviceControlAdapter.ViewHolder> {
    private List<Controls> controls;
    private Context context;



    public DeviceControlAdapter(List<Controls> controls, Context context ) {
        this.controls = controls;
        this.context = context;

    }


    @NonNull
    @Override
    public DeviceControlAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guest_control, parent, false);
        return new DeviceControlAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DeviceControlAdapter.ViewHolder holder, int position) {
        Controls control = controls.get(position);
        holder.deviceNameTextView.setText(control.getgAAProjectSpaceTypePlannedDeviceName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //                boolean provisionStatus = control.isProvisioned();
//                SharedPreferences sharedPreferences13 = view.getContext().getSharedPreferences("my_shared_prefty", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor2 = sharedPreferences13.edit();
//                Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: "+sharedPreferences13 );
//                editor2.putBoolean("KEY_USERNAMEw", provisionStatus);
//                editor2.apply();
//
//                    if (!control.isProvisioned()) {
//                        LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
//                        Long GaaProjectSpaceTypePlannedDeviceRef = control.getGaaProjectSpaceTypePlannedDeviceRef();
//                        SharedPreferences sharedPreferences = inflater.getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: "+GaaProjectSpaceTypePlannedDeviceRef );
//                        editor.putLong("KEY_USERNAME", GaaProjectSpaceTypePlannedDeviceRef);
//                        editor.apply();
//
////                   boolean provisionStatus = control.isProvisioned();
////
////                        boolean provisionStatus = control.isProvisioned();
////                        SharedPreferences sharedPreferences13 = inflater.getContext().getSharedPreferences("my_shared_prefty", Context.MODE_PRIVATE);
////                        SharedPreferences.Editor editor2 = sharedPreferences13.edit();
////                        Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: "+sharedPreferences13 );
////                        editor2.putBoolean("KEY_USERNAMEw", provisionStatus);
////                        editor2.apply();
//
//
//
//
//
//                        Toast.makeText(inflater.getContext(), "This is a toast message"+control.getGaaProjectSpaceTypePlannedDeviceName(), Toast.LENGTH_SHORT).show();
//                        holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), AddDeviceActivity.class));
//                    } else {


//
                        LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());

                        Long GaaProjectSpaceTypePlannedDeviceRef = Long.valueOf(control.getgAAProjectSpaceTypePlannedDeviceRef());
                        SharedPreferences sharedPreferences = inflater.getContext().getSharedPreferences("my_shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = sharedPreferences.edit();
                        Log.e(TAG, "GaaProjectSpaceTypePlannedDeviceName11: "+GaaProjectSpaceTypePlannedDeviceRef );
                        editor3.putLong("KEY_USERNAME", GaaProjectSpaceTypePlannedDeviceRef);
                        editor3.apply();



                        String nodeId = control.getNodeId();
                        SharedPreferences sharedPreferences2 = inflater.getContext().getSharedPreferences("my_shared_prefe", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences2.edit();
                        Log.e(TAG, "Node Id: "+nodeId );
                        editor.putString("KEY_USERNAMEs", nodeId);
                        editor.apply();

                        holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), EspMainActivity.class));
//                    }

            }

        });

    }



    @Override
    public int getItemCount() {
        return controls.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView deviceNameTextView;
        LinearLayout llGuestControl;
        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            deviceNameTextView = itemView.findViewById(R.id.btnTitle);
            llGuestControl = itemView.findViewById(R.id.llGuestControl);
            deviceNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }



}
