package com.example;

import java.util.ArrayList;

import com.example.enums.SpotType;
import com.example.enums.VehicleSize;
import com.example.models.lot.ParkingFloor;
import com.example.models.lot.ParkingLot;
import com.example.models.lot.ParkingSpot;
import com.example.models.vehicles.Bike;
import com.example.models.vehicles.Car;
import com.example.models.vehicles.Truck;
import com.example.models.vehicles.Vehicle;
import com.example.utils.PlateNumberGenerator;

public class Main {
    public static void main(String[] args) {
        
        ParkingLot parkingLot = ParkingLot.getInstance();

        // Vehicles        
        Car  car1= new Car(PlateNumberGenerator.generate());
        Car  car2= new Car(PlateNumberGenerator.generate());
        Car  car3= new Car(PlateNumberGenerator.generate());
        Bike bike1= new Bike(PlateNumberGenerator.generate());
        Truck truck1= new Truck(PlateNumberGenerator.generate()); 
    
        Vehicle[] vehicles= {car1, car2, car3, bike1, truck1};
        


        // ParkingSpots
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("F1-S1", SpotType.SMALL));
        floor1.addSpot(new ParkingSpot("F1-M1", SpotType.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", SpotType.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2-M1", SpotType.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-M2", SpotType.MEDIUM));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);


    }
}
