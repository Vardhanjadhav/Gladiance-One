package com.espressif.ui.fragment.ControlBouquet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.espressif.wifi_provisioning.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class AddFoodItemFragment extends BottomSheetDialogFragment {


    public AddFoodItemFragment() {
        // Required empty public constructor
    }
    TextView scoreText;
    int score = 0;
    ImageView incrementButton, decrementButton;
    Button addItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_food_item, container, false);

        scoreText = view.findViewById(R.id.scoreText);
        scoreText.setText(String.valueOf(score));
        incrementButton = view.findViewById(R.id.incrementButton);
        decrementButton = view.findViewById(R.id.decrementButton);
        addItem = view.findViewById(R.id.btn_add_item);

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                score ++;
                scoreText.setText(String.valueOf(score));
            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(score > 0) {
                    score --;
                    scoreText.setText(String.valueOf(score));
                }

            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(score > 0) {
                    score --;
                    scoreText.setText(String.valueOf(score));
                }

            }
        });

        return view;
    }
}