package com.espressif.ui.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectSpaceLandingReqModel {

    private String Version;
    private String GAAProjectSpaceGroupRef;
    private String GAAProjectSpaceGroupName;
    private List<Space> Spaces;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

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

    public List<Space> getSpaces() {
        return Spaces;
    }

    public void setSpaces(List<Space> spaces) {
        Spaces = spaces;
    }

    public ProjectSpaceLandingReqModel(String version, String GAAProjectSpaceGroupRef, String GAAProjectSpaceGroupName, List<Space> spaces) {
        Version = version;
        this.GAAProjectSpaceGroupRef = GAAProjectSpaceGroupRef;
        this.GAAProjectSpaceGroupName = GAAProjectSpaceGroupName;
        Spaces = spaces;
    }

    public static class Space{

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

        public Space(String GAAProjectSpaceRef, String GAAProjectSpaceName, String GAAProjectSpaceTypeRef, String GAAProjectSpaceTypeName, int displayOrder, String description) {
            this.GAAProjectSpaceRef = GAAProjectSpaceRef;
            this.GAAProjectSpaceName = GAAProjectSpaceName;
            this.GAAProjectSpaceTypeRef = GAAProjectSpaceTypeRef;
            this.GAAProjectSpaceTypeName = GAAProjectSpaceTypeName;
            DisplayOrder = displayOrder;
            Description = description;
        }
    }

}
