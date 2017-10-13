package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

/**
 * Created by SAMSUNG on 2017-10-13.
 */

public class RecyclerItem {
    private String name;

    public RecyclerItem (String name){
        this.name = name;
    }

    public RecyclerItem() {

    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
