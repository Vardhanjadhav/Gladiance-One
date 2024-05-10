package com.espressif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.fragment.ControlBouquet.AddFoodItemFragment;
import com.espressif.wifi_provisioning.R;

import java.util.List;

public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.MyViewHolder> {

    private static Context context;
    private List<Integer> imageVegNonveg;
    private List<String> tvMemuName;
    private List<String> tvMemuPrice;
    private List<Integer> imageMenu;
    private List<String> btnAdd;


    public FoodMenuAdapter(Context context, List<Integer> imageVegNonveg, List<String> tvMemuName, List<String> tvMemuPrice, List<Integer> imageMenu, List<String> btnAdd) {

        this.context = context;
        this.imageVegNonveg = imageVegNonveg;
        this.tvMemuName = tvMemuName;
        this.tvMemuPrice = tvMemuPrice;
        this.imageMenu = imageMenu;
        this.btnAdd = btnAdd;
    }


    @NonNull
    @Override
    public FoodMenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(context).inflate(R.layout.food_menu_rc, parent, false);
        return new FoodMenuAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMenuAdapter.MyViewHolder holder, int position) {
        holder.Image1.setImageResource(imageVegNonveg.get(position));
        holder.TextView1.setText(tvMemuName.get(position));
        holder.TextView2.setText(tvMemuPrice.get(position));
        holder.Image2.setImageResource(imageMenu.get(position));
        holder.button.setText(btnAdd.get(position));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the bottom sheet
                AddFoodItemFragment addFoodItemFragment = new AddFoodItemFragment();
                addFoodItemFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), addFoodItemFragment.getTag());
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvMemuName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView Image1, Image2;
        TextView TextView1, TextView2;
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Image1 = itemView.findViewById(R.id.imgVegNonveg);
            TextView1 = itemView.findViewById(R.id.tvmenu_name);
            TextView2 = itemView.findViewById(R.id.tvmenu_price);
            Image2 = itemView.findViewById(R.id.imgmenu);
            button = itemView.findViewById(R.id.btnAddItem);

        }
    }
}
