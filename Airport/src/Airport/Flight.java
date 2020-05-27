package Airport;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Flight {
	public static enum eStatus {OnTime, Delayed, Canceled}
	
	private String flightNumber;
	private String airlineName;
	private String origin;
	private String destination;
	private LocalDate departureDate;
	private Duration durationOfFlight;
	private LocalDate returnDate;
	private Duration stayingDuarion;
	private String gate;
	private eStatus status;
	
	public Flight(String airlineName, String origin, String destination, LocalDate departureDate, Duration durationOfFlight, LocalDate returnDate, String gate) {
		this.airlineName = airlineName;
		this.origin = origin;
		this.destination = destination;
		
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		this.durationOfFlight = durationOfFlight;
		this.stayingDuarion = Duration.between(departureDate, returnDate);
		
		this.gate = gate;
		this.status = eStatus.OnTime;
	}
	
	public Flight(Scanner scan) throws FileNotFoundException {
		this.airlineName = scan.nextLine();
		this.origin = scan.nextLine();
		this.destination = scan.nextLine();
		
		this.departureDate = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
		this.returnDate = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
		this.durationOfFlight = Duration.ofMinutes(scan.nextInt());
		this.stayingDuarion = Duration.between(departureDate, returnDate);
		
		scan.nextLine();
		this.gate = scan.next();
		scan.nextLine();
		this.status = eStatus.valueOf(scan.next());
	}
	
	public void save(PrintWriter pw) throws FileNotFoundException {
		pw.write(this.airlineName + "\n");
		pw.write(this.origin +"\n");
		pw.write(this.destination +"\n");
		
		pw.write(this.departureDate.getYear() +" "+ this.departureDate.getMonth() +" "+ this.departureDate.getDayOfMonth() +"\n");
		pw.write(this.returnDate.getYear() +" "+ this.returnDate.getMonth() +" "+ this.returnDate.getDayOfMonth() +"\n");
		pw.write((int)this.durationOfFlight.toMinutes() +"\n");
		
		pw.write(this.gate +"\n");
		pw.write(this.status.toString() +"\n");
	}
	
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalDate getFlightDate() {
		return this.departureDate;
	}
	
	public Duration getDurationOfFlight() {
		return this.durationOfFlight;
	}

	public eStatus getStatus() {
		return this.status;
	}

	public String getFlightNumber() {
		return this.flightNumber;
	}

	public void setStatus(eStatus status) {
		this.status = status;
	}

	public void setDepartureDate(LocalDate newDate) {
		this.departureDate = newDate;
	}
	
	@Override
	public String toString() {
		String str = "Flight number: "+ this.flightNumber + " "
				   + "of Airline: "+ this.airlineName +" | "
				   + "Departure from: "+ this.origin +" "
				   + "in gate: "+ this.gate +" "
				   + "at "+ this.departureDate.toString() +" | "
				   + "Landing in: "+ this.destination +" "
				   + "and returning at :"+ this.returnDate.toString() +" | "
				   + "Duration of flights: "+ this.durationOfFlight +" hours | "
				   + "Duration of staying: "+ stayingDuarion.toDays() +" days | "
				   + "Current status: "+ this.status.toString();
		return str;
	}

	public String getDestination() {
		return this.destination;
	}

	public String getOrigin() {
		return this.origin;
	}

	public LocalDate getDepartureDate() {
		return this.departureDate;
	}
	
	public LocalDate getReturnDate() {
		return this.returnDate;
	}

	public String getAirline() {
		return this.airlineName;
	}

	public String getGate() {
		return this.gate;
	}

	public Duration getStayingDuratuin() {
		return this.stayingDuarion;
	}
}
