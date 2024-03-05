package com.espressif.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.login.AreaLandingActivity;
import com.espressif.ui.login.ProjectSpaceGroupActivity;
import com.espressif.ui.login.ProjectSpaceLandingActivity;
import com.espressif.ui.models.Project;
import com.espressif.ui.models.ProjectSpaceGroupReqModel;
import com.espressif.ui.models.Space;
import com.espressif.ui.models.SpaceGroup;
import com.espressif.wifi_provisioning.R;

import java.util.List;

public class ProjectSpaceGroupListAdapter extends RecyclerView.Adapter<ProjectSpaceGroupListAdapter.ViewHolder> {

    private static List<SpaceGroup> arraylist;

    public ProjectSpaceGroupListAdapter(List<SpaceGroup> arraylist) {
        this.arraylist = arraylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_space_group_recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpaceGroup spaceGroup = arraylist.get(position);
        holder.spaceGroupNameTextView.setText(spaceGroup.getGAAProjectSpaceGroupName());
        // You can also bind other data related to the space group here if needed
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView spaceGroupNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spaceGroupNameTextView = itemView.findViewById(R.id.projectSpaceGroupList);

            spaceGroupNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        SpaceGroup clickedCard = arraylist.get(position);
                        String name = clickedCard.getGAAProjectSpaceGroupRef();


                        Context context = view.getContext();
                        Intent intent = new Intent(context, ProjectSpaceLandingActivity.class);
                        intent.putExtra("SPACE_GROUP_REF", name);


                        context.startActivity(intent);


                    }
                }
            });
        }
    }
}
