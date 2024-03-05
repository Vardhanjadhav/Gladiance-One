package com.espressif.ui.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.Project;
import com.espressif.ui.models.arealandingmodel.Area;
import com.espressif.ui.navigation.NavBarActivity;
import com.espressif.wifi_provisioning.R;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> {
    private static List<Area> areas;

    public AreaAdapter(List<Area> areas) {
        this.areas = areas;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area_name, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        Area area = areas.get(position);
        holder.areaNameTextView.setText(area.getGAAProjectSpaceTypeAreaName());

    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public static class AreaViewHolder extends RecyclerView.ViewHolder {
        TextView areaNameTextView;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            areaNameTextView = itemView.findViewById(R.id.areaName);

            areaNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Area clickedCard = areas.get(position);
                        Long name = Long.valueOf((clickedCard.getGAAProjectSpaceTypeAreaRef()));


                        Context context = view.getContext();
                        Intent intent = new Intent(context,AreaLandingActivity.class);
                        intent.putExtra("SPACE_TYPE_AREA_REF", name);

                        context.startActivity(intent);

                    }

                }
            });
        }
    }
}
