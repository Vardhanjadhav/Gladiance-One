package com.espressif.ui.navigation.Home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class HomeFavoriteAdapter extends RecyclerView.Adapter<HomeFavoriteAdapter.MyViewHolder> {
    private static Context context;
    private List<String> titles;
    private List<Integer> images;

    public HomeFavoriteAdapter(Context context, List<String> titles, List<Integer> images) {

        this.context = context;
        this.titles = titles;
        this.images = images;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextView.setText(titles.get(position));
        holder.mImageView.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout bg1;
        ImageView mImageView;
        TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bg1 = itemView.findViewById(R.id.bg1);
            mImageView = itemView.findViewById(R.id.iv_grid_view);
            mTextView = (TextView) itemView.findViewById(R.id.textview1);

            final String[] a = {"1"};

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Intent intent = new Intent(context, Seekbar.class);
                    // context.startActivities(new Intent[]{intent});
                    // v.setBackgroundResource(R.drawable.card_border);
                    if (a[0] == "1") {
                        //  bg1.setBackgroundResource(R.drawable.card_border);
                        //   mImageView.setImageResource(R.drawable.sun__glow);
                        mTextView.setTextColor(Color.parseColor("#ffae18"));
                        a[0] = "2";
                        //   mTextView.setTextColor(R.color.link_color);
                        //     bg1.setBackgroundResource(R.drawable.bg_main);
                        //   mTextView.getResources().getColor(R.color.link_color);

                        //

                        //    holder.itemView.setBackgroundResource(selectedPosition == position ? R.drawable.border_highlight : 0);
                        //    holder.textViewItem.setTextColor(selectedPosition == position ? Color.parseColor("#FFA500") : Color.parseColor("#FFFFFF"));
                    } else {

                        //bg1.setBackgroundResource(R.drawable.background_main_light);
                        mImageView.setImageResource(R.drawable.sun__white);
                        mTextView.setTextColor(Color.parseColor("#FFFFFF"));
                        //     mTextView.getResources().getColor(R.color.white);

                        //      mTextView.setTextColor(R.color.white);
                        a[0] = "1";
                        //   mTextView.setTextColor(R.color.white);
                    }
                }
            });
        }
    }
}
