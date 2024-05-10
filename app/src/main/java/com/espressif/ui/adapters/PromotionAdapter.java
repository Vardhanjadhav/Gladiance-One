package com.espressif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.wifi_provisioning.R;

import java.util.List;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.MyViewHolder>{

    private static Context context;
    private List<Integer> imgProm;
    private List<String> txtProm;


    public PromotionAdapter(Context context, List<Integer> imgProm,List<String> txtProm){

        this.context=context;
        this.imgProm=imgProm;
        this.txtProm=txtProm;

    }

    @NonNull
    @Override
    public PromotionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.promotion_recycler,parent,false);
        return new PromotionAdapter.MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull PromotionAdapter.MyViewHolder holder, int position) {
        holder.mImageView.setImageResource(imgProm.get(position));
        holder.mTextView.setText(txtProm.get(position));

    }

    @Override
    public int getItemCount() {
        return txtProm.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTextView;
        Button buttonSeeMore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imgpromotion);
            mTextView = itemView.findViewById(R.id.tvpromoinfo);
            buttonSeeMore = itemView.findViewById(R.id.buttonSeeMore);

            buttonSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleSeeMore(mTextView, buttonSeeMore);
                }
            });

        }
    }

    private static void toggleSeeMore(TextView textView, Button button) {
        if (textView.getMaxLines() == 3) {
            // Expand the text
            textView.setMaxLines(Integer.MAX_VALUE);
            button.setText("See Less");
        } else {
            // Collapse the text
            textView.setMaxLines(3);
            button.setText("See More...");
        }
    }

}
