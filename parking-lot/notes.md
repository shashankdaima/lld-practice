# Parking Lot — Build Notes

Step by step notes of how this project was built.

---

## Step 1: Project Setup
- Created a new Java project using a custom shell script `create_new_project`
- Command used: `./create_new_project --name="parking-lot"`
- Project comes with Gradle, Docker, JUnit 5 out of the box

---

## Step 2: Enums
Created two enums to represent types:

**`SpotType`** — types of parking spots available in the lot
```java
public enum SpotType { BIKE, SMALL, MEDIUM, LARGE }
```

**`VehicleSize`** — size of the incoming vehicle
```java
public enum VehicleSize { TWO_WHEELER, SMALL, MEDIUM, LARGE }
```

> Using `VehicleSize` instead of `VehicleType` makes the spot-matching logic more intuitive — you match by size, not by vehicle category.

---

## Step 3: Vehicle Models
Created an abstract `Vehicle` class and three concrete subclasses.

**`Vehicle` (abstract)**
- Fields: `plateNumber`, `size`
- Fields are `protected` with getters so subclasses and other classes can access them

**Subclasses** — each hardcodes its `VehicleSize`:
- `Bike` → `VehicleSize.TWO_WHEELER`
- `Car` → `VehicleSize.SMALL`
- `Truck` → `VehicleSize.LARGE`

---

## Step 4: ParkingSpot
- Holds `spotId`, `SpotType`, and `isOccupied` flag
- Has `setOccupied(boolean)` to mark spot as taken or free

---

## Step 5: ParkingFloor
- Holds a `List<ParkingSpot>`
- Key method: `getAvailableParkingSpot(VehicleSize)`
  - Maps `VehicleSize` → `SpotType` using a `switch`
  - Iterates spots, returns first free matching spot
  - Returns `null` if floor is full for that type
- `synchronized` keyword added to `getAvailableParkingSpot` for thread safety

**Size → Spot mapping:**
```
TWO_WHEELER → BIKE
SMALL       → SMALL
MEDIUM      → MEDIUM
LARGE       → LARGE
```

---

## Step 6: ParkingLot (Singleton)
- Only one instance of `ParkingLot` exists in the system
- Implemented using the **Singleton pattern**
- Private constructor, `static synchronized getInstance()`
- Initialized with an empty `List<ParkingFloor>`, floors added via `addFloor()`

---

## Step 7: Custom Exception
- `ParkingSlotUnavailableException` extends `RuntimeException`
- Thrown when no spot is found across all floors

---

## Step 8: ParkingTicket
- Issued when a vehicle parks
- Holds: `ticketId` (UUID), `vehicle`, `spot`, `entryTime`, `exitTime`
- `entryTime` set automatically to `LocalDateTime.now()` on creation

---

## Step 9: ParkingAttendent
- Orchestrates the park/unpark flow
- `assignParkingTicket(vehicle)`:
  - Iterates all floors
  - Asks each floor for an available spot
  - If found → marks spot occupied, returns a `ParkingTicket`
  - If not found after all floors → throws `ParkingSlotUnavailableException`
- `unAssignParkingTicket(ticket)`:
  - Frees the spot
  - Calculates and returns the fee

**Bug fixed during build:** Originally threw exception on first floor miss instead of continuing to next floor.

---

## Step 10: Fee Strategy (Strategy Pattern)
Used the **Strategy pattern** to make fee calculation swappable.

- `FeeStrategy` (interface) — defines `calculateFee(ParkingTicket)`
- `HourlyFeeStrategy` (implements `FeeStrategy`):
  - Rate: ₹1000/hour
  - Rounds up to next hour (`Math.ceil`)
  - Minimum charge: 1 hour
- `FeeCalculatorService` — holds a `FeeStrategy`, delegates calculation to it

> Benefit: Can swap to `FlatFeeStrategy` or `WeekendFeeStrategy` without touching any other class.

---

## Step 11: Thread Safety
Added `synchronized` to `getAvailableParkingSpot` in `ParkingFloor`.

**Why:** Without it, two threads could both read the same spot as free, then both park in it — a classic race condition.

**Key insight:** The `setOccupied(true)` must happen inside the synchronized block, not outside it. Otherwise the lock is pointless.

```java
public synchronized ParkingSpot getAvailableParkingSpot(VehicleSize vehicleSize) {
    // find spot AND mark it occupied — atomically
}
```

---

## Design Patterns Used

| Pattern | Class | Why |
|---|---|---|
| Singleton | `ParkingLot` | Only one parking lot exists |
| Strategy | `FeeStrategy` + `HourlyFeeStrategy` | Swap fee logic at runtime |
| Template Method | `Vehicle` (abstract) | Common structure, subclasses fill in size |

---

## Key Lessons

- Always check multi-floor traversal logic — don't throw on first floor miss
- Thread safety: lock must cover both *find* and *mark* as one atomic operation
- `VehicleSize` → `SpotType` mapping belongs in `ParkingFloor`, not in `Vehicle` or `ParkingAttendent`
- Singleton `getInstance()` should initialize lazily with an empty list, not require floors upfront
