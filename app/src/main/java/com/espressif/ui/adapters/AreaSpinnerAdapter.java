package com.espressif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.espressif.ui.models.arealandingmodel.Area;

import java.util.List;

public class AreaSpinnerAdapter extends ArrayAdapter<Area> {
    private Context mContext;
    private List<Area> mAreas;
    private Spinner mSpinner;

    private OnAreaSelectedListener mListener;

    public AreaSpinnerAdapter(Context context,Spinner spinner, List<Area> areas) {
        super(context, android.R.layout.simple_spinner_item, areas);
        mContext = context;
        mAreas = areas;
        mSpinner = spinner;
    }

    public void setOnAreaSelectedListener(OnAreaSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_item, parent, false);
        }


        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(mAreas.get(position).getGAAProjectSpaceTypeAreaName());

        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // Implement getDropDownView as before
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(mAreas.get(position).getGAAProjectSpaceTypeAreaName());



        // Set click listener
        convertView.setOnClickListener(v -> {
            // Retrieve the selected area reference
            String selectedAreaRef = String.valueOf(mAreas.get(position).getGAAProjectSpaceTypeAreaRef());
            // Pass the value to the click listener interface method
            if (mListener != null) {
                mListener.onAreaSelected(selectedAreaRef);
                mSpinner.setSelection(position);
            }
        });
        return convertView;
    }

    // Define a click listener interface
    public interface OnAreaSelectedListener {
        void onAreaSelected(String selectedAreaRef);

    }


}


