package com.espressif.ui.models;

public class Space {
    private String GAAProjectSpaceRef;
    private String SpaceName;
    private int DisplayOrder;
    private String Description;

    public String getGAAProjectSpaceRef() {
        return GAAProjectSpaceRef;
    }

    public void setGAAProjectSpaceRef(String GAAProjectSpaceRef) {
        this.GAAProjectSpaceRef = GAAProjectSpaceRef;
    }

    public String getSpaceName() {
        return SpaceName;
    }

    public void setSpaceName(String spaceName) {
        SpaceName = spaceName;
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

    public Space(String GAAProjectSpaceRef, String spaceName, int displayOrder, String description) {
        this.GAAProjectSpaceRef = GAAProjectSpaceRef;
        SpaceName = spaceName;
        DisplayOrder = displayOrder;
        Description = description;
    }

}

