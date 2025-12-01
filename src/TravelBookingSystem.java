import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TravelBookingSystem {
    private List<Flight> flights;
    private List<Hotel> hotels;
    private List<Reservation> reservations;
    private Scanner scanner;
    private DateTimeFormatter dateFormatter;

    public TravelBookingSystem() {
        this.flights = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        initializeData();
    }

    private void initializeData() {
        // Initialize penerbangan
        flights.add(new Flight("GA101", "Jakarta", "Surabaya", 
                               LocalDate.of(2025, 2, 10), 4, 850000));
        flights.add(new Flight("GA102", "Jakarta", "Bali", 
                               LocalDate.of(2025, 2, 10), 3, 950000));
        flights.add(new Flight("GA201", "Surabaya", "Jakarta", 
                               LocalDate.of(2025, 2, 15), 2, 850000));
        flights.add(new Flight("GA301", "Bali", "Jakarta", 
                               LocalDate.of(2025, 2, 12), 5, 950000));

        // Initialize hotel
        hotels.add(new Hotel("H001", "Grand Hotel Surabaya", "Surabaya", 
                            LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 13), 2, 650000));
        hotels.add(new Hotel("H002", "Luxury Resort Bali", "Bali", 
                            LocalDate.of(2025, 2, 10), LocalDate.of(2025, 2, 15), 4, 1200000));
        hotels.add(new Hotel("H003", "Business Hotel Jakarta", "Jakarta", 
                            LocalDate.of(2025, 2, 15), LocalDate.of(2025, 2, 18), 3, 550000));
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> searchFlights();
                    case "2" -> searchHotels();
                    case "3" -> bookFlight();
                    case "4" -> bookHotel();
                    case "5" -> cancelReservation();
                    case "6" -> viewAllReservations();
                    case "7" -> {
                        System.out.println("\nTerima kasih telah menggunakan sistem pemesanan perjalanan.");
                        running = false;
                    }
                    default -> System.out.println("\nPilihan tidak valid. Silakan coba lagi.");
                }
            } catch (Exception e) {
                System.out.println("\nTerjadi kesalahan: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n========== SISTEM PEMESANAN PERJALANAN ==========");
        System.out.println("1. Cari Penerbangan");
        System.out.println("2. Cari Hotel");
        System.out.println("3. Pesan Penerbangan");
        System.out.println("4. Pesan Hotel");
        System.out.println("5. Batalkan Reservasi");
        System.out.println("6. Lihat Semua Pemesanan");
        System.out.println("7. Keluar");
        System.out.print("Pilih menu (1-7): ");
    }

    private void searchFlights() {
        System.out.println("\n--- Pencarian Penerbangan ---");
        String origin = getValidStringInput("Masukkan kota asal: ");
        String destination = getValidStringInput("Masukkan kota tujuan: ");
        LocalDate departDate = getValidDateInput("Masukkan tanggal keberangkatan (yyyy-MM-dd): ");

        List<Flight> results = flights.stream()
            .filter(f -> f.getOrigin().equalsIgnoreCase(origin) &&
                        f.getDestination().equalsIgnoreCase(destination) &&
                        f.getDepartureDate().equals(departDate))
            .toList();

        if (results.isEmpty()) {
            System.out.println("\nTidak ada penerbangan tersedia dengan kriteria tersebut.");
        } else {
            System.out.println("\n=== Hasil Pencarian Penerbangan ===");
            results.forEach(f -> System.out.println(f.displayInfo()));
        }
    }

    private void searchHotels() {
        System.out.println("\n--- Pencarian Hotel ---");
        String location = getValidStringInput("Masukkan lokasi: ");
        LocalDate checkIn = getValidDateInput("Masukkan tanggal check-in (yyyy-MM-dd): ");
        LocalDate checkOut = getValidDateInput("Masukkan tanggal check-out (yyyy-MM-dd): ");

        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            System.out.println("\nTanggal check-out harus setelah tanggal check-in.");
            return;
        }

        List<Hotel> results = hotels.stream()
            .filter(h -> h.getLocation().equalsIgnoreCase(location) &&
                        !h.getCheckInDate().isAfter(checkIn) &&
                        !h.getCheckOutDate().isBefore(checkOut))
            .toList();

        if (results.isEmpty()) {
            System.out.println("\nTidak ada hotel tersedia dengan kriteria tersebut.");
        } else {
            System.out.println("\n=== Hasil Pencarian Hotel ===");
            results.forEach(h -> System.out.println(h.displayInfo()));
        }
    }

    private void bookFlight() {
        System.out.println("\n--- Pemesanan Penerbangan ---");
        String flightNumber = getValidStringInput("Masukkan nomor penerbangan: ");

        Flight flight = flights.stream()
            .filter(f -> f.getFlightNumber().equalsIgnoreCase(flightNumber))
            .findFirst()
            .orElse(null);

        if (flight == null) {
            System.out.println("\nPenerbangan tidak ditemukan.");
            return;
        }

        if (flight.getAvailableSeats() <= 0) {
            System.out.println("\nSistem tidak memiliki kursi yang tersedia untuk penerbangan ini.");
            return;
        }

        String passengerName = getValidStringInput("Masukkan nama penumpang: ");
        String passengerContact = getValidStringInput("Masukkan kontak penumpang: ");
        int numPassengers = getValidIntInput("Masukkan jumlah penumpang: ");

        if (numPassengers > flight.getAvailableSeats()) {
            System.out.println("\nJumlah penumpang melebihi kursi tersedia (" + flight.getAvailableSeats() + ").");
            return;
        }

        FlightReservation reservation = new FlightReservation(flight, passengerName, passengerContact, numPassengers);
        flight.setAvailableSeats(flight.getAvailableSeats() - numPassengers);
        reservations.add(reservation);

        System.out.println("\n=== Konfirmasi Pemesanan Penerbangan ===");
        reservation.display();
    }

    private void bookHotel() {
        System.out.println("\n--- Pemesanan Hotel ---");
        String hotelId = getValidStringInput("Masukkan ID Hotel: ");

        Hotel hotel = hotels.stream()
            .filter(h -> h.getHotelId().equalsIgnoreCase(hotelId))
            .findFirst()
            .orElse(null);

        if (hotel == null) {
            System.out.println("\nHotel tidak ditemukan.");
            return;
        }

        String guestName = getValidStringInput("Masukkan nama tamu: ");
        String guestContact = getValidStringInput("Masukkan kontak tamu: ");
        int numGuests = getValidIntInput("Masukkan jumlah tamu: ");

        HotelReservation reservation = new HotelReservation(hotel, guestName, guestContact, numGuests);
        reservations.add(reservation);

        System.out.println("\n=== Konfirmasi Pemesanan Hotel ===");
        reservation.display();
    }

    private void cancelReservation() {
        System.out.println("\n--- Pembatalan Reservasi ---");
        String confirmationNumber = getValidStringInput("Masukkan nomor konfirmasi: ");

        boolean found = false;
        for (Reservation res : reservations) {
            if (res instanceof FlightReservation fr && 
                fr.getConfirmationNumber().equals(confirmationNumber)) {
                Flight flight = fr.getFlight();
                flight.setAvailableSeats(flight.getAvailableSeats() + fr.getNumberOfPassengers());
                reservations.remove(res);
                System.out.println("\nReservasi penerbangan berhasil dibatalkan.");
                found = true;
                break;
            }
        }

        if (!found) {
            for (Reservation res : reservations) {
                if (res instanceof HotelReservation hr && 
                    hr.getConfirmationNumber().equals(confirmationNumber)) {
                    reservations.remove(res);
                    System.out.println("\nReservasi hotel berhasil dibatalkan.");
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("\nNomor konfirmasi tidak ditemukan.");
        }
    }

    private void viewAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("\nTidak ada reservasi saat ini.");
            return;
        }

        System.out.println("\n=== Semua Pemesanan ===");
        reservations.forEach(Reservation::display);
    }

    private String getValidStringInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
        }
    }

    private int getValidIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value <= 0) {
                    System.out.println("Masukkan angka yang lebih besar dari 0.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
            }
        }
    }

    private LocalDate getValidDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine().trim(), dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.");
            }
        }
    }
}