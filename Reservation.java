public class Reservation {
    private String passengerCode;
    private String flightCode;
    private String seatNumber;
    private boolean isConfirmed;
    private Reservation.ReservationType reservationType;

    public Reservation(String passengerCode, String flightCode, String seatNumber, Reservation.ReservationType reservationType, boolean isConfirmed) {
        this.passengerCode = passengerCode;
        this.flightCode = flightCode;
        this.seatNumber = seatNumber;
        this.reservationType = reservationType;
        this.isConfirmed = isConfirmed;
    }

    public enum ReservationType {
        FIRST_CLASS,
        ECONOMY
    }
    public String getPassengerCode() {
        return this.passengerCode;
    }

    public void setPassengerCode(String passengerCode) {
        this.passengerCode = passengerCode;
    }

    public String getFlightCode() {
        return this.flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean getIsConfirmed() {
        return this.isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
    public Reservation.ReservationType getReservationType() {
        return this.reservationType;
    }
    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }

    public void confirmReservation() {
        this.isConfirmed = true;
    }

    public void changeSeatNumber(String newSeatNumber) {
        if (this.isConfirmed) {
            this.seatNumber = newSeatNumber;
        } else {
            System.out.println("Cannot change seat number until reservation is confirmed.");
        }
    }
}