//package id314920505_id316013804;
//
//import java.time.LocalDate;
//import java.util.Scanner;
//
//public class SickCitizen extends Citizen {
//	protected LocalDate sickDate;
//	
//	public SickCitizen(String name, String id, int birthYear, Ballot ballot, LocalDate sickDate) {
//		super(name, id, birthYear, ballot);
//		this.sickDate = sickDate;
//	}
//
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
//}
