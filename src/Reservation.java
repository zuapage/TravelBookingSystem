import java.util.Random;

public sealed abstract class Reservation 
    permits FlightReservation, HotelReservation {
    
    protected String confirmationNumber;
    protected String personName;
    protected String personContact;

    protected Reservation(String personName, String personContact) {
        this.confirmationNumber = generateConfirmationNumber();
        this.personName = personName;
        this.personContact = personContact;
    }

    protected String generateConfirmationNumber() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public abstract void display();
}