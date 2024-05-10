package com.espressif.ui.adapters;

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

public class MyDeviceAdapter extends RecyclerView.Adapter<MyDeviceAdapter.MyViewHolder> {


    private static Context context;

    private List<String> mDLabel;
    private List<String> mDLabelName;
    private List<String> mDProject;
    private List<String> mDProjectName;
    private List<String> mDSpace;
    private List<String> mDSpaceName;
    private List<String> mDArea;
    private List<String> mDAreaName;
    private List<Integer> mDDelete;
    private List<Integer> mDRefresh;

    public MyDeviceAdapter(Context context, List<String> mDLabel, List<String> mDLabelName, List<String> mDProject, List<String> mDProjectName, List<String> mDSpace, List<String> mDSpaceName, List<String> mDArea, List<String> mDAreaName, List<Integer> mDDelete, List<Integer> mDRefresh) {
        this.context = context;
        this.mDLabel = mDLabel;
        this.mDLabelName = mDLabelName;
        this.mDProject = mDProject;
        this.mDProjectName = mDProjectName;
        this.mDSpace = mDSpace;
        this.mDSpaceName = mDSpaceName;
        this.mDArea = mDArea;
        this.mDAreaName = mDAreaName;
        this.mDDelete = mDDelete;
        this.mDRefresh = mDRefresh;
    }


    @NonNull
    @Override
    public MyDeviceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.my_device_recyleview, parent, false);
        return new MyDeviceAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDeviceAdapter.MyViewHolder holder, int position) {

        holder.TextView1.setText(mDLabel.get(position));
        holder.TextView2.setText(mDLabelName.get(position));
        holder.TextView3.setText(mDProject.get(position));
        holder.TextView4.setText(mDProjectName.get(position));
        holder.TextView5.setText(mDSpace.get(position));
        holder.TextView6.setText(mDSpaceName.get(position));
        holder.TextView7.setText(mDArea.get(position));
        holder.TextView8.setText(mDAreaName.get(position));
        holder.Image1.setImageResource(mDDelete.get(position));
        holder.Image2.setImageResource(mDRefresh.get(position));

    }

    @Override
    public int getItemCount() {
        return mDLabel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Image1, Image2;
        TextView TextView1, TextView2, TextView3, TextView4, TextView5, TextView6, TextView7, TextView8;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TextView1 = itemView.findViewById(R.id.mDLabel);
            TextView2 = itemView.findViewById(R.id.mDLabelName);
            TextView3 = itemView.findViewById(R.id.mDProject);
            TextView4 = itemView.findViewById(R.id.mDProjectName);
            TextView5 = itemView.findViewById(R.id.mDSpace);
            TextView6 = itemView.findViewById(R.id.mDSpaceName);
            TextView7 = itemView.findViewById(R.id.mDArea);
            TextView8 = itemView.findViewById(R.id.mDAreaName);

            Image1 = itemView.findViewById(R.id.mDDelete);
            Image2 = itemView.findViewById(R.id.mDRefresh);


        }

    }
}