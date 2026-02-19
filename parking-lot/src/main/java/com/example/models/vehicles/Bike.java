package com.example.models.vehicles;

import com.example.enums.VehicleSize;

public class Bike extends Vehicle {
    Bike(String plateNumber){
        super(VehicleSize.TWO_WHEELER, plateNumber);
    }
}
