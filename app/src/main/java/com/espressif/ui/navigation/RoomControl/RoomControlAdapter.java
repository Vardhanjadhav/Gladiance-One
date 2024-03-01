package com.espressif.ui.navigation.RoomControl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class RoomControlAdapter extends RecyclerView.Adapter<RoomControlAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<String> data;
    private final OnItemClickListener clickListener;
    //private int selectedPosition = RecyclerView.NO_POSITION;

    private static Context context;
    private static int selectedPosition = -1;

    public RoomControlAdapter(List<String> data, OnItemClickListener clickListener) {
        this.data = data;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String item = data.get(position);
        holder.textViewItem.setText(item);

        holder.itemView.setBackgroundResource(selectedPosition == position ? R.drawable.border_highlight : 0);
        holder.textViewItem.setTextColor(selectedPosition == position ? Color.parseColor("#FFA500") : R.style.TEXT);

        holder.textViewItem.setOnClickListener(v -> {
            clickListener.onItemClick(position);

            notifyItemChanged(selectedPosition);
            selectedPosition = position;
            notifyItemChanged(selectedPosition);
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedPosition = selectedPosition;
                selectedPosition = position;
                notifyItemChanged(previousSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.btnTitle);
        }

        public void bindData(int position) {
            // Determine the current theme and set text color and style accordingly
            int textColor;
            int textStyle;

            if (selectedPosition == position) {
                textColor = isDarkTheme()
                        ? ContextCompat.getColor(context, R.color.link_color)
                        : ContextCompat.getColor(context, R.color.white);

                textStyle = R.style.TEXT;
            } else {
                textColor = isDarkTheme()
                        ? ContextCompat.getColor(context, R.color.link_color)
                        : ContextCompat.getColor(context, R.color.color_black);

                textStyle = 0; // Set to 0 or another default style if not needed
            }

            textViewItem.setTextColor(textColor);
            if (textStyle != 0) {
                textViewItem.setTextAppearance(context, textStyle);
            }
        }
    }

    // Helper method to check if the current theme is dark
    private static boolean isDarkTheme() {
        int nightModeFlags = context.getResources().getConfiguration().uiMode &
                Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }
}
