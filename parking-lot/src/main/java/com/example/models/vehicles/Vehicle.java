package com.example.models.vehicles;

import com.example.enums.VehicleSize;

public abstract class Vehicle{
    VehicleSize size ;
    String plateNumber;

    Vehicle(VehicleSize size, String plateNumber){
        this.size=size;
        this.plateNumber=plateNumber;
    }
}