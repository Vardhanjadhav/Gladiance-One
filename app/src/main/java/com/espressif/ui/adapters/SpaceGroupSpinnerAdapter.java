package com.espressif.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.espressif.ui.models.ProjectSpaceGroupReqModel;



import java.util.List;

public class SpaceGroupSpinnerAdapter extends ArrayAdapter<ProjectSpaceGroupReqModel.SpaceGroup> {
    private Context mContext;
    private List<ProjectSpaceGroupReqModel.SpaceGroup> mSpaceGroup;
    private Spinner mSpinner;

    private SpaceGroupSpinnerAdapter.OnAreaSelectedListener mListener;

    public SpaceGroupSpinnerAdapter(Context context,Spinner spinner, List<ProjectSpaceGroupReqModel.SpaceGroup> spaceGroups) {
        super(context, android.R.layout.simple_spinner_item, spaceGroups);
        mContext = context;
        mSpaceGroup = spaceGroups;
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
        textView.setText(mSpaceGroup.get(position).getGAAProjectSpaceGroupName());

        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // Implement getDropDownView as before
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(mSpaceGroup.get(position).getGAAProjectSpaceGroupName());

        // Set click listener
        convertView.setOnClickListener(v -> {
                // Retrieve the selected area reference
                String selectedAreaRef = String.valueOf(mSpaceGroup.get(position).getGAAProjectSpaceGroupRef());
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
