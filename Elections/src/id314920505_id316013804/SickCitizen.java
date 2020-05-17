package id314920505_id316013804;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class SickCitizen extends Citizen implements Sickable {
	protected LocalDate sickDate;
	private LocalDate infectedDate;
	
	public SickCitizen(String name, String id, int birthYear, LocalDate sickDate) {
		super(name, id, birthYear);
		this.sickDate = sickDate;	
	}

	@Override
	public int vote(Scanner scan, String parties, int numOfParties) {
		int choice = 0;
		boolean b = false;

		while(!b) {
			System.out.println("Are you protected? y/n");
			choice = scan.next().charAt(0);
			if(choice == 'y' || choice == 'Y') 
				b = true;

			else if(choice == 'n' || choice == 'N') 
				return 0;

			else 
				System.out.println("Wrong input! try again.");
		}
		
		return super.vote(scan, parties, numOfParties);
	}

	@Override
	public Period sicknessPeriod(LocalDate electionsDate) {
		return this.infectedDate.until(electionsDate);
	}
}
