
import org.omg.CORBA.Context;
import org.omg.CORBA.ContextList;
import org.omg.CORBA.DomainManager;
import org.omg.CORBA.ExceptionList;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Object;
import org.omg.CORBA.Policy;
import org.omg.CORBA.Request;
import javax.naming.InitialContext;
import org.omg.CORBA.SetOverrideType;

import Airline.*;

public class ImpClientService extends ClientServicePOA {

    protected ImpClientService() {
        super();
    }

    @Override
    public void registerNewPassenger(String passengerName, String phonenumber, String address, String email,
            String passport, String seattype) {
        System.out.println("New Passenger Booked:");
        System.out.println("Passenger Full Name: " + passengerName);
        System.out.println("Passenger Phone Number: " + phonenumber);
        System.out.println("Passenger Address: " + address);
        System.out.println("Passenger Email: " + email);
        System.out.println("Passenger Passport ID: " + passport);
        System.out.println("Passenger Seat Type: " + seattype);
        System.out.println();
    }

    @Override
    public void viewAllFlights() {
        System.out.println("All Flights:");
        System.out.println("___________________________________________________________________________________");
        System.out.println("|Flight Number|Departure Airport|Arrival Airport|Departure Time|Arrival Time|Price|");
        System.out.println("|_____________|_________________|_______________|______________|____________|_____|");
        System.out.println("|   00026     |     London      |    Boston     |    18:00     |    1:30    | 150$|");
        System.out.println("|   00055     |     Berlin      |    Sydney     |    13:00     |    12:00   | 350$|");
        System.out.println("|   00069     |     Madrid      |    Cologne    |    04:20     |    6:09    | 69$ |");
        System.out.println("|   00089     |     Krakow      |    Frankfurt  |    10:40     |    11:15   | 50$ |");
        System.out.println("|   00145     |     Tokyo       |    Seoul      |    12:15     |    2:30    | 80$ |");
        System.out.println("|_____________|_________________|_______________|______________|____________|_____|");
    }

    @Override
    public void availableseats() {
        System.out.println("Available Seats:");
        System.out.println("DataBase is Under Maintenance, Please Try Again Later.");
    }

    @Override
    public void payment(double pay) {
        System.out.println("Payment Successful:");
        System.out.println("Payment Added: " + pay + "$");
    }

    @Override
    public boolean _is_a(String repositoryIdentifier) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method '_is_a'");
    }

}
