package com.espressif.ui.models;

import java.util.List;

public class ProjectAreaLandingReqModel {

    private String version;
    private String GAAProjectSpaceRef;
    private String GAAProjectSpaceName;
    private List<Area> Areas;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

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

    public List<Area> getAreas() {
        return Areas;
    }

    public void setAreas(List<Area> areas) {
        Areas = areas;
    }

    public ProjectAreaLandingReqModel(String version, String GAAProjectSpaceRef, String GAAProjectSpaceName, List<Area> Areas) {
        this.version = version;
        this.GAAProjectSpaceRef = GAAProjectSpaceRef;
        this.GAAProjectSpaceName = GAAProjectSpaceName;
        this.Areas = Areas;
    }

    public static class Area {
        private String GAAProjectSpaceTypeAreaRef;
        private String GAAProjectSpaceTypeAreaName;
        private String wifiSSID;
        private String wifiPassword;


        public String getGAAProjectSpaceTypeAreaRef() {
            return GAAProjectSpaceTypeAreaRef;
        }

        public void setGAAProjectSpaceTypeAreaRef(String GAAProjectSpaceTypeAreaRef) {
            this.GAAProjectSpaceTypeAreaRef = GAAProjectSpaceTypeAreaRef;
        }

        public String getGAAProjectSpaceTypeAreaName() {
            return GAAProjectSpaceTypeAreaName;
        }

        public void setGAAProjectSpaceTypeAreaName(String GAAProjectSpaceTypeAreaName) {
            this.GAAProjectSpaceTypeAreaName = GAAProjectSpaceTypeAreaName;
        }

        public String getWifiSSID() {
            return wifiSSID;
        }

        public void setWifiSSID(String wifiSSID) {
            this.wifiSSID = wifiSSID;
        }

        public String getWifiPassword() {
            return wifiPassword;
        }

        public void setWifiPassword(String wifiPassword) {
            this.wifiPassword = wifiPassword;
        }



        public Area(String GAAProjectSpaceTypeAreaRef, String GAAProjectSpaceTypeAreaName, String wifiSSID, String wifiPassword) {
            this.GAAProjectSpaceTypeAreaRef = GAAProjectSpaceTypeAreaRef;
            this.GAAProjectSpaceTypeAreaName = GAAProjectSpaceTypeAreaName;
            this.wifiSSID = wifiSSID;
            this.wifiPassword = wifiPassword;

        }
    }
}
