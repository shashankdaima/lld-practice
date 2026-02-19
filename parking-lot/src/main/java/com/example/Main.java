package com.example;

import com.example.models.vehicles.Bike;
import com.example.models.vehicles.Car;
import com.example.models.vehicles.Truck;
import com.example.utils.PlateNumberGenerator;

public class Main {
    public static void main(String[] args) {
        Car  car1= new Car(PlateNumberGenerator.generate());
        Car  car2= new Car(PlateNumberGenerator.generate());
        Car  car3= new Car(PlateNumberGenerator.generate());
        Bike bike1= new Bike(PlateNumberGenerator.generate());
        Truck truck1= new Truck(PlateNumberGenerator.generate()); 
    
        
    }
}
