package com.udacity.gradle.builditbigger.backend;

import java.util.List;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private List myData;

    public List getData() {
        return myData;
    }

    public void setData(List data) {
        myData = data;
    }
}