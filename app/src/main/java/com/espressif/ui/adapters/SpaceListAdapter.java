package com.espressif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.ProjectSpaceRequestModel;
import com.espressif.wifi_provisioning.R;

import java.util.List;

public class SpaceListAdapter extends RecyclerView.Adapter<SpaceListAdapter.ViewHolder> {

    private List<ProjectSpaceRequestModel.Space> spaces;

    public SpaceListAdapter(List<ProjectSpaceRequestModel.Space> spaces) {
        this.spaces = spaces;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.space_name_recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectSpaceRequestModel.Space space = spaces.get(position);
        holder.spaceNameTextView.setText(space.getSpaceName());
        // You can also bind other data related to the space here if needed
    }

    @Override
    public int getItemCount() {
        return spaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView spaceNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spaceNameTextView = itemView.findViewById(R.id.spaceName);
        }
    }
}
