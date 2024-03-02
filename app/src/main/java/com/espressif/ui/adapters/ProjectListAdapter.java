package com.espressif.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.activities.FanActivity;
import com.espressif.ui.login.ForgotPasswordActivity;
import com.espressif.ui.login.LoginActivity;
import com.espressif.ui.login.ProjectSpaceActivity;
import com.espressif.ui.login.ProjectSpaceGroupActivity;
import com.espressif.ui.models.Devices;
import com.espressif.ui.models.Project;
import com.espressif.ui.models.ProjectSpaceRequestModel;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

     private static List<Project> arrayList;

    public ProjectListAdapter(ArrayList<Project> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_name_recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Project project = arrayList.get(position);
        holder.projectNameTextView.setText(project.getGAAProjectName());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView projectNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectNameTextView = itemView.findViewById(R.id.projectList);

            projectNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Project clickedCard = arrayList.get(position);
                        String name = clickedCard.getGAAProjectRef();


                        Context context = view.getContext();
                        Intent intent = new Intent(context, ProjectSpaceGroupActivity.class);
                        intent.putExtra("MESSAGE_KEY", name);

                        context.startActivity(intent);

                    }

                }
            });
        }
    }
}
