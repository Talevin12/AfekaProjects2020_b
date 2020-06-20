package Model;

import java.util.ArrayList;

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
	
	public Class<T> getType1() {
		return type;
	}
	
	public String getType() {
		return type.getSimpleName();
	}

	public int getId() {
		return this.id;
	}
	
	public String getCity() {
		return this.address.getCity();
	}
	public String getStreet() {
		return this.address.getStreet();
	}
	public int getHouseNo() {
		return this.address.getHouseNo();
	}

	public boolean setId(int ballotIdCounter) {
		this.id = ballotIdCounter;
		return true;	
	}
	
	public ArrayList<Citizen> getVoters() {
		return (ArrayList<Citizen>)this.voters.getVoters();
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
		String str = this.getType()+ " | "
				   + "Id: "+ this.id +" | "
				   + this.address.toString();
		return str;
	}
	
	public ArrayList<Integer> getBallotResultsList() {
		return this.ballotResults;
	}
	
	public String findLeadingPartyName(ArrayList<Party> parties) {
		for(Party party : parties) {
			party.sortByPrimeriesVotes();
		}
		
		String str = parties.get(0).getName();
		if(parties.get(0).getCandidatesInParty().size()>0)
			str += "("+ parties.get(0).getCandidatesInParty().get(0).getName() +")";
		else
			str += "(Empty)";
		
		int max = 1;
		for (int i = 2; i < this.ballotResults.size(); i++) {
			if (this.ballotResults.get(i) > this.ballotResults.get(max)) {
				max = i;
					str = parties.get(i-1).getName();
					if(parties.get(i-1).getCandidatesInParty().size()>0)
						str += "("+ parties.get(i-1).getCandidatesInParty().get(0).getName() +")";
					else
						str += "(Empty)";
			}
			else if(this.ballotResults.get(i) == this.ballotResults.get(max)) {
				str += ", "+ parties.get(i-1).getName();
				if(parties.get(i-1).getCandidatesInParty().size()>0)
					str += "("+ parties.get(i-1).getCandidatesInParty().get(0).getName() +")";
				else
					str += "(Empty)";
			}
			
			if(ballotResults.get(max) == 0)
				str = "";
		}
		return str;
	}
	
	public boolean isExists(T citizen) {
		for(Votable voter : this.voters.getVoters()) {
			if(voter.equals(citizen))
				return true;
		}
		return false;
	}
}
