package com.example.models.vehicles;

import com.example.enums.VehicleSize;

public abstract class Vehicle {
    protected VehicleSize size;
    protected String plateNumber;

    public Vehicle(VehicleSize size, String plateNumber) {
        this.size = size;
        this.plateNumber = plateNumber;
    }

    public VehicleSize getSize() {
        return size;
    }

    public String getPlateNumber() {
        return plateNumber;
    }
}