package com.example.models.vehicles;

import com.example.enums.VehicleSize;

public class Car extends Vehicle {
    public Car(String plateNumber){
        super(VehicleSize.SMALL, plateNumber);
    }
}
