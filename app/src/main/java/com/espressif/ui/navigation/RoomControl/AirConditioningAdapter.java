package com.espressif.ui.navigation.RoomControl;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class AirConditioningAdapter extends RecyclerView.Adapter<AirConditioningAdapter.MyViewHolder> {

    private static Context context;
    private List<String> titles;
    private List<Integer> imagesAirCon;

    public AirConditioningAdapter(Context context, List<String> titles, List<Integer> imagesAirCon) {

        this.context = context;
        this.titles = titles;
        this.imagesAirCon = imagesAirCon;
    }

    @NonNull
    @Override
    public AirConditioningAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.airconditioning_item, parent, false);
        return new AirConditioningAdapter.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AirConditioningAdapter.MyViewHolder holder, int position) {
        holder.ImageViewAirCon.setImageResource(imagesAirCon.get(position));
        holder.textView.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout bg1;
        ImageView ImageViewAirCon;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bg1 = itemView.findViewById(R.id.bg1);
            ImageViewAirCon = itemView.findViewById(R.id.ivLight);
            textView = itemView.findViewById(R.id.tvTitle);

            final String[] a = {"1"};

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (a[0] == "1") {
                        bg1.setBackgroundResource(R.drawable.transparent_button_background);
                        ImageViewAirCon.setImageResource(R.drawable.wether);
                        textView.setTextColor(Color.parseColor("#ffae18"));
                        a[0] = "2";

                    } else {

                        bg1.setBackgroundResource(R.drawable.main_light_background);
                        ImageViewAirCon.setImageResource(R.drawable.sun__white);
                        textView.setTextColor(Color.parseColor("#FFFFFF"));

                        a[0] = "1";

                    }

                }
            });

            ImageViewAirCon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click for ImageViewMore to open a new Fragment
                    Fragment newFragment = new AirConditioningControlFragment();
                    FragmentTransaction transaction = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.airConditioning_fragment_container, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
    }
}