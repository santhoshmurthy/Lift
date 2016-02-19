package com.LAW.Lift.model;

/**
 * Created by santhoshis on 2/18/2016.
 */
public class librarycard {
    private String retimag, monthtext;

    public librarycard(String retimag,String monthtext) {
        this.retimag=retimag;
        this.monthtext=monthtext;

    }
    public String getretimag() {
        return retimag;
    }
    public String getmonthtext() {
        return monthtext;
    }

}
