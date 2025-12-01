import java.time.LocalDate;

public final class HotelReservation extends Reservation {
    private Hotel hotel;
    private int numberOfGuests;
    private LocalDate bookingDate;

    public HotelReservation(Hotel hotel, String guestName, 
                           String guestContact, int numberOfGuests) {
        super(guestName, guestContact);
        this.hotel = hotel;
        this.numberOfGuests = numberOfGuests;
        this.bookingDate = LocalDate.now();
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Override
    public void display() {
        System.out.printf(
            "Tipe: Hotel%n" +
            "Nomor Konfirmasi: %s%n" +
            "Nama Tamu: %s%n" +
            "Kontak: %s%n" +
            "Hotel: %s%n" +
            "Lokasi: %s%n" +
            "Check-in: %s%n" +
            "Check-out: %s%n" +
            "Jumlah Tamu: %d%n" +
            "Harga Total: Rp%.0f%n" +
            "Tanggal Pemesanan: %s%n",
            confirmationNumber, personName, personContact, 
            hotel.getHotelName(), hotel.getLocation(),
            hotel.getCheckInDate(), hotel.getCheckOutDate(), 
            numberOfGuests, hotel.getPrice() * numberOfGuests, bookingDate);
    }
}