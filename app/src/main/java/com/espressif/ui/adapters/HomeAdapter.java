package com.espressif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class HomeAdapter  extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context context;
    private List<String> titles1;

    public HomeAdapter(Context context, List<String> titles1) {

        this.context = context;
        this.titles1 = titles1;

    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.room_item, parent, false);
        return new HomeAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, int position) {
        holder.mTextView.setText(titles1.get(position));

    }

    @Override
    public int getItemCount() {
        return titles1.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.btnTitle);


        }
    }
}