package com.example.models.vehicles;

import com.example.enums.VehicleSize;

public class Truck extends Vehicle {
    Truck(String plateNumber){
        super(VehicleSize.LARGE, plateNumber);
    }
}
