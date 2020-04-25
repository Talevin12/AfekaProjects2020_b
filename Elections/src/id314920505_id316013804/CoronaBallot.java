package id314920505_id316013804;

import java.util.Scanner;

public class CoronaBallot extends Ballot {

	public CoronaBallot(Address address) {
		super(address);
	}

	public CoronaBallot() {
		super();
	}

	@Override
	public void letsVote(Scanner scn, String parties) {
		System.out.println("Welcome to Ballot #"+ this.id);
		int vote;
		for(int i = 0; i < super.voters.size(); i++) {
			vote = super.voters.get(i).vote(scn, parties, super.ballotResults.size());
			this.ballotResults.set(vote , this.ballotResults.get(vote) + 1);
		}
	}

	public boolean addVoter(Citizen voter, int electionYear) {
		if(!voter.isQuarintined)
			return false;
		super.voters.add(voter);
		return true;
	}
	
	public boolean equals(CoronaBallot cb) {
		return super.equals(cb);
	}

	@Override
	public String toString() {
		return "Corona "+ super.toString();
	}
}
