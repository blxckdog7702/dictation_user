package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by user on 2017-08-14.
 */

public class School implements Serializable {

    public volatile static School school;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("region1")
    @Expose
    private String regionn1;
    @SerializedName("region2")
    @Expose
    private String regionn2;
    @SerializedName("address")
    @Expose
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionn1() {
        return regionn1;
    }

    public void setRegionn1(String regionn1) {
        this.regionn1 = regionn1;
    }

    public String getRegionn2() {
        return regionn2;
    }

    public void setRegionn2(String regionn2) {
        this.regionn2 = regionn2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
