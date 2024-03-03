
package com.espressif.ui.models.arealandingmodel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("GAAProjectSpaceRef")
    @Expose

    private Long gAAProjectSpaceRef;
    @SerializedName("GAAProjectSpaceName")
    @Expose
    private String gAAProjectSpaceName;
    @SerializedName("Areas")
    @Expose

    private List<Area> areas;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getGAAProjectSpaceRef() {
        return gAAProjectSpaceRef;
    }

    public void setGAAProjectSpaceRef(Long gAAProjectSpaceRef) {
        this.gAAProjectSpaceRef = gAAProjectSpaceRef;
    }

    public String getGAAProjectSpaceName() {
        return gAAProjectSpaceName;
    }

    public void setGAAProjectSpaceName(String gAAProjectSpaceName) {
        this.gAAProjectSpaceName = gAAProjectSpaceName;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

}
