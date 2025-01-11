package com.universitaskuningan.yamaha_dealer_app;

public class ItemModel {
    String  motorType;
    int motorImage;

    public ItemModel(String motorType, int motorImage) {
        this.motorType = motorType;
        this.motorImage = motorImage;
    }

    public String getMotorType() {
        return motorType;
    }

    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    public int getMotorImage() {
        return motorImage;
    }

    public void setMotorImage(int motorImage) {
        this.motorImage = motorImage;
    }
}
