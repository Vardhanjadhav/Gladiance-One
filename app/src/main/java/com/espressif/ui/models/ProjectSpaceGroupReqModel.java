package com.espressif.ui.models;

import java.util.List;

public class ProjectSpaceGroupReqModel {
    private String version;
    private String GAAProjectRef;
    private String GAAProjectName;
    private List<SpaceGroup> SpaceGroups;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public List<SpaceGroup> getSpaceGroups() {
        return SpaceGroups;
    }

    public void setSpaceGroups(List<SpaceGroup> spaceGroups) {
        SpaceGroups = spaceGroups;
    }

    public ProjectSpaceGroupReqModel(String version, String GAAProjectRef, String GAAProjectName, List<SpaceGroup> spaceGroups) {
        this.version = version;
        this.GAAProjectRef = GAAProjectRef;
        this.GAAProjectName = GAAProjectName;
        SpaceGroups = spaceGroups;
    }

    public static class SpaceGroup {
        private String GAAProjectSpaceGroupRef;
        private String GAAProjectSpaceGroupName;
        private int displayOrder;

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
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
        }
    }
}


