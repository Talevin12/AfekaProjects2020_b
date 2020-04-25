package Airport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Airport.Flight.eFlightType;
import Airport.Flight.eStatus;

public class FlightsControl {
	private int idCounter = 1;
	private ArrayList<Flight> flights;
	ArrayList<Flight> presentationFlights = new ArrayList<>();

	public FlightsControl() {
		this.flights = new ArrayList<>();
	}

	public void sortFlightsByDate(){
		int n = this.flights.size(); 
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) { 
				if (this.flights.get(j).getFlightDate().isAfter(this.flights.get(j+1).getFlightDate())) { 
					// swap arr[j+1] and arr[i] 
					Flight temp = this.flights.get(j); 
					this.flights.set(j, this.flights.get(j+1)); 
					this.flights.set(j+1, temp); 
				} 
			}
		}
	}

	public void sortFlightsByDuration(){
		int n = this.flights.size(); 
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) { 
				if (this.flights.get(j).getDurationOfFlight().getDays() > this.flights.get(j+1).getDurationOfFlight().getDays()) { 
					// swap arr[j+1] and arr[i] 
					Flight temp = this.flights.get(j); 
					this.flights.set(j, this.flights.get(j+1)); 
					this.flights.set(j+1, temp); 
				} 
			}
		}
	}
	
    public static String generateFlightNumber(int n) 
    { 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"; 
  
        StringBuilder sb = new StringBuilder(n); 
 
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() * Math.random()); 
  
            sb.append(AlphaNumericString.charAt(index)); 
        } 
        return sb.toString(); 
    }

	public boolean addFlight(Flight flight) {
		flight.setFlightNumber(generateFlightNumber(8));
		this.flights.add(flight);
		return true;
	} 
	
	@Override
	public String toString() {
		refresh();
		return this.flights.toString();
	}
	
	public boolean cancelFlight(String flightNumber) {
		for(int i = 0; i < this.flights.size(); i++) {
			if(this.flights.get(i).getFlightNumber().equals(flightNumber)) {
				this.flights.get(i).setStatus(eStatus.Canceled);
				return true;
			}
		}
		return false;
	}
	
	public boolean cancelFlight(String flightNumber, LocalDate newDate) {
		for(int i = 0; i < this.flights.size(); i++) {
			if(this.flights.get(i).getFlightNumber().equals(flightNumber)) {
				this.flights.get(i).setStatus(eStatus.Delayed);
				this.flights.get(i).setDepartureDate(newDate);
				return true;
			}
		}
		return false;
	}
	
	public void filterByFlightTypeShow(eFlightType flightType) {
		for(int i = 0; i < this.flights.size(); i++) {
			if(this.flights.get(i).getFlightType().equals(flightType))
				presentationFlights.add(this.flights.get(i));
		}
		showFlights();
	}
	
	private String showFlights() {
		StringBuffer str = new StringBuffer("Flights: \n");
		for(int i = 0; i < presentationFlights.size(); i++) {
			str.append(presentationFlights.get(i).toString() + "\n");
		}
		
		return str.toString();
	}

	private void refresh() {
		for(int i = 0; i < this.flights.size(); i++) {
			if(this.flights.get(i).getFlightDate().isAfter(LocalDate.now()))
				this.flights.remove(i);
			if(this.flights.get(i).getStatus().equals(eStatus.Canceled))
				this.flights.remove(i);
		}
	}
}
