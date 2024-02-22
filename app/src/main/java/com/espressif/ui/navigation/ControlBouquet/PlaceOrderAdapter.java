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

public class PlaceOrderAdapter extends  RecyclerView.Adapter<PlaceOrderAdapter.MyViewHolder> {

    private static Context context;
    private List<Integer> imageVegNonveg;
    private List<String> tvMemuName;
    private List<String> tvMemuPrice;
    private List<String> tvtype;
    private List<Integer> imageMenu;

    public PlaceOrderAdapter(Context context, List<Integer> imageVegNonveg, List<String> tvMemuName, List<String> tvMemuPrice, List<String> tvtype, List<Integer> imageMenu) {
        this.context = context;
        this.imageVegNonveg = imageVegNonveg;
        this.tvMemuName = tvMemuName;
        this.tvMemuPrice = tvMemuPrice;
        this.tvtype = tvtype;
        this.imageMenu = imageMenu;
    }

    @NonNull
    @Override
    public PlaceOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.place_order_rv, parent, false);
        return new PlaceOrderAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceOrderAdapter.MyViewHolder holder, int position) {

        holder.Image1.setImageResource(imageVegNonveg.get(position));
        holder.TextView1.setText(tvMemuName.get(position));
        holder.TextView2.setText(tvMemuPrice.get(position));
        holder.TextView3.setText(tvtype.get(position));
        holder.Image2.setImageResource(imageMenu.get(position));


    }

    @Override
    public int getItemCount() {
        return tvMemuName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Image1, Image2;
        TextView TextView1, TextView2, TextView3;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Image1 = itemView.findViewById(R.id.imgVegNonveg);
            TextView1 = itemView.findViewById(R.id.tvtitle);
            TextView2 = itemView.findViewById(R.id.tvprice);
            TextView3 = itemView.findViewById(R.id.tvtype);
            Image2 = itemView.findViewById(R.id.imgthim);


        }
    }
}
