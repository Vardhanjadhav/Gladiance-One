package com.espressif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.ProjectSpaceGroupReqModel;
import com.espressif.ui.models.SpaceSpaceGroupReqModel;
import com.espressif.wifi_provisioning.R;

import java.util.List;

public class SpaceSpaceGroupListAdapter extends RecyclerView.Adapter<SpaceSpaceGroupListAdapter.ViewHolder> {

    private List<SpaceSpaceGroupReqModel.Space> spaceGroups;

    public SpaceSpaceGroupListAdapter(List<SpaceSpaceGroupReqModel.Space> spaceGroups) {
        this.spaceGroups = spaceGroups;
    }

    @NonNull
    @Override
    public SpaceSpaceGroupListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.space_space_group_recycleview, parent, false);
        return new SpaceSpaceGroupListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpaceSpaceGroupListAdapter.ViewHolder holder, int position) {
        SpaceSpaceGroupReqModel.Space spaceGroup = spaceGroups.get(position);
        holder.spaceGroupNameTextView.setText(spaceGroup.getSpaceName());
        // You can also bind other data related to the space group here if needed
    }

    @Override
    public int getItemCount() {
        return spaceGroups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView spaceGroupNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spaceGroupNameTextView = itemView.findViewById(R.id.spaceSpaceGroupList);
        }
    }
}
