package com.example.thuchanh_1;

import android.widget.Switch;

import java.io.Serializable;

public class Device implements Serializable {
    //Attributes
    private int ID;
    private String Image;
    private String Name;
    private String Wattage;
    private boolean Status;


    public Device(int ID, String image, String name, String wattage, boolean status) {
        this.ID = ID;
        Image = image;
        Name = name;
        Wattage = wattage;
        Status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWattage() {
        return Wattage;
    }

    public void setWattage(String wattage) {
        Wattage = wattage;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
    //Contrustors
}