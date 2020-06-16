package com.example.testparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private String name, color, brand;

    public Car(String name, String color, String brand) {
        this.name = name;
        this.color = color;
        this.brand = brand;
    }

    protected Car(Parcel in) {
        name = in.readString();
        color = in.readString();
        brand = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(color);
        dest.writeString(brand);
    }
}
