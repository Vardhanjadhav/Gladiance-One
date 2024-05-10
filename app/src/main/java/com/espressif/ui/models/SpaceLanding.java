package com.espressif.ui.models;

public class SpaceLanding {

    private String GAAProjectSpaceRef;
    private String GAAProjectSpaceName;
    private String GAAProjectSpaceTypeRef;
    private String GAAProjectSpaceTypeName;
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

    public String getGAAProjectSpaceTypeRef() {
        return GAAProjectSpaceTypeRef;
    }

    public void setGAAProjectSpaceTypeRef(String GAAProjectSpaceTypeRef) {
        this.GAAProjectSpaceTypeRef = GAAProjectSpaceTypeRef;
    }

    public String getGAAProjectSpaceTypeName() {
        return GAAProjectSpaceTypeName;
    }

    public void setGAAProjectSpaceTypeName(String GAAProjectSpaceTypeName) {
        this.GAAProjectSpaceTypeName = GAAProjectSpaceTypeName;
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

    public SpaceLanding(String GAAProjectSpaceRef, String GAAProjectSpaceName, String GAAProjectSpaceTypeRef, String GAAProjectSpaceTypeName, int displayOrder, String description) {
        this.GAAProjectSpaceRef = GAAProjectSpaceRef;
        this.GAAProjectSpaceName = GAAProjectSpaceName;
        this.GAAProjectSpaceTypeRef = GAAProjectSpaceTypeRef;
        this.GAAProjectSpaceTypeName = GAAProjectSpaceTypeName;
        DisplayOrder = displayOrder;
        Description = description;
    }
}
