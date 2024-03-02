package com.espressif.ui.models;

public class SpaceGroup {
    private String GAAProjectSpaceGroupRef;
    private String GAAProjectSpaceGroupName;
    private int DisplayOrder;

    public String getGAAProjectSpaceGroupRef() {
        return GAAProjectSpaceGroupRef;
    }

    public void setGAAProjectSpaceGroupRef(String GAAProjectSpaceGroupRef) {
        this.GAAProjectSpaceGroupRef = GAAProjectSpaceGroupRef;
    }

    public String getGAAProjectSpaceGroupName() {
        return GAAProjectSpaceGroupName;
    }

    public void setGAAProjectSpaceGroupName(String GAAProjectSpaceGroupName) {
        this.GAAProjectSpaceGroupName = GAAProjectSpaceGroupName;
    }

    public int getDisplayOrder() {
        return DisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        DisplayOrder = displayOrder;
    }

    public SpaceGroup(String GAAProjectSpaceGroupRef, String GAAProjectSpaceGroupName, int displayOrder) {
        this.GAAProjectSpaceGroupRef = GAAProjectSpaceGroupRef;
        this.GAAProjectSpaceGroupName = GAAProjectSpaceGroupName;
        DisplayOrder = displayOrder;
    }

}
