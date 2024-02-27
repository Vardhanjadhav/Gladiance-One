package com.espressif.ui.models;

import java.util.List;

public class ProjectSpaceRequestModel {

    private String Version;
    private List<Project> Projects;
    private List<Space> Spaces;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public List<Project> getProjects() {
        return Projects;
    }

    public void setProjects(List<Project> projects) {
        Projects = projects;
    }

    public List<Space> getSpaces() {
        return Spaces;
    }

    public void setSpaces(List<Space> spaces) {
        Spaces = spaces;
    }

    public ProjectSpaceRequestModel(String version, List<Project> projects, List<Space> spaces) {
        Version = version;
        Projects = projects;
        Spaces = spaces;
    }

    public static class Project {
        private String GAAProjectRef;
        private String GAAProjectName;

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

        public Project(String GAAProjectRef, String GAAProjectName) {
            this.GAAProjectRef = GAAProjectRef;
            this.GAAProjectName = GAAProjectName;
        }
    }

   public static class Space {
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
}


