package com.espressif.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.SpaceGroup;
import com.espressif.ui.activities.Home.NavBarActivity;
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
                        String spaceName = clickedCard.getGAAProjectSpaceGroupName();

                        // Storing data in SharedPreferences
                        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("MyPrefsPSGR", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("SPACE_GROUP_REF", name);
                        editor.apply();

                        SharedPreferences sharedPreferences1 = view.getContext().getSharedPreferences("MyPrefsPSGN", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putString("SPACE_GROUP_NAME", spaceName);
                        editor1.apply();

                        // Start the activity
                        Context context = view.getContext();
                        Intent intent = new Intent(context, NavBarActivity.class);
                        context.startActivity(intent);

                    }
                }
            });
        }
    }
}
