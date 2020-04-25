package Airport;
import java.time.LocalDate;
import java.time.Period;

public class Flight {
	public static enum eFlightType {Arrival, Departures}
	public static enum eStatus {OnTime, Delayed, Canceled}
	
	private String flightNumber;
	private String airlineName;
	private eFlightType flightType;
	private String origin;
	private String destination;
	private LocalDate departuretDate;
	private LocalDate arrivalDate;
	private Period durationOfFlight;
	private String gate;
	private eStatus status;
	
	public Flight(String airlineName, eFlightType flightType, String origin, String destination, LocalDate flightDate, LocalDate arrivalDate, String gate) {
		this.airlineName = airlineName;
		this.flightType = flightType;
		this.destination = destination;
		this.departuretDate = flightDate;
		this.arrivalDate = arrivalDate;
		this.durationOfFlight = Period.between(flightDate, arrivalDate);
		this.gate = gate;
		this.status = eStatus.OnTime;
	}
	
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalDate getFlightDate() {
		return this.departuretDate;
	}
	
	public Period getDurationOfFlight() {
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
		this.departuretDate = newDate;
		this.arrivalDate = this.departuretDate.plus(this.durationOfFlight);
	}

	public eFlightType getFlightType() {
		return this.getFlightType();
	}
	
	@Override
	public String toString() {
		String str = "Flight number: "+ this.flightNumber + " "
				   + "of Airline: "+ this.airlineName +" | "
				   + "Type: "+ this.flightType.name() +" | "
				   + "Departure from: "+ this.origin +" "
				   + "in gate: "+ this.gate +" "
				   + "at "+ this.departuretDate.toString() +" | "
				   + "Landing in: "+ this.destination +" "
				   + "at :"+ this.arrivalDate.toString() +" | "
				   + "Duration of flight: "+ this.durationOfFlight.toString() +" | "
				   + "Current status: "+ this.status.toString();
		return str;
	}
}
