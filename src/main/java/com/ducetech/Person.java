package com.ducetech;

import java.io.Serializable;

/**
 * Created by lenzhao on 17-2-20.
 */
public class Person implements Serializable {

    private String name;

    private String pwd;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
