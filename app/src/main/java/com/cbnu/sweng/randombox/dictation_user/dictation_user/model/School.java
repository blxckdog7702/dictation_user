package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import java.io.Serializable;

/**
 * Created by user on 2017-08-14.
 */

public class School implements Serializable {

    public volatile static School school;
    private String name;
    private String regionn1;
    private String regionn2;
    private String address;
    private String _id;

    public static School getInstance() {
        if(school == null){
            school = new School();
        }
        return school;
    }

    private School() {
    }

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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
