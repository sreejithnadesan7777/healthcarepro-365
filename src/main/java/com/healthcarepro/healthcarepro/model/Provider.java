package com.healthcarepro.healthcarepro.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "provider")
public class Provider {
    private String providerid;
    private String providername;
    private int providerage;

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public int getProviderage() {
        return providerage;
    }

    public void setProviderage(int providerage) {
        this.providerage = providerage;
    }
}
