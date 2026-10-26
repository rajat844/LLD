# Cab Booking System

## Fuctional Requirements

1. Drivers can mark themseleves available and update their current location.
2. User can enter source and destination, the estimation strategy will give estimate across vehicles and on proceed will send the notification to drivers by matching strategy about the trip.
3. Drivers can accept the trip when AVAILABLE. Both user and driver can CANCEL the trip before it moves to IN_PROGRESS state. Cancellation strategy will the cancellation charges.
4. After trip is completed user can pay with cash or methods handled by the payment strategy.

## Non Functional Requirements

1. Matching Strategy shall handle concurrency.
2. Follow open closed principle.
3. Prevent illegal state transition of trip.

## Entities

1. Driver
2. Vehicle
3. User
4. Trip
5. Payment
6. Driver Status
7. Location
8. VehicleType
9. Trip Status
10. Payment Status
11. Fare Estimation Strategy
12. Driver Matching Strategy
13. Cancellation Strategy
14. Payment Strategy
15. Booking State
16. Booking Manager