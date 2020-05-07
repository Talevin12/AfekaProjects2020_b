package id314920505_id316013804;

import java.util.Scanner;

public class SickSoldier extends Soldier implements Votable, Sickable {

	public SickSoldier(String name, String id, int birthYear, Ballot<SickSoldier> ballot, boolean isQuarintined) {
		super(name, id, birthYear, ballot, isQuarintined);
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

}
