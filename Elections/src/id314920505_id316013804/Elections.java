package id314920505_id316013804;
import java.util.ArrayList;
import java.util.Scanner;

public class Elections {
	protected int month;
	protected int year;
//	protected Ballot[] ballots;
	protected ArrayList<Ballot<Votable>> ballots;
//	protected Party[] parties;
	protected ArrayList<Party> parties;
	protected int ballotIdCounter = 1;
//	protected int partiesCounter = 0;
	protected ArrayList<Integer> results;
	protected boolean hasStarted = false;


	public Elections(int month, int year) {
		this.year = year;
		this.month = month;
		this.ballots = new ArrayList<>();
		this.parties = new ArrayList<>();
		this.results = new ArrayList<>(0);
		this.results.add(0);
	}
	public Ballot<Votable> findBallotById(int id) {
//		for(int i = 0; i < this.ballotIdCounter; i++) {
//			if(id == this.ballots[i].getId()) {
//				return this.ballots[i];
//			}
//		}
//		return null;
		
		//for now we don't have remove ballot so this is more efficient
		if(id <= this.ballotIdCounter || id > 0)
			return this.ballots.get(id-1);
		return null;
	}


	public void addBallot(Ballot<Votable> ballot) {
		ballot.setId(this.ballotIdCounter);
		this.ballots.add(ballot);
		this.ballotIdCounter++;
	}

	public boolean addCitizen(Citizen citizen) {
		if(isCitizenExists(citizen))
			return false;
		
		@SuppressWarnings("unchecked")
		Ballot<Citizen> tempBallot = (Ballot<Citizen>) citizen.getBallot();
		if(tempBallot != null) {
			return tempBallot.addVoter(citizen, this.year);
		}
		return false;


	}

	public void addParty(Party party) {
		this.parties.add(party);
		this.results.add(0);
		increaseBallotsResultList();
	}
	
	private void increaseBallotsResultList() {
		for(Ballot<Votable> b : this.ballots) {
			b.ballotResults.add(0);
		}
		
	}
	public boolean addCandidateToParty(Party party, Politician candidate) {
		if(isCandidateExists(candidate))
			return false;
		
		boolean b = false;
		int i = 0;
		while(!b && i <= this.parties.size()) {
			if(this.parties.get(i) == party)
				b = true;
			i++;
		}
		if(b) {
			addCitizen(candidate);
			party.addCandidate(candidate);
		}
		return b;
	}

	public String showAllBallots() {
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < this.ballotIdCounter-1; i++) {
			str.append(this.ballots.get(i).toString()+ "\n");
		}
		return str.toString();
	}

	public String showAllVoters() {

		StringBuffer str = new StringBuffer();
		for(int i = 0; i < this.ballotIdCounter-1; i++) {
			str.append("In ballot #"+ (i+1) +": \n");
			str.append(this.ballots.get(i).showAllVoters());
			
		}

		return str.toString();
	}

	public void startElections() {
		Scanner scn = new Scanner(System.in);
		System.out.println("Let the elactions Begin!");
		
		for(int i = 0; i < this.parties.size(); i++) {
			this.parties.get(i).PrimeriesVote(scn);
			this.parties.get(i).sortByPrimeriesVotes();
		}
		
		for(int i = 0; i < this.ballotIdCounter-1; i++) {
//			this.ballots.get(i).setBallotesResults(this.parties.size()+1);
			this.ballots.get(i).letsVote(scn, showAllParties());
		}

		this.hasStarted = true;
	}

	public String showAllParties() {
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < this.parties.size(); i++) {
			str.append((i+1) +" - "+ this.parties.get(i).getName() +", faction of "+this.parties.get(i).faction+ ", was astablish at "+this.parties.get(i).establishDate+"\n");
			str.append(parties.get(i).showAllCandidateInParty());
		}

		return str.toString();
	}

	private void countVotes() {
//		this.results = new ArrayList<Integer>(this.parties.size()+1);
		for(int i = 0; i < this.ballotIdCounter-1; i++) {
			for(int j = 0; j < this.parties.size()+1; j++) {
				this.results.set(j,  this.results.get(j) + (int)this.ballots.get(i).ballotResults.get(j));
			}
		}
	}

	public String showElectionsResults() {

		StringBuffer str = new StringBuffer("\nHere are the "+ this.month +"/"+ this.year +" elections results: \n");

		for(int i = 0; i < this.ballotIdCounter-1; i++) {
			str.append(this.ballots.get(i).showBallotResult(this.parties) +"\n");
		}

		str.append("Results summary: \n");
		countVotes();
		str.append("Abstained from voting: "+ this.results.get(0) +" citizens\n");

		for(int i = 1; i <= this.parties.size(); i++) {
			str.append(this.parties.get(i-1).getName() +"- "+ this.results.get(0) +" votes. \n");
		}
		
		str.append("The leading party for votes in the elections are: "+ findLeadingPartyName() +"\n");

		str.append("And now forming a coalition.... wait... Something went wrong /: .... See you again in 3 months! \n");

		return str.toString();
	}
	private String findLeadingPartyName() {
		String str = this.parties.get(0).getName();
		int max = 1;
	    for (int i = 2; i < this.results.size(); i++) {
	        if (this.results.get(i) > this.results.get(max)) {
	            max = i;
	            str = this.parties.get(i-1).getName(); 
	        }
	        else if(this.results.get(i) == this.results.get(max)) {
	        	str += ", "+ this.parties.get(i-1).getName();
	        }
	    }
	    return str;
	}

	//to avoid creating the same candidate
	private boolean isCandidateExists(Politician candidate) {
		for(int i = 0; i < this.parties.size(); i++) {
			if(this.parties.get(i).isExists(candidate))
				return true;
		}
		return false;
	}
	//to avoid creating the same citizen
	private boolean isCitizenExists(Citizen voter) {
		for(int i = 0; i < this.ballotIdCounter-1; i++) {
			if(this.ballots.get(i).isExists(voter))
				return true;
		}
		return false;
	}

	public ArrayList<Party> getParties() {
		return this.parties;
	}
}
