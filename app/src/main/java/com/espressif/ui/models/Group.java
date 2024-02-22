package com.espressif.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.espressif.AppConstants;
import com.espressif.db.StringArrayListConverters;

import java.util.ArrayList;

@Entity(tableName = AppConstants.GROUP_TABLE)
public class Group implements Parcelable {

    @PrimaryKey
    @NonNull
    private String groupId;

    @ColumnInfo(name = "group_name")
    @NonNull
    private String groupName;

    @ColumnInfo(name = "node_list")
    @TypeConverters(StringArrayListConverters.class)
    private ArrayList<String> nodeList;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    protected Group(Parcel in) {
        groupId = in.readString();
        groupName = in.readString();
        nodeList = in.createStringArrayList();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<String> getNodeList() {
        return nodeList;
    }

    public void setNodeList(ArrayList<String> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(groupId);
        dest.writeString(groupName);
        dest.writeStringList(nodeList);
    }
}
