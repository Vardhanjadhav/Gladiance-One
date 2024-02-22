package com.espressif.ui.navigation.MyProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class MyProjectAdapter extends RecyclerView.Adapter<MyProjectAdapter.MyViewHolder> {


    private static Context context;

    private List<String> tvProject;
    private List<String> tvProjectName;
    private List<String> tvSpace;
    private List<String> tvSpaceName;
    private List<Integer> imgView;
    private List<Integer> imgDelete;

    public MyProjectAdapter(Context context, List<String> tvProject, List<String> tvProjectName, List<String> tvSpace, List<String> tvSpaceName, List<Integer> imgView, List<Integer> imgDelete) {
        this.context = context;
        this.tvProject = tvProject;
        this.tvProjectName = tvProjectName;
        this.tvSpace = tvSpace;
        this.tvSpaceName = tvSpaceName;
        this.imgView = imgView;
        this.imgDelete = imgDelete;
    }


    @NonNull
    @Override
    public MyProjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.myproject_recyleview, parent, false);
        return new MyProjectAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProjectAdapter.MyViewHolder holder, int position) {

        holder.TextView1.setText(tvProject.get(position));
        holder.TextView2.setText(tvProjectName.get(position));
        holder.TextView3.setText(tvSpace.get(position));
        holder.TextView4.setText(tvSpaceName.get(position));
        holder.Image1.setImageResource(imgView.get(position));
        holder.Image2.setImageResource(imgDelete.get(position));


    }

    @Override
    public int getItemCount() {
        return tvProject.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Image1, Image2;
        TextView TextView1, TextView2, TextView3, TextView4;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TextView1 = itemView.findViewById(R.id.tvProject);
            TextView2 = itemView.findViewById(R.id.tvProjectName);
            TextView3 = itemView.findViewById(R.id.tvSpace);
            TextView4 = itemView.findViewById(R.id.tvSpaceName);
            Image1 = itemView.findViewById(R.id.imgView);
            Image2 = itemView.findViewById(R.id.imgDelete);


        }
    }
}
