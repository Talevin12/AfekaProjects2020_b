package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Ballot<T extends Votable> {
	protected int id;
	protected Address address;
	protected double votePercentage;
	protected VotableSet<T> voters;
	protected ArrayList<Integer> ballotResults;
	protected Class<T> type;
	
	public Ballot(Address address, Class<T> type) {
		this.address = address;
		this.type = type;
		
		this.voters = new VotableSet<>(new ArrayList<T>(), type);
		
		this.ballotResults = new ArrayList<>(0);
		this.ballotResults.add(0);
	}
	
	public void addVoter(T voter, int electionYear) throws InvalidInputException {
		this.voters.addVoter(voter);
	}
	
	public Class<T> getType() {
		return type;
	}

	public int getId() {
		return this.id;
	}

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
		String str = "This is a "+ this.getType().getSimpleName()
				   + "Ballot id: "+ this.id +" | "
				   + "ballot address: "+ this.address.toString();
		return str;
	}
	
	public String showAllVoters() {
		StringBuffer str = new StringBuffer();
		for(Votable voter : this.voters.getVoters()) {
			str.append(voter.toString()+ "\n");
		}
		
		return str.toString();
	}

	public void letsVote(Scanner scn, String parties) {
		System.out.println("Welcome to Ballot #"+ this.id);
		int vote;
		for(Votable voter : this.voters.getVoters()) {
			System.out.println("\nHello, "+ voter.getName());
			vote = voter.vote(scn, parties, this.ballotResults.size());
			this.ballotResults.set(vote, this.ballotResults.get(vote) + 1);
		}
	}
	
	public String showBallotResult(ArrayList<Party> parties) {
		StringBuffer str = new StringBuffer("Results of ballot #"+ this.id +":\n");
		
		if(this.ballotResults.get(0) != 0)// Prevents division by zero
			str.append("Percentage of voters: "+ (double)(100-(100*((double)this.ballotResults.get(0)/(this.voters.getSize())))) +"%\n");
		else
			str.append("Percentage of votes: "+ 100 +"%\n");
		
		str.append("Abstained from voting: "+ this.ballotResults.get(0) +" citizens\n");
		
		for(int i = 1; i < this.ballotResults.size(); i++) {
				str.append(parties.get(i-1).getName() +" - "+ this.ballotResults.get(i) +" votes.\n");
		}
		
		return str.toString();
	}
	
	public boolean isExists(T citizen) {
		for(Votable voter : this.voters.getVoters()) {
			if(voter.equals(citizen))
				return true;
		}
		return false;
	}
}
