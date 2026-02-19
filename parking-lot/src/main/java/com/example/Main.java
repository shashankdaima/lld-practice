package com.example;


import com.example.enums.SpotType;
import com.example.models.lot.ParkingAttendent;
import com.example.models.lot.ParkingFloor;
import com.example.models.lot.ParkingLot;
import com.example.models.lot.ParkingSpot;
import com.example.models.lot.ParkingTicket;
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
        floor1.addSpot(new ParkingSpot("F1-S2", SpotType.SMALL));
        floor1.addSpot(new ParkingSpot("F1-B1", SpotType.BIKE));
        floor1.addSpot(new ParkingSpot("F1-M1", SpotType.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", SpotType.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2-S1", SpotType.SMALL));
        floor2.addSpot(new ParkingSpot("F2-B1", SpotType.BIKE));
        floor2.addSpot(new ParkingSpot("F2-M1", SpotType.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-M2", SpotType.MEDIUM));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        // Park vehicles
        ParkingAttendent attendant = new ParkingAttendent();

        // Park car1
        ParkingTicket t1 = attendant.assignParkingTicket(car1);
        System.out.println("Parked " + car1.getPlateNumber() + " at spot " + t1.getSpot().getSpotId());

        // Park car2
        var t2 = attendant.assignParkingTicket(car2);
        System.out.println("Parked " + car2.getPlateNumber() + " at spot " + t2.getSpot().getSpotId());

        // Park bike1
        var t3 = attendant.assignParkingTicket(bike1);
        System.out.println("Parked " + bike1.getPlateNumber() + " at spot " + t3.getSpot().getSpotId());

        // Park truck1
        var t4 = attendant.assignParkingTicket(truck1);
        System.out.println("Parked " + truck1.getPlateNumber() + " at spot " + t4.getSpot().getSpotId());

        // Unpark car1
        attendant.unAssignParkingTicket(t1);
        System.out.println("Unparked " + car1.getPlateNumber() + " from spot " + t1.getSpot().getSpotId());

        // Park car3 — should reuse the freed spot
        var t5 = attendant.assignParkingTicket(car3);
        System.out.println("Parked " + car3.getPlateNumber() + " at spot " + t5.getSpot().getSpotId());

    }
}
