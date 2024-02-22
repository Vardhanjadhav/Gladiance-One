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

public class KeyContactAdapter extends RecyclerView.Adapter<KeyContactAdapter.MyViewHolder> {
    private static Context context;
    private List<String> tvtitle2;
    private List<Integer> imgcall2;
    private List<String> tvnumber2;
    private List<Integer> imgmap;
    private List<Integer> imgthim2;

    public KeyContactAdapter(Context context, List<String> tvtitle2, List<Integer> imgcall2, List<String> tvnumber2, List<Integer> imgmap, List<Integer> imgthim2) {

        this.context = context;
        this.tvtitle2 = tvtitle2;
        this.imgcall2 = imgcall2;
        this.tvnumber2 = tvnumber2;
        this.imgmap = imgmap;
        this.imgthim2 = imgthim2;
    }


    @NonNull
    @Override
    public KeyContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.key_contact_recycler, parent, false);
        return new KeyContactAdapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull KeyContactAdapter.MyViewHolder holder, int position) {
        holder.TextView1.setText(tvtitle2.get(position));
        holder.Image1.setImageResource(imgcall2.get(position));
        holder.TextView2.setText(tvnumber2.get(position));
        holder.Image2.setImageResource(imgmap.get(position));
        holder.Image3.setImageResource(imgthim2.get(position));

    }

    @Override
    public int getItemCount() {
        return tvnumber2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Image1, Image2, Image3;
        TextView TextView1, TextView2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView1 = itemView.findViewById(R.id.tvtitle2);
            Image1 = itemView.findViewById(R.id.imgcall2);
            TextView2 = itemView.findViewById(R.id.tvnumber2);
            Image2 = itemView.findViewById(R.id.imgmap);
            Image3 = itemView.findViewById(R.id.imgthim2);

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
