package com.espressif.ui.activities;

import java.util.ArrayList;

public class param {

    private String name;
    private String type;
    private String data_type;
    private String ui_type;


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

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getUi_type() {
        return ui_type;
    }

    public void setUi_type(String ui_type) {
        this.ui_type = ui_type;
    }

    public param(String name, String type, String data_type, String ui_type) {
        this.name = name;
        this.type = type;
        this.data_type = data_type;
        this.ui_type = ui_type;
    }
}
