package com.espressif.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TsData implements Parcelable {

    private long timeStamp;
    private Object value;

    public TsData(long timeStamp, Object value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }

    protected TsData(Parcel in) {
        timeStamp = in.readLong();
        value = in.readValue(Object.class.getClassLoader());
    }

    public static final Creator<TsData> CREATOR = new Creator<TsData>() {
        @Override
        public TsData createFromParcel(Parcel in) {
            return new TsData(in);
        }

        @Override
        public TsData[] newArray(int size) {
            return new TsData[size];
        }
    };

    public long getTimeStamp() {
        return timeStamp;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timeStamp);
        dest.writeValue(value);
    }
}
