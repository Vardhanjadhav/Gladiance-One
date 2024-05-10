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

public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.MyViewHolder> {
    private static Context context;
    private List<String> tvtitle1;
    private List<String> tvtime;
    private List<Integer> imgcall1;
    private List<String> tvNumber;
    private List<String> tvseemore;
    private List<Integer> imgthim1;

    public AmenitiesAdapter(Context context, List<String> tvtitle1, List<String> tvtime, List<Integer> imgcall1, List<String> tvNumber, List<String> tvseemore, List<Integer> imgthim1) {

        this.context = context;
        this.tvtitle1 = tvtitle1;
        this.tvtime = tvtime;
        this.imgcall1 = imgcall1;
        this.tvNumber = tvNumber;
        this.tvseemore = tvseemore;
        this.imgthim1 = imgthim1;
    }

    @NonNull
    @Override
    public AmenitiesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.amenities_recycler, parent, false);
        return new AmenitiesAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AmenitiesAdapter.MyViewHolder holder, int position) {
        holder.TextView1.setText(tvtitle1.get(position));
        holder.TextView2.setText(tvtime.get(position));
        holder.Image1.setImageResource(imgcall1.get(position));
        holder.TextView3.setText(tvNumber.get(position));
        holder.TextView4.setText(tvseemore.get(position));
        holder.Image2.setImageResource(imgthim1.get(position));

    }

    @Override
    public int getItemCount() {
        return tvtitle1.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Image1, Image2;
        TextView TextView1, TextView2, TextView3, TextView4;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            TextView1 = itemView.findViewById(R.id.tvtitle);
            TextView2 = itemView.findViewById(R.id.tvtime);
            Image1 = itemView.findViewById(R.id.imgcall);
            TextView3 = itemView.findViewById(R.id.tvNumber);
            TextView4 = itemView.findViewById(R.id.tvseemore);
            Image2 = itemView.findViewById(R.id.imgthim);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent intent = new Intent(context, Seekbar.class);
                    // context.startActivities(new Intent[]{intent});
                    //  v.setBackgroundResource(R.drawable.recycleview_border);

                }
            });
        }
    }
}
