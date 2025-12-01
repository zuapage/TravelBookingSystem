import java.time.LocalDate;

public class Hotel {
    private String hotelId;
    private String hotelName;
    private String location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int capacity;
    private double price;

    public Hotel(String hotelId, String hotelName, String location, 
                 LocalDate checkInDate, LocalDate checkOutDate, int capacity, double price) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.capacity = capacity;
        this.price = price;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getPrice() {
        return price;
    }

    public String displayInfo() {
        return String.format(
            "ID: %s | Nama: %s | Lokasi: %s | Check-in: %s | Check-out: %s | Kapasitas: %d | Harga: Rp%.0f",
            hotelId, hotelName, location, checkInDate, checkOutDate, capacity, price);
    }
}