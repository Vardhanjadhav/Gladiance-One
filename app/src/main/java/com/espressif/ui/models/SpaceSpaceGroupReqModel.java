package com.espressif.ui.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpaceSpaceGroupReqModel {

    @SerializedName("Version")
    private int version;

    @SerializedName("GAAProjectSpaceGroupRef")
    private String projectSpaceGroupRef;

    @SerializedName("GAAProjectSpaceGroupName")
    private String projectSpaceGroupName;

    @SerializedName("Spaces")
    private List<Space> spaces;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getProjectSpaceGroupRef() {
        return projectSpaceGroupRef;
    }

    public void setProjectSpaceGroupRef(String projectSpaceGroupRef) {
        this.projectSpaceGroupRef = projectSpaceGroupRef;
    }

    public String getProjectSpaceGroupName() {
        return projectSpaceGroupName;
    }

    public void setProjectSpaceGroupName(String projectSpaceGroupName) {
        this.projectSpaceGroupName = projectSpaceGroupName;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }

    public SpaceSpaceGroupReqModel(int version, String projectSpaceGroupRef, String projectSpaceGroupName, List<Space> spaces) {
        this.version = version;
        this.projectSpaceGroupRef = projectSpaceGroupRef;
        this.projectSpaceGroupName = projectSpaceGroupName;
        this.spaces = spaces;
    }

    public class Space {
        @SerializedName("GAAProjectSpaceRef")
        private String spaceRef;

        @SerializedName("GAAProjectSpaceName")
        private String spaceName;

        @SerializedName("DisplayOrder")
        private int displayOrder;

        @SerializedName("Description")
        private String description;

        public String getSpaceRef() {
            return spaceRef;
        }

        public void setSpaceRef(String spaceRef) {
            this.spaceRef = spaceRef;
        }

        public String getSpaceName() {
            return spaceName;
        }

        public void setSpaceName(String spaceName) {
            this.spaceName = spaceName;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Space(String spaceRef, String spaceName, int displayOrder, String description) {
            this.spaceRef = spaceRef;
            this.spaceName = spaceName;
            this.displayOrder = displayOrder;
            this.description = description;
        }
    }
}