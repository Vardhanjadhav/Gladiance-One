package com.espressif.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.Project;
import com.espressif.ui.activities.Home.NavBarActivity;
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
        Context mContext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectNameTextView = itemView.findViewById(R.id.projectList);

            projectNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Project clickedCard = arrayList.get(position);
                        String projectRef = clickedCard.getGAAProjectRef();
                        String projectName = clickedCard.getGAAProjectName();

                        SharedPreferences sharedPreferencesRef = view.getContext().getSharedPreferences("MyPrefsPR", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencesRef.edit();
                        editor.putString("ProjectRef", projectRef);
                        editor.apply();

                        SharedPreferences sharedPreferencesName = view.getContext().getSharedPreferences("MyPrefsPN", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferencesName.edit();
                        editor1.putString("ProjectName", projectName);
                        editor1.apply();

                        projectNameTextView.setBackground(getDrawableForTheme(context, R.drawable.new_border_button_background));


                        int textColor = context.getResources().getColor(R.color.white);
                        if (isNightModeEnabled(context)) {
                            textColor = context.getResources().getColor(R.color.color_black);
                        }
                        projectNameTextView.setTextColor(textColor);



                        Intent intent = new Intent(context, NavBarActivity.class);
                        context.startActivity(intent);

                    }

                }
            });
        }
    }
    private boolean isNightModeEnabled(Context context) {
        int currentNightMode = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

    private Drawable getDrawableForTheme(Context context, @DrawableRes int drawableResId) {
        if (isNightModeEnabled(context)) {
            // Load night mode drawable
            return ContextCompat.getDrawable(context, R.drawable.new_border_button_background_night);
        } else {
            // Load day mode drawable
            return ContextCompat.getDrawable(context, drawableResId);
        }
    }
}
