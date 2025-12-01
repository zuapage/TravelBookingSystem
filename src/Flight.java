import java.time.LocalDate;

public class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int passengers;
    private double price;
    private int availableSeats;

    public Flight(String flightNumber, String origin, String destination, 
                  LocalDate departureDate, int passengers, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.passengers = passengers;
        this.price = price;
        this.availableSeats = passengers;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public int getPassengers() {
        return passengers;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String displayInfo() {
        return String.format("Penerbangan: %s | %s -> %s | Tanggal: %s | Harga: Rp%.0f | Kursi: %d/%d",
            flightNumber, origin, destination, departureDate, price, availableSeats, passengers);
    }
}