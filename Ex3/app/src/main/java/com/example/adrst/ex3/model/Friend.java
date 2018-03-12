package com.example.adrst.ex3.model;

/**
 * Created by adrst on 23.02.2018.
 */

public class Friend {
    private String name, birthday;

    public Friend(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (" + birthday + ")";
    }
}
