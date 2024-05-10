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

public class TelephoneAdapter extends RecyclerView.Adapter<TelephoneAdapter.MyViewHolder> {

    private Context context;
    private List<String> tvtitle;
    private List<Integer> imgcall;
    private List<String> tvnumber;
    private List<Integer> imgthim;

    public TelephoneAdapter(Context context, List<String> tvtitle, List<Integer> imgcall, List<String> tvnumber, List<Integer> imgthim) {

        this.context = context;
        this.tvtitle = tvtitle;
        this.imgcall = imgcall;
        this.tvnumber = tvnumber;
        this.imgthim = imgthim;

    }

    @NonNull
    @Override
    public TelephoneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.telephone_recycler, parent, false);
        return new TelephoneAdapter.MyViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull TelephoneAdapter.MyViewHolder holder, int position) {

        holder.TextView1.setText(tvtitle.get(position));
        holder.Image1.setImageResource(imgcall.get(position));
        holder.TextView2.setText(tvnumber.get(position));
        holder.Image2.setImageResource(imgthim.get(position));

    }


    @Override
    public int getItemCount() {
        return tvtitle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Image1, Image2;
        TextView TextView1, TextView2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TextView1 = itemView.findViewById(R.id.tvtitle);
            Image1 = itemView.findViewById(R.id.imgcall);
            TextView2 = itemView.findViewById(R.id.tvnumber);
            Image2 = itemView.findViewById(R.id.imgthim);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}