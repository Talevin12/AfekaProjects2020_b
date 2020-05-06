package id314920505_id316013804;

import java.util.Scanner;

public class Soldier extends Citizen {
	protected boolean carryWeapon;
	
	public Soldier() throws IdException {
		super();
	}
	
	public int vote(Scanner scn, String parties, int numOfParties) {
		return super.vote(scn, parties, numOfParties);
	}
}
