package com.espressif.ui.models;

public class Space {
    private String GAAProjectSpaceRef;
    private String GAAProjectSpaceName;
    private int DisplayOrder;
    private String Description;

    public String getGAAProjectSpaceRef() {
        return GAAProjectSpaceRef;
    }

    public void setGAAProjectSpaceRef(String GAAProjectSpaceRef) {
        this.GAAProjectSpaceRef = GAAProjectSpaceRef;
    }

    public String getGAAProjectSpaceName() {
        return GAAProjectSpaceName;
    }

    public void setGAAProjectSpaceName(String GAAProjectSpaceName) {
        this.GAAProjectSpaceName = GAAProjectSpaceName;
    }

    public int getDisplayOrder() {
        return DisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        DisplayOrder = displayOrder;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public Space(String GAAProjectSpaceRef, String GAAProjectSpaceName, int displayOrder, String description) {
        this.GAAProjectSpaceRef = GAAProjectSpaceRef;
        this.GAAProjectSpaceName = GAAProjectSpaceName;
        DisplayOrder = displayOrder;
        Description = description;
    }
}

