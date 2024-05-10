package com.espressif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.guestlandingpage.GuestControls;
import com.espressif.wifi_provisioning.R;

import java.util.List;


public class ControlAdapter extends RecyclerView.Adapter<ControlAdapter.ViewHolder1> {

    private int selectedPosition = RecyclerView.NO_POSITION;


    public interface OnControlTypeClickListener {
        void onControlTypeClicked(GuestControls control);

    }
    private static List<GuestControls> controls1;
    private Context context;
    private static OnControlTypeClickListener listener;

    public ControlAdapter(List<GuestControls> controls, Context context,OnControlTypeClickListener listener) {
        this.controls1 = controls;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ControlAdapter.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_control_card, parent, false);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlAdapter.ViewHolder1 holder, int position) {

        GuestControls control = controls1.get(position);

        holder.deviceNameTextView.setText(control.getControlTypeName());


        holder.itemView.setBackgroundResource(selectedPosition == position ? R.drawable.border_highlight : 0);

        holder.deviceNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onControlTypeClicked(control);

                notifyItemChanged(selectedPosition);
                selectedPosition = position;
                notifyItemChanged(selectedPosition);
            }
        });
    }


    @Override
    public int getItemCount() {
        return controls1.size();
    }
    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        AppCompatButton deviceNameTextView;
        public ViewHolder1(@NonNull View itemView ) {
            super(itemView);
            deviceNameTextView = itemView.findViewById(R.id.btnTitle);

            deviceNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    GuestControls control = controls1.get(getAdapterPosition());
                    if (listener != null) {
                        listener.onControlTypeClicked(control);
                    }
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        // Pass the list of controls to DeviceControlAdapter
//                        listener.onControlTypeClicked(controls1.get(0));
//                    }
                }
            });
        }
    }

}