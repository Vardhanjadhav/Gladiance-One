package com.espressif.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Param implements Parcelable, Comparable {

    private String name;
    private String dataType;
    private String uiType;
    private String paramType;
    private boolean isDynamicParam;
    private ArrayList<String> properties;
    private int minBounds;
    private int maxBounds;
    private float stepCount;
    private ArrayList<String> validStrings;
    private String labelValue;
    private double value;
    private boolean switchStatus;
    private boolean isSelected;




    public Param(Param param) {

        name = param.getName();
        paramType = param.getParamType();
        dataType = param.getDataType();
        uiType = param.getUiType();
        validStrings = param.getValidStrings();
        labelValue = param.getLabelValue();
        value = param.getValue();



    }

    public Param() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    protected Param(Parcel in) {
        name = in.readString();
        paramType = in.readString();
        dataType = in.readString();
        uiType = in.readString();
        validStrings = in.createStringArrayList();

    }



    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUiType() {
        return uiType;
    }

    public void setUiType(String uiType) {
        this.uiType = uiType;
    }

    public ArrayList<String> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }


    public boolean isDynamicParam() {
        return isDynamicParam;
    }

    public void setDynamicParam(boolean dynamicParam) {
        isDynamicParam = dynamicParam;
    }

    public int getMinBounds() {
        return minBounds;
    }

    public void setMinBounds(int minBounds) {
        this.minBounds = minBounds;
    }

    public int getMaxBounds() {
        return maxBounds;
    }

    public void setMaxBounds(int maxBounds) {
        this.maxBounds = maxBounds;
    }

    public float getStepCount() {
        return stepCount;
    }

    public void setStepCount(float stepCount) {
        this.stepCount = stepCount;
    }

    public ArrayList<String> getValidStrings() {
        return validStrings;
    }

    public void setValidStrings(ArrayList<String> validStrings) {
        this.validStrings = validStrings;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(boolean switchStatus) {
        this.switchStatus = switchStatus;
    }


    public static final Creator<Param> CREATOR = new Creator<Param>() {
        @Override
        public Param createFromParcel(Parcel in) {
            return new Param(in);
        }

        @Override
        public Param[] newArray(int size) {
            return new Param[size];
        }


    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
