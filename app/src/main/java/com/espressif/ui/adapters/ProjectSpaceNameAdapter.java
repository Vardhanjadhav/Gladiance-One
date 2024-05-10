package com.espressif.ui.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.models.SpaceLanding;
import com.espressif.ui.fragment.RoomControl.AreaLandingFragment;
import com.espressif.wifi_provisioning.R;

import java.util.List;

public class ProjectSpaceNameAdapter extends RecyclerView.Adapter<ProjectSpaceNameAdapter.ViewHolder> {



    private static List<SpaceLanding> arraylist;
    private int selectedPosition = RecyclerView.NO_POSITION;


    public ProjectSpaceNameAdapter(List<SpaceLanding> arraylist) {
        this.arraylist = arraylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_control_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpaceLanding space = arraylist.get(position);
        holder.spaceNameTextView.setText(space.getGAAProjectSpaceName());
        // You can also bind other data related to the space group here if needed


    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView spaceNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spaceNameTextView = itemView.findViewById(R.id.btnTitle);

            spaceNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        SpaceLanding clickedCard = arraylist.get(position);
                        String name = clickedCard.getGAAProjectSpaceRef();
                        String typeRef = clickedCard.getGAAProjectSpaceTypeRef();

                        // Storing data in SharedPreferences
                        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("MyPrefsPSR", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Project_Space_Ref", name);
                        editor.apply();

                        SharedPreferences sharedPreferences1 = view.getContext().getSharedPreferences("MyPrefsPSTR", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putString("Project_Space_Type_Ref", typeRef);
                        editor1.apply();


                        //spaceNameTextView.setBackground(getDrawableForTheme(view.getContext(), R.drawable.new_border_button_background));

                        // Change text color of clicked text view based on theme
                        int textColor = view.getContext().getResources().getColor(R.color.white);
                        if (isNightModeEnabled(view.getContext())) {
                            textColor = view.getContext().getResources().getColor(R.color.color_black);
                        }
                        spaceNameTextView.setTextColor(textColor);



                        Fragment newFragment = new AreaLandingFragment(); // Replace YourNewFragment with the fragment you want to navigate to
                        FragmentTransaction fragmentTransaction = ((AppCompatActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.framelayout, newFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();




                    }
                }
            });
        }
    }

    // Function to check if night mode is enabled
    private boolean isNightModeEnabled(Context context) {
        int currentNightMode = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }

//    // Function to get drawable for current theme
//    private Drawable getDrawableForTheme(Context context, @DrawableRes int drawableResId) {
//        if (isNightModeEnabled(context)) {
//            // Load night mode drawable
//            return ContextCompat.getDrawable(context, R.drawable.new_border_button_background_night);
//        } else {
//            // Load day mode drawable
//            return ContextCompat.getDrawable(context, drawableResId);
//        }
//    }
}