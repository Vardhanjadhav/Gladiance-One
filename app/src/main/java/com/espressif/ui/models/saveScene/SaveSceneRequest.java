package com.espressif.ui.models.saveScene;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.List;

public class SaveSceneRequest {
    @SerializedName("Ref")
    @Expose
    private BigInteger ref;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("GAAProjectSpaceTypeRef")
    @Expose
    private Long gAAProjectSpaceTypeRef;
    @SerializedName("Configurations")
    @Expose
    private List<SceneConfig> configurations;

    public BigInteger getRef() {
        return ref;
    }

    public void setRef(BigInteger ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getgAAProjectSpaceTypeRef() {
        return gAAProjectSpaceTypeRef;
    }

    public void setgAAProjectSpaceTypeRef(Long gAAProjectSpaceTypeRef) {
        this.gAAProjectSpaceTypeRef = gAAProjectSpaceTypeRef;
    }

    public List<SceneConfig> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<SceneConfig> configurations) {
        this.configurations = configurations;
    }
}
