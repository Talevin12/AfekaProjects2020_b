package Airport;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import Airport.Flight.eStatus;

public class FlightsControl {
	private ArrayList<Flight> flights;
	ArrayList<Flight> presentationFlights = this.flights;

	public FlightsControl() {
		this.flights = new ArrayList<>();
	}
	
	public void save(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileName);
		for(Flight flight : this.flights) {
			flight.save(pw);
		}
	}
	///Sorting
	public void sortFlightsByDate(){
		int n = this.presentationFlights.size(); 
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) { 
				if (this.presentationFlights.get(j).getFlightDate().isAfter(this.presentationFlights.get(j+1).getFlightDate())) { 
					Flight temp = this.presentationFlights.get(j); 
					this.presentationFlights.set(j, this.presentationFlights.get(j+1)); 
					this.presentationFlights.set(j+1, temp); 
				} 
			}
		}
	}
	
	public void sortFlightsByStayingDuration() {
		int n = this.presentationFlights.size(); 
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) { 
				if ((this.presentationFlights.get(j).getStayingDuratuin().toDays() - this.presentationFlights.get(j+1).getStayingDuratuin().toDays()) > 0) { 
					Flight temp = this.presentationFlights.get(j); 
					this.presentationFlights.set(j, this.presentationFlights.get(j+1)); 
					this.presentationFlights.set(j+1, temp); 
				} 
			}
		}
	}

	public void sortFlightsByDurationOfflight(){
		int n = this.presentationFlights.size(); 
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) { 
				if (this.presentationFlights.get(j).getDurationOfFlight().toMinutes() > this.presentationFlights.get(j+1).getDurationOfFlight().toMinutes()) { 
					Flight temp = this.presentationFlights.get(j); 
					this.presentationFlights.set(j, this.presentationFlights.get(j+1)); 
					this.presentationFlights.set(j+1, temp); 
				} 
			}
		}
	}
	
	public void sortByStatus() {
		int n = this.presentationFlights.size(); 
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) { 
				if (this.presentationFlights.get(j).getStatus().ordinal() > this.presentationFlights.get(j+1).getStatus().ordinal()) { 
					Flight temp = this.presentationFlights.get(j); 
					this.presentationFlights.set(j, this.presentationFlights.get(j+1)); 
					this.presentationFlights.set(j+1, temp); 
				} 
			}
		}
	}
	
	///Filtering
	
	public void filterByAirLine(String airline) {
		for(int i = 0; i < this.presentationFlights.size(); i++) {
			if(this.presentationFlights.get(i).getAirline().equals(airline)) {
				this.presentationFlights.remove(i);
				i--;
			}
		}
	}
	
	public void filterByDestination(String destination) {
		for(int i = 0; i < this.presentationFlights.size(); i++) {
			if(this.presentationFlights.get(i).getDestination().equals(destination)) {
				this.presentationFlights.remove(i);
				i--;
			}
		}
	}
	public void filterByOrigin(String origin) {
		for(int i = 0; i < this.presentationFlights.size(); i++) {
			if(this.presentationFlights.get(i).getOrigin().equals(origin)) {
				this.presentationFlights.remove(i);
				i--;
			}
		}
	}
	public void filterByDepartureDate(LocalDate departureDate) {
		for(int i = 0; i < this.presentationFlights.size(); i++) {
			if(this.presentationFlights.get(i).getDepartureDate().equals(departureDate)) {
				this.presentationFlights.remove(i);
				i--;
			}
		}
	}
	public void filterByReturnDate(LocalDate returnDate) {
		for(int i = 0; i < this.presentationFlights.size(); i++) {
			if(this.presentationFlights.get(i).getReturnDate().equals(returnDate)) {
				this.presentationFlights.remove(i);
				i--;
			}
		}
	}
	public void filterByDurationOfFlight(int minHoursFlightDuration, int maxHoursFlightDuration) {
		Duration duration;
		for(int i = 0; i < this.presentationFlights.size(); i++) {
			duration = this.presentationFlights.get(i).getDurationOfFlight();
			if(((float)duration.toMinutes()/60) < minHoursFlightDuration || ((float)duration.toMinutes()/60) > maxHoursFlightDuration) {
				this.presentationFlights.remove(i);
				i--;
			}
		}
	}
	public void filterByGate(String gate) {
		for(int i = 0; i < this.presentationFlights.size(); i++) {
			if(this.presentationFlights.get(i).getGate().equals(gate)) {
				this.presentationFlights.remove(i);
				i--;
			}
		}
	}
	public void filterByStatus(eStatus status) {
		for(int i = 0; i < this.presentationFlights.size(); i++) {
			if(this.presentationFlights.get(i).getStatus().equals(status)) {
				this.presentationFlights.remove(i);
				i--;
			}
		}
	}
	
	
	
	
	
	/// arithmetic
	
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
		this.presentationFlights.add(flight);
		return true;
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
	
	private void refresh() {
		for(int i = 0; i < this.flights.size(); i++) {
			if(this.flights.get(i).getFlightDate().isAfter(LocalDate.now()))
				this.flights.remove(i);
			if(this.flights.get(i).getStatus().equals(eStatus.Canceled))
				this.flights.remove(i);
		}
	}
	
	///Displaying
	
	@Override
	public String toString() {
		refresh();
		return this.flights.toString();
	}
	
	private String showFlights() {
		StringBuffer str = new StringBuffer("Flights: \n");
		for(int i = 0; i < presentationFlights.size(); i++) {
			str.append(presentationFlights.get(i).toString() + "\n");
		}
		
		return str.toString();
	}
}
