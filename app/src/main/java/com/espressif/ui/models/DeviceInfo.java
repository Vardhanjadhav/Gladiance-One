package com.espressif.ui.models;

import java.util.List;

public class DeviceInfo {

    private String node_id;
    private String config_version;
    private List<Device> devices;

    public String getNode_id() {
        return node_id;
    }

    public String getConfig_version() {
        return config_version;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public static class Device {
        private String name;
        private String type;
        private String primary;
        private List<Param> params;

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getPrimary() {
            return primary;
        }

        public List<Param> getParams() {
            return params;
        }
    }

    public static class Param {
        private String name;
        private String type;
        private String data_type;
        private List<String> properties;
        private String ui_type;

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getData_type() {
            return data_type;
        }

        public List<String> getProperties() {
            return properties;
        }

        public String getUi_type() {
            return ui_type;
        }
    }

}
