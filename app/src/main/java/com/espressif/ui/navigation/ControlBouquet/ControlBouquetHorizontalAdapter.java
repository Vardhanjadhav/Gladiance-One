package com.espressif.ui.navigation.ControlBouquet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class ControlBouquetHorizontalAdapter extends RecyclerView.Adapter<ControlBouquetHorizontalAdapter.ViewHolder>  {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<String> data;
    private final OnItemClickListener clickListener;

    private Context context;

    private int selectedPosition = RecyclerView.NO_POSITION;

    public ControlBouquetHorizontalAdapter(List<String> data, OnItemClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.btnTitle);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_control_bouquet, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item = data.get(position);
        holder.textViewItem.setText(item);

        holder.itemView.setBackgroundResource(selectedPosition == position ? R.drawable.border_highlight : 0);

        holder.textViewItem.setTextColor(selectedPosition == position ? Color.parseColor("#FFA500") : R.style.TEXT);



        holder.textViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);

                notifyItemChanged(selectedPosition);
                selectedPosition = position;
                notifyItemChanged(selectedPosition);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
