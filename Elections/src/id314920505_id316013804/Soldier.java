package id314920505_id316013804;

import java.util.Scanner;

public class Soldier extends Citizen  {
	protected boolean carryWeapon;
	
	public Soldier(String name, String id, int birthYear, Ballot<? extends Soldier> ballot) {
		super(name, id, birthYear, ballot);
	}
	
	@Override
	public int vote(Scanner scn, String parties, int numOfParties) {
		return super.vote(scn, parties, numOfParties);
	}
}
