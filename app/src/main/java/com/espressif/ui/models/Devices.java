package com.espressif.ui.models;

import java.util.ArrayList;
import java.util.List;

public class Devices {

    private String name;
    private String type;
    private String primary;
    //private ArrayList<Param> params;


    public Devices(String name, String type, String primary) {
        this.name = name;
        this.type = type;
        this.primary = primary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

//    public ArrayList<Devices.Param> getParams() {
//        return params;
//    }
//
//    public void setParams(ArrayList<Param> params) {
//        this.params = params;
//    }

//    public class Param {
//        private String name;
//        private String type;
//        private String data_type;
//        private ArrayList<String> properties;
//        private String ui_type;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        public void setType(String type) {
//            this.type = type;
//        }
//
//        public String getData_type() {
//            return data_type;
//        }
//
//        public void setData_type(String data_type) {
//            this.data_type = data_type;
//        }
//
//        public ArrayList<String> getProperties() {
//            return properties;
//        }
//
//        public void setProperties(ArrayList<String> properties) {
//            this.properties = properties;
//        }
//
//        public String getUi_type() {
//            return ui_type;
//        }
//
//        public void setUi_type(String ui_type) {
//            this.ui_type = ui_type;
//        }
//    }
}
