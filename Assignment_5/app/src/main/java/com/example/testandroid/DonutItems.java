package com.example.testandroid;

public class DonutItems {
    String donutName;

    Integer image;

    public DonutItems(String donutName, Integer image) {
        this.donutName = donutName;
        this.image = image;
    }

    public String getDonutName() {
        return donutName;
    }

    public void setDonutName(String donutName) {
        this.donutName = donutName;
    }


    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
