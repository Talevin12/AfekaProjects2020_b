package Model;

import java.util.Scanner;

public class Soldier extends Citizen {
	
	public Soldier(String name, String id, int birthYear) {
		super(name, id, birthYear);
	}
	
//	@Override
//	public int vote(Scanner scan, String parties, int numOfParties) {
//		int choice = 0;
//		boolean b = false;
//
//		while(!b) {
//			System.out.println("Are you carrying your weapon? y/n");
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
//		return super.vote(scan, parties, numOfParties);
//	}
}
