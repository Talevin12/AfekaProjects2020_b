package Model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class SickPolitician extends Politician implements Sickable{
	
//	private LocalDate infectedDate;
	
	public SickPolitician(Party party, String name, String id, int birthYear, LocalDate infectedDate) {
		super(party, name, id, birthYear);
		super.sickDate = infectedDate;
	}

	@Override
	public int sicknessPeriod() {
		return super.sickDate.until(LocalDate.now()).getDays();
	}
	
//	@Override
//	public int vote(Scanner scan, String parties, int numOfParties) {
//		int choice = 0;
//		boolean b = false;
//
//		while(!b) {
//			System.out.println("Are you protected? y/n");
//			choice = scan.next().charAt(0);
//			if(choice == 'y' || choice == 'Y') 
//				b = true;
//
//			else if(choice == 'n' || choice == 'N') 
//				return 0;
//
//			else 
//				System.out.println("Wrong input! try again.");
//		}
//		
//		return super.vote(scan, parties, numOfParties);
//	}
}
