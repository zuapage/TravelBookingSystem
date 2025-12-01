# Travel Booking System

## Group Information

**Course**: COSC6047 - Introduction to Programming for Business  
**Assignment Type**: Group Project (Group 3)

### Team Members
1. ILHAM MAULANA SULAEMAN  2802521680 
2. NAZWA ANINDYA           2802554640 
3. SILVIA CHOIRUNNISA      2802554413 

## Overview

Console-based Java travel booking application. Users dapat mencari dan memesan penerbangan serta hotel dengan nomor konfirmasi otomatis. Implements advanced OOP concepts: encapsulation, inheritance, polymorphism, sealed classes, pattern matching, lambda expressions, dan stream API.

## System Requirements

- Java 21 LTS (minimum Java 17)
- VS Code with Extension Pack for Java
- Operating System: Windows, Mac, or Linux

## Project Structure

```
TravelBookingSystem/
├── README.md
└── src/
    ├── Main.java
    ├── Flight.java
    ├── Hotel.java
    ├── Reservation.java
    ├── FlightReservation.java
    ├── HotelReservation.java
    └── TravelBookingSystem.java
```

## System Design

### Classes

| Class | Responsibility |
|-------|---|
| Main | Application entry point |
| Flight | Flight entity: flightNumber, origin, destination, departureDate, price, availableSeats |
| Hotel | Hotel entity: hotelId, hotelName, location, checkInDate, checkOutDate, price |
| Reservation | Sealed abstract base class; generates 6-digit confirmation number; permits only FlightReservation dan HotelReservation |
| FlightReservation | Final class extends Reservation; stores flight, numberOfPassengers, bookingDate |
| HotelReservation | Final class extends Reservation; stores hotel, numberOfGuests, bookingDate |
| TravelBookingSystem | Main orchestrator; manages flights, hotels, reservations; handles UI dan business logic |

### Design Patterns

- **Encapsulation**: All fields private dengan public getter/setter methods
- **Inheritance**: FlightReservation dan HotelReservation extend Reservation
- **Polymorphism**: List<Reservation> menyimpan kedua tipe, display() method overridden
- **Sealed Class**: Restricts extension hanya ke specified subclasses
- **Final Class**: Prevents further subclassing dari FlightReservation dan HotelReservation

## Features

### 1. Cari Penerbangan
```
Input: kota asal, kota tujuan, tanggal
Process: Filter menggunakan Stream API
Output: Available flights with details (number, route, date, price, seats)
```

### 2. Cari Hotel
```
Input: lokasi, check-in date, check-out date
Process: Validasi tanggal dan filter available hotels
Output: Available hotels dengan lengkap (ID, name, location, price)
```

### 3. Pesan Penerbangan
```
Input: flight number, passenger name, contact, number of passengers
Process: Validate seats, create FlightReservation, generate confirmation
Output: Confirmation dengan nomor konfirmasi 6 digit
```

### 4. Pesan Hotel
```
Input: hotel ID, guest name, contact, number of guests
Process: Create HotelReservation, generate confirmation number
Output: Confirmation dengan detail lengkap
```

### 5. Batalkan Reservasi
```
Input: confirmation number
Process: Pattern matching untuk identify type, remove from list, restore seats
Output: Cancellation confirmation atau error message
```

### 6. Lihat Semua Pemesanan
```
Output: List semua reservasi dengan polymorphic display()
```

## Implementation Details

### Exception Handling
- **NumberFormatException**: Try-catch saat parsing integers dengan retry mechanism
- **DateTimeParseException**: Try-catch saat parsing dates (format: yyyy-MM-dd)
- **InputMismatchException**: Graceful input validation dengan re-prompting

### Java Features Used
- **Lambda Expression**: `flights.stream().filter(f -> f.getOrigin().equalsIgnoreCase(origin) && ...)`
- **Stream API**: Filter, map, toList operations pada collections
- **Pattern Matching**: `if (res instanceof FlightReservation fr) { ... }`
- **Collections**: ArrayList dengan generics untuk type safety
- **DateTime API**: LocalDate untuk date handling dan validation

### Validation Rules
- String input: Non-empty check
- Integer input: Must be numeric dan positive
- Date input: Format yyyy-MM-dd
- Business rules: Available seats > 0, checkout > checkin, confirmation exists

## Installation & Setup

### 1. Install Java 21
```bash
# Windows: Download JDK 21 dari oracle.com/java
# Mac: brew install openjdk@21
# Linux: sudo apt-get install openjdk-21-jdk

# Verify
java -version
```

### 2. Install VS Code Extension
- Open VS Code
- Extensions (Ctrl+Shift+X)
- Search dan install "Extension Pack for Java"

### 3. Setup Project
```bash
git clone https://github.com/zuapage/TravelBookingSystem.git
```

---

## Running Application

### Method 1: VS Code (Recommended)
1. Open Main.java
2. Click "Run" button (green triangle)
3. Program starts in terminal

### Method 2: Command Line
```bash
javac src/*.java
java -cp src Main
```

