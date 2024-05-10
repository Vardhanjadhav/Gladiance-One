
package com.espressif.ui.models.scene;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjectTag {

    @SerializedName("Ref")
    @Expose
    private Long ref;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("GAAProjectRef")
    @Expose
    private Long gAAProjectRef;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("IsSystemDefinedScene")
    @Expose
    private Integer isSystemDefinedScene;
    @SerializedName("GAAProjectSpaceTypeRef")
    @Expose
    private Long gAAProjectSpaceTypeRef;
    @SerializedName("GAAProjectSpaceTypeName")
    @Expose
    private String gAAProjectSpaceTypeName;
    @SerializedName("GAAProjectName")
    @Expose
    private String gAAProjectName;
    @SerializedName("Configurations")
    @Expose
    private List<Configuration> configurations;

    public Long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getgAAProjectRef() {
        return gAAProjectRef;
    }

    public void setgAAProjectRef(Long gAAProjectRef) {
        this.gAAProjectRef = gAAProjectRef;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIsSystemDefinedScene() {
        return isSystemDefinedScene;
    }

    public void setIsSystemDefinedScene(Integer isSystemDefinedScene) {
        this.isSystemDefinedScene = isSystemDefinedScene;
    }

    public Long getgAAProjectSpaceTypeRef() {
        return gAAProjectSpaceTypeRef;
    }

    public void setgAAProjectSpaceTypeRef(Long gAAProjectSpaceTypeRef) {
        this.gAAProjectSpaceTypeRef = gAAProjectSpaceTypeRef;
    }

    public String getgAAProjectSpaceTypeName() {
        return gAAProjectSpaceTypeName;
    }

    public void setgAAProjectSpaceTypeName(String gAAProjectSpaceTypeName) {
        this.gAAProjectSpaceTypeName = gAAProjectSpaceTypeName;
    }

    public String getgAAProjectName() {
        return gAAProjectName;
    }

    public void setgAAProjectName(String gAAProjectName) {
        this.gAAProjectName = gAAProjectName;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    public ObjectTag(Long ref, String name, Long gAAProjectRef, String code, Integer isSystemDefinedScene, Long gAAProjectSpaceTypeRef, String gAAProjectSpaceTypeName, String gAAProjectName, List<Configuration> configurations) {
        this.ref = ref;
        this.name = name;
        this.gAAProjectRef = gAAProjectRef;
        this.code = code;
        this.isSystemDefinedScene = isSystemDefinedScene;
        this.gAAProjectSpaceTypeRef = gAAProjectSpaceTypeRef;
        this.gAAProjectSpaceTypeName = gAAProjectSpaceTypeName;
        this.gAAProjectName = gAAProjectName;
        this.configurations = configurations;
    }
}
