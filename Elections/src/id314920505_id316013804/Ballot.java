package id314920505_id316013804;

import java.util.ArrayList;
import java.util.Scanner;

// testing
public class Ballot<T extends Votable> {
	protected int id;
	protected Address address;
	protected double votePercentage;
	protected ArrayList<Citizen>/*<T>*/ voters;
//	protected int numOfVoters = 0;
	protected ArrayList<Integer> ballotResults;
	
	public Ballot(Address address) {
		this.address = address;
		this.voters = new ArrayList<>();
		this.ballotResults = new ArrayList<>(0);
		this.ballotResults.add(0);
	}
	
	public Ballot() {
		this(new Address());
	}
	
	public boolean addVoter(/*T*/Citizen voter, int electionYear) {
		if(voter.isQuarintined)
			return false;
		if(isSoldier(voter, electionYear))
			return false;
		this.voters.add(voter);

		return true;
	}
	
	public boolean isSoldier(Citizen voter, int electionsYear) {
		int age = voter.getAge(electionsYear);
		return (age >= 18 && age <= 21);
	}

	public int getId() {
		return this.id;
	}
	
//	public boolean setBallotesResults(int numOfParties) {
//		this.ballotResults = new ArrayList<>(numOfParties);
//		return true;
//	}

	public boolean setId(int ballotIdCounter) {
		this.id = ballotIdCounter;
		return true;	
	}
	
	public boolean equals(Ballot<T> ballot) {
		if ((this.id==ballot.id)&&(this.address.equals(ballot.address))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String str = "Ballot id: "+ this.id +" | "
				   + "ballot address: "+ this.address.toString();
		return str;
	}
	
	public String showAllVoters() {
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < this.voters.size(); i++) {
			str.append(this.voters.get(i).toString()+ "\n");
		}
		
		return str.toString();
	}

	public void letsVote(Scanner scn, String parties) {
		System.out.println("Welcome to Ballot #"+ this.id);
		int vote;
		for(int i = 0; i < this.voters.size(); i++) {
			vote = this.voters.get(i).vote(scn, parties, this.ballotResults.size());
			this.ballotResults.set(vote, this.ballotResults.get(vote) + 1);
		}
	}
	
	public String showBallotResult(ArrayList<Party> parties) {
		StringBuffer str = new StringBuffer("Results of ballot #"+ this.id +":\n");
		
		if(this.ballotResults.get(0) != 0)// Prevents division by zero
			str.append("Percentage of voters: "+ (double)(100-(100*((double)this.ballotResults.get(0)/(this.voters.size())))) +"%\n");
		else
			str.append("Percentage of votes: "+ 100 +"%\n");
		
		str.append("Abstained from voting: "+ this.ballotResults.get(0) +" citizens\n");
		
		for(int i = 1; i < this.ballotResults.size(); i++) {
			if(parties.get(i-1) == null)
				break;
			else {
				str.append(parties.get(i-1).getName() +" - "+ this.ballotResults.get(0) +" votes.\n");
			}
		}
		
		return str.toString();
	}
	
	public boolean isExists(Citizen candidate) {
		for(int i = 0; i < this.voters.size(); i++) {
			if(this.voters.get(i).equals(candidate))
				return true;
		}
		return false;
	}
}
