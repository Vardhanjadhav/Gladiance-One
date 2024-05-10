package com.espressif.ui.adapters;

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

import com.espressif.ui.fragment.RoomControl.CurtainsControlFragment;
import com.espressif.wifi_provisioning.R;

import java.util.List;

public class CurtainsAdapter extends RecyclerView.Adapter<CurtainsAdapter.MyViewHolder> {

    private static Context context;
    private List<String> titles;
    private List<Integer> imagesCurtains;

    public CurtainsAdapter(Context context, List<String> titles, List<Integer> imagesCurtains) {

        this.context = context;
        this.titles = titles;
        this.imagesCurtains = imagesCurtains;
    }

    @NonNull
    @Override
    public CurtainsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.curtains_item, parent, false);
        return new CurtainsAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CurtainsAdapter.MyViewHolder holder, int position) {
        holder.ImageViewCurtains.setImageResource(imagesCurtains.get(position));
        holder.textView.setText(titles.get(position));
    }


    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout bg1;
        ImageView ImageViewCurtains;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bg1 = itemView.findViewById(R.id.bg1);
            ImageViewCurtains = itemView.findViewById(R.id.ivLight);
            textView = itemView.findViewById(R.id.tvTitle);

            final String[] a = {"1"};

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (a[0] == "1") {
                        bg1.setBackgroundResource(R.drawable.transparent_button_background);
                        ImageViewCurtains.setImageResource(R.drawable.wether);
                        textView.setTextColor(Color.parseColor("#ffae18"));
                        a[0] = "2";

                    } else {

                        bg1.setBackgroundResource(R.drawable.main_light_background);
                        ImageViewCurtains.setImageResource(R.drawable.sun__white);
                        textView.setTextColor(Color.parseColor("#FFFFFF"));

                        a[0] = "1";

                    }

                }
            });

            ImageViewCurtains.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click for ImageViewMore to open a new Fragment
                    Fragment newFragment = new CurtainsControlFragment();
                    FragmentTransaction transaction = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.curtains_fragment_container, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

        }
    }
}