package Airport;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.Scanner;

public class program {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		FlightsControl flightsControl = new FlightsControl();
		Flight flight;

		System.out.println("Hello, Welcome to TalAirport Flights control system!");
		
			 int id;
			 String airlineName;
			 String origin;
			 String destination;
			 LocalDate flightDate;
			 LocalDate arrivalDate;
			 Period durationOfFlight;
			 String gate;

			System.out.println("Enter flight id: ");
			id = scn.nextInt();
			System.out.println("Enter the airline name that you like to: ");
			airlineName = scn.next();
			System.out.println("Enter origin city: ");
			origin = scn.next();
			System.out.println("Enter destination: ");
			destination = scn.next();
			System.out.println("Enter flight date: (Year (Enter) Month (Enter) Day (Enter))");
			flightDate = LocalDate.of(scn.nextInt(), scn.nextInt(), scn.nextInt());
			System.out.println("Enter arrival date: (Year (Enter) Month (Enter) Day (Enter))");
			arrivalDate = LocalDate.of(scn.nextInt(), scn.nextInt(), scn.nextInt());
			System.out.println("Enter gate number: ");
			gate = scn.next();
			
			//flight = new Flight(airlineName, origin, destination, flightDate, arrivalDate, gate);
			
		//	flightsControl.addFlight(flight);
			
			System.out.println(flightsControl.toString());
			
			System.out.println("Thank you have a nice day! :)");
		
		}
}
