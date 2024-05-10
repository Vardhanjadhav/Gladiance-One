package com.espressif.ui.adapters;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.ui.activities.DeviceControls.AirContiningActivity;
import com.espressif.ui.activities.DeviceControls.BellActivity;
import com.espressif.ui.activities.DeviceControls.CurtainActivity;
import com.espressif.ui.activities.DeviceControls.DimmerActivity;
import com.espressif.ui.activities.EspMainActivity;
import com.espressif.ui.activities.DeviceControls.FanActivity;
import com.espressif.ui.models.Devices;
import com.espressif.wifi_provisioning.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //private List<param> arrayList;
    private static List<Devices> arrayList;

    EspMainActivity espMainActivity;
    private static final int VIEW_TYPE_ONE = 1;
    private static final int VIEW_TYPE_TWO = 2;
    private static final int VIEW_TYPE_THREE = 3;
    private static final int VIEW_TYPE_FOUR = 4;
    private static final int VIEW_TYPE_FIVE = 5;
    private static final int VIEW_TYPE_SIX = 6;
    private static final int VIEW_TYPE_SEVEN = 7;

    public CardAdapter(ArrayList<Devices> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getItemViewType(int position) {
        String title = arrayList.get(position).getType();
        Log.d(TAG, "getItemViewType: " + title);

        if (title.equals("esp.device.fan")) {
            return VIEW_TYPE_ONE;
        } else if (title.equals("esp.device.switch")) {
            return VIEW_TYPE_TWO;
        } else if (title.equals("esp.device.curtain")) {
            return VIEW_TYPE_THREE;
        } else if (title.equals("esp.device.light")) {
            return VIEW_TYPE_FOUR;
        } else if (title.equals("esp.device.lightbulb")) {
            return VIEW_TYPE_FIVE;
        } else if (title.equals("esp.device.bellcontrol")) {
            return VIEW_TYPE_SIX;
        } else if (title.equals("esp.device.thermostat")) {
            return VIEW_TYPE_SEVEN;
        } else {
            return VIEW_TYPE_FOUR;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == VIEW_TYPE_ONE) {
            view = layoutInflater.inflate(R.layout.card_fan, parent, false);
            return new TypeOneViewHolder(view);
        } else if (viewType == VIEW_TYPE_TWO) {
            view = layoutInflater.inflate(R.layout.card_toggle, parent, false);
            return new TypeTwoViewHolder(view);
        } else if (viewType == VIEW_TYPE_THREE) {
            view = layoutInflater.inflate(R.layout.card_curtain, parent, false);
            return new TypeThreeViewHolder(view);
        } else if (viewType == VIEW_TYPE_FOUR) {
            view = layoutInflater.inflate(R.layout.card_dimmer, parent, false);
            return new TypeFourViewHolder(view);
        } else if (viewType == VIEW_TYPE_FIVE) {
            view = layoutInflater.inflate(R.layout.card_rgblight, parent, false);
            return new TypeFiveViewHolder(view);
        } else if (viewType == VIEW_TYPE_SIX) {
            view = layoutInflater.inflate(R.layout.card_bell, parent, false);
            return new TypeSixViewHolder(view);
        } else if (viewType == VIEW_TYPE_SEVEN) {
            view = layoutInflater.inflate(R.layout.card_aircondition, parent, false);
            return new TypeSevenViewHolder(view);
        } else {
            view = layoutInflater.inflate(R.layout.card_curtain, parent, false);
            return new TypeThreeViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Devices devices = arrayList.get(position);

        if (holder.getItemViewType() == VIEW_TYPE_ONE) {
            TypeOneViewHolder typeOneViewHolder = (TypeOneViewHolder) holder;
            // Bind data for type one view
            typeOneViewHolder.title.setText(devices.getName());
        } else if (holder.getItemViewType() == VIEW_TYPE_TWO) {
            TypeTwoViewHolder typeTwoViewHolder = (TypeTwoViewHolder) holder;
            // Bind data for type two view
            typeTwoViewHolder.title.setText(devices.getName());
        } else if (holder.getItemViewType() == VIEW_TYPE_THREE) {
            TypeThreeViewHolder typeThreeViewHolder = (TypeThreeViewHolder) holder;
            // Bind data for type two view
            typeThreeViewHolder.title.setText(devices.getName());
        } else if (holder.getItemViewType() == VIEW_TYPE_FOUR) {
            TypeFourViewHolder typeFourViewHolder = (TypeFourViewHolder) holder;
            // Bind data for type two view
            typeFourViewHolder.title.setText(devices.getName());
        } else if (holder.getItemViewType() == VIEW_TYPE_FIVE) {
            TypeFiveViewHolder typeFiveViewHolder = (TypeFiveViewHolder) holder;
            // Bind data for type two view
            typeFiveViewHolder.title.setText(devices.getName());
        } else if (holder.getItemViewType() == VIEW_TYPE_SIX) {
            TypeSixViewHolder typeSixViewHolder = (TypeSixViewHolder) holder;
            // Bind data for type two view
            typeSixViewHolder.title.setText(devices.getName());
        } else if (holder.getItemViewType() == VIEW_TYPE_SEVEN) {
            TypeSevenViewHolder typeSevenViewHolder = (TypeSevenViewHolder) holder;
            // Bind data for type two view
            typeSevenViewHolder.title.setText(devices.getName());
        } else {
            TypeFourViewHolder typeFourViewHolder = (TypeFourViewHolder) holder;
            // Bind data for type two view
            typeFourViewHolder.title.setText(devices.getName());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class TypeOneViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        Switch switch_btn;

        TypeOneViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click for the second card view
                    // Change the activity here
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Devices clickedCard = arrayList.get(position);
                        String name = clickedCard.getName();

                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name", name);
                        editor.apply();


                        Context context = v.getContext();
                        Intent intent = new Intent(context, FanActivity.class);
                        context.startActivity(intent);
                    }
                }
            });

        }


    }

    static class TypeTwoViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CardView cd;
        LinearLayout linearLayout;
        EspMainActivity espMainActivity = EspMainActivity.getInstance();
        // MainActivity mainActivity = MainActivity.getInstance();
        final String[] a = {"1"};
        //boolean a = true;

        TypeTwoViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextView);
            cd = itemView.findViewById(R.id.node_cardView);
            linearLayout = itemView.findViewById(R.id.llLight);


            itemView.setOnClickListener(new View.OnClickListener() {
                boolean powerState = false;

                @Override
                public void onClick(View v) {

                    powerState = !powerState;
                    if(powerState == true){
                        String hexColor = "#FF6434";
                        int color = Color.parseColor(hexColor);
                        title.setTextColor(color);
                        linearLayout.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_highlight));
                    }
                    else
                    {
                        title.setTextAppearance(R.style.TEXT);
                        //cd.setCardBackgroundColor(Color.TRANSPARENT);
                        linearLayout.setBackgroundColor(Color.parseColor("#A6A6A6"));
                    }
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Devices clickedCard = arrayList.get(position);
                        String name = clickedCard.getName();
                        String power = clickedCard.getPrimary();

                        espMainActivity.sendSwitchState(powerState, name, power);
                    }
                }
            });
        }

    }

    static class TypeThreeViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        Switch switch_btn;

        TypeThreeViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click for the second card view
                    // Change the activity here
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Devices clickedCard = arrayList.get(position);
                        String name = clickedCard.getName();

                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name", name);
                        editor.apply();

                        Context context = v.getContext();
                        Intent intent = new Intent(context, CurtainActivity.class);
                        context.startActivity(intent);
                    }
                }
            });

        }
    }

    static class TypeFourViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        Switch switch_btn;

        TypeFourViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Devices clickedCard = arrayList.get(position);
                        String name = clickedCard.getName();

                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name", name);
                        editor.apply();

                        Context context = v.getContext();
                        Intent intent = new Intent(context, DimmerActivity.class);
                        context.startActivity(intent);
                    }
                }
            });

        }
    }

    static class TypeFiveViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        Switch switch_btn;

        TypeFiveViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click for the second card view
                    // Change the activity here
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Devices clickedCard = arrayList.get(position);
                        String name = clickedCard.getName();

                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name", name);
                        editor.apply();

                        Context context = v.getContext();
                        Intent intent = new Intent(context, AirContiningActivity.RGBLightActivity.class);
                        context.startActivity(intent);
                    }
                }
            });

        }
    }

    //Bell
    static class TypeSixViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        EspMainActivity espMainActivity = EspMainActivity.getInstance();
        final String[] a = {"1"};
        //boolean a = true;

        TypeSixViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextView);
            //  switch_btn = itemView.findViewById(R.id.switch_btn);
            itemView.setOnClickListener(new View.OnClickListener() {
                boolean powerState = true;

                @Override
                public void onClick(View v) {


                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Devices clickedCard = arrayList.get(position);
                        String name = clickedCard.getName();
                        String primary = clickedCard.getPrimary();

                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name", name);
                        editor.apply();

                        SharedPreferences sharedPreferences1 = v.getContext().getSharedPreferences("MyPrefsPrimary", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putString("Primary", primary);
                        editor1.apply();

                        Context context = v.getContext();
                        Intent intent = new Intent(context, BellActivity.class);
                        context.startActivity(intent);


                    }
                }
            });
        }
    }

    static class TypeSevenViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        Switch switch_btn;

        TypeSevenViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click for the second card view
                    // Change the activity here
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Devices clickedCard = arrayList.get(position);
                        String name = clickedCard.getName();

                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("MyPrefsName", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name", name);
                        editor.apply();

                        Context context = v.getContext();
                        Intent intent = new Intent(context, AirContiningActivity.class);
                        context.startActivity(intent);
                    }
                }
            });

        }
    }
}
