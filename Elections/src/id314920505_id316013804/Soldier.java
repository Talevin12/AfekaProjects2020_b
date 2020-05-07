package id314920505_id316013804;

import java.util.Scanner;

public class Soldier extends Citizen implements Votable {
	protected boolean carryWeapon;
	
	public Soldier(String name, String id, int birthYear, Ballot ballot, boolean isQuarintined) {
		super(name, id, birthYear, ballot, isQuarintined);
	}
	
	@Override
	public int vote(Scanner scn, String parties, int numOfParties) {
		return super.vote(scn, parties, numOfParties);
	}
}
