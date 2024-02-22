package com.espressif.ui.navigation.ControlBouquet;

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

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.MyViewHolder>{

    private static Context context;
    private List<String> tvName;
    private List<Integer> imagecall;
    private List<String> tvnumber;
    private List<Integer> imagemap;
    private List<Integer> imagelogo;

    public EmergencyAdapter(Context context, List<String> tvName, List<Integer> imagecall, List<String> tvnumber, List<Integer> imagemap, List<Integer> imagelogo) {

        this.context = context;
        this.tvName = tvName;
        this.imagecall = imagecall;
        this.tvnumber = tvnumber;
        this.imagemap = imagemap;
        this.imagelogo = imagelogo;
    }


    @NonNull
    @Override
    public EmergencyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.emergency_recycler, parent, false);
        return new EmergencyAdapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull EmergencyAdapter.MyViewHolder holder, int position) {
        holder.TextView1.setText(tvName.get(position));
        holder.Image1.setImageResource(imagecall.get(position));
        holder.TextView2.setText(tvnumber.get(position));
        holder.Image2.setImageResource(imagemap.get(position));
        holder.Image3.setImageResource(imagelogo.get(position));


    }

    @Override
    public int getItemCount() {
        return tvName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Image1, Image2, Image3;
        TextView TextView1, TextView2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TextView1 = itemView.findViewById(R.id.tvtitle);
            Image1 = itemView.findViewById(R.id.imgcall);
            TextView2 = itemView.findViewById(R.id.tvnumber);
            Image2 = itemView.findViewById(R.id.imgmap);
            Image3 = itemView.findViewById(R.id.imgthim);


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
