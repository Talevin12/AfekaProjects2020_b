package Model;

import java.util.Scanner;

public class Politician extends Citizen implements Votable, Comparable<Politician> {
	private Party party;
	private int primeriesVotes = 0;
	
	public Politician(Party party, String name, String id, int birthYear) {
		super(name, id, birthYear);
		setParty(party);
	}

	private boolean setParty(Party party) {
		this.party = party;
		return true;
	}
	
	public Party getParty() {
		return this.party;
	}
	
	//politicians are equals just like citizens
	public boolean equals(Politician politician) {
		return super.equals(politician);
	}
	
	public int voteForPrimeries(Scanner scn, String candidates, int numOfCandidates) {
		boolean b = false;
		int choice = 0;;
		while(!b) {
			System.out.println("\nHello, "+ this.name);
			System.out.println("Choose candidate to vote for:");
			System.out.println(candidates);
			choice = scn.nextInt();
			
			if(choice >= 1 && choice <= numOfCandidates)
				b = true;
		}
		return choice;
	}
	
	public int getPrimeriesVotes() {
		return this.primeriesVotes;
	}
	
	public void voted() {
		this.primeriesVotes++;
	}

	@Override
	public int compareTo(Politician other) {
		return other.primeriesVotes - this.primeriesVotes;
	}
}
