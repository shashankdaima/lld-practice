# Parking Lot — Low Level Design

## Problem Statement

Design a parking lot system that can handle multiple floors, different vehicle types, and parking spot allocation.

---

## Requirements

### Functional
- Support multiple floors, each with multiple parking spots
- Support multiple vehicle types: **Motorcycle**, **Car**, **Truck/Bus**
- Assign the nearest available spot to an incoming vehicle
- Free up a spot when a vehicle leaves
- Track which vehicle is parked in which spot
- Generate a ticket on entry with spot info and timestamp
- Calculate parking fee on exit based on duration

### Non-Functional
- Thread-safe spot allocation (multiple entry/exit gates)
- Scalable to multiple floors and large spot counts

---

## Entities

```
ParkingLot
├── Floor[]
│   └── ParkingSpot[]
│       ├── spotId
│       ├── spotType (MOTORCYCLE / CAR / TRUCK)
│       └── isOccupied
├── Vehicle (abstract)
│   ├── Motorcycle
│   ├── Car
│   └── Truck
├── Ticket
│   ├── ticketId
│   ├── vehicle
│   ├── spot
│   └── entryTime
└── ParkingFeeStrategy (interface)
    ├── HourlyFeeStrategy
    └── FlatFeeStrategy
```

---

## Class Design

### Enums
```java
enum VehicleType { MOTORCYCLE, CAR, TRUCK }
enum SpotType    { MOTORCYCLE, CAR, TRUCK }
```

### Core Classes

| Class | Responsibility |
|---|---|
| `ParkingLot` | Singleton. Entry point. Delegates to floors. |
| `Floor` | Holds spots. Finds nearest free spot for a vehicle type. |
| `ParkingSpot` | Represents one spot. Knows its type and occupancy. |
| `Vehicle` | Abstract. Has plate number and type. |
| `Ticket` | Issued on entry. Holds vehicle, spot, timestamp. |
| `ParkingAttendant` | Handles park/unpark flow. |
| `FeeCalculator` | Computes fee from ticket using a strategy. |

---

## Key Design Patterns

| Pattern | Where Used |
|---|---|
| **Singleton** | `ParkingLot` — only one instance |
| **Strategy** | `FeeCalculator` — swap fee logic without changing core |
| **Factory** | `VehicleFactory` — create vehicle by type |

---

## Spot Assignment Rules

- `MOTORCYCLE` fits in: Motorcycle spot only
- `CAR` fits in: Car spot only
- `TRUCK` fits in: Truck spot only
- Nearest spot = lowest floor → lowest spot index with matching type

---

## Flow

```
Vehicle arrives
    → ParkingAttendant.park(vehicle)
        → ParkingLot.findSpot(vehicleType)
            → Floor.getAvailableSpot(vehicleType)
        → spot.occupy(vehicle)
        → return Ticket

Vehicle leaves
    → ParkingAttendant.unpark(ticket)
        → spot.free()
        → FeeCalculator.calculate(ticket, exitTime)
        → return Receipt
```

---

## Tech Stack

- **Java 17**
- **Gradle 9** (build tool)
- **JUnit 5** (unit testing)
- **Docker** (containerization)

## Build & Run

```bash
gradle build        # compile + test
gradle run          # run main
gradle clean test   # force re-run tests

docker compose up --build   # run in Docker
```
