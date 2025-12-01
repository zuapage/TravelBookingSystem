import java.time.LocalDate;

public final class FlightReservation extends Reservation {
    private Flight flight;
    private int numberOfPassengers;
    private LocalDate bookingDate;

    public FlightReservation(Flight flight, String passengerName, 
                            String passengerContact, int numberOfPassengers) {
        super(passengerName, passengerContact);
        this.flight = flight;
        this.numberOfPassengers = numberOfPassengers;
        this.bookingDate = LocalDate.now();
    }

    public Flight getFlight() {
        return flight;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    @Override
    public void display() {
        System.out.printf(
            "Tipe: Penerbangan%n" +
            "Nomor Konfirmasi: %s%n" +
            "Nama Penumpang: %s%n" +
            "Kontak: %s%n" +
            "Penerbangan: %s%n" +
            "Rute: %s -> %s%n" +
            "Tanggal: %s%n" +
            "Jumlah Penumpang: %d%n" +
            "Harga Total: Rp%.0f%n" +
            "Tanggal Pemesanan: %s%n",
            confirmationNumber, personName, personContact, 
            flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(),
            flight.getDepartureDate(), numberOfPassengers, 
            flight.getPrice() * numberOfPassengers, bookingDate);
    }
}