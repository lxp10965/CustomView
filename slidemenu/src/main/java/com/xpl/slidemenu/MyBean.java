package com.xpl.slidemenu;

public class MyBean {
    private String name;

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyBean(String name) {
        this.name = name;
    }
}