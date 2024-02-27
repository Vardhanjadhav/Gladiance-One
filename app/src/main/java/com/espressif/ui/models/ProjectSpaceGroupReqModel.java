package com.espressif.ui.models;

import java.util.List;

public class ProjectSpaceGroupReqModel {

    private String Version;
    private String GAAProjectRef;
    private String GAAProjectName;
    private List<SpaceGroup> GAAProjectSpaceGroups;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getGAAProjectRef() {
        return GAAProjectRef;
    }

    public void setGAAProjectRef(String GAAProjectRef) {
        this.GAAProjectRef = GAAProjectRef;
    }

    public String getGAAProjectName() {
        return GAAProjectName;
    }

    public void setGAAProjectName(String GAAProjectName) {
        this.GAAProjectName = GAAProjectName;
    }

    public List<SpaceGroup> getGAAProjectSpaceGroups() {
        return GAAProjectSpaceGroups;
    }

    public void setGAAProjectSpaceGroups(List<SpaceGroup> GAAProjectSpaceGroups) {
        this.GAAProjectSpaceGroups = GAAProjectSpaceGroups;
    }

    public ProjectSpaceGroupReqModel(String version, String GAAProjectRef, String GAAProjectName, List<SpaceGroup> GAAProjectSpaceGroups) {
        Version = version;
        this.GAAProjectRef = GAAProjectRef;
        this.GAAProjectName = GAAProjectName;
        this.GAAProjectSpaceGroups = GAAProjectSpaceGroups;


    }

    public static class SpaceGroup {
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

}
