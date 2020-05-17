package id314920505_id316013804;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Elections {
	protected int month;
	protected int year;
//	protected Ballot[] ballots;
	
	// All citizens 
	protected VotableSet<? extends Votable> allVotables;

	// Choose
	// OR 
	// All ballots types in same list
	protected ArrayList<Ballot<? extends Votable>> ballots;	
	
	// OR
	// Separate ballots for each type
//	protected ArrayList<Ballot<Citizen>> citizenBallots;	
//	protected ArrayList<Ballot<SickCitizen>> sickCitizenBallots;	
//	protected ArrayList<Ballot<Soldier>> soldierBallots;	
//	protected ArrayList<Ballot<SickSoldier>> sickSoldierBallots;	
	
	
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
		this.results = new ArrayList<>();
		this.results.add(0);
	}
	
	private <C extends Votable> Ballot<C> findBallotById(int id, Class<C> clazz) {
		
		List<Ballot<C>> cBallots = this.<C>getBallotsByType(clazz);		
		for(Ballot<C> ballot : cBallots) {
			if(ballot.getId() == id)
				return ballot;
		}
		return null;	
	}


	public void addBallot(Ballot<? extends Votable> ballot) {
		ballot.setId(this.ballotIdCounter);
		this.ballots.add(ballot);
		this.ballotIdCounter++;
	}
	
	@SuppressWarnings("unchecked")
	private <C extends Votable>  List<Ballot<C>> getBallotsByType(Class<C> clazz) {
		List<Ballot<C>> ballotsOfTypeCResults = new ArrayList<Ballot<C>>();
		
		for(Ballot<? extends Votable> b : this.ballots) {
			if (b.getType() == clazz)
				ballotsOfTypeCResults.add((Ballot<C>)b);
		}
		
		return ballotsOfTypeCResults;
	}

	public <C extends Votable> boolean addCitizen(C citizen, int ballotId) throws InvalidInputException {
		@SuppressWarnings("unchecked")
		Ballot<C> tempBallot = this.<C>findBallotById(ballotId, (Class<C>)citizen.getClass());
		if(tempBallot != null) {
			tempBallot.addVoter(citizen, this.year);
			return true;
		}
		return false;

	}

	public void addParty(Party party) {
		this.parties.add(party);
		this.results.add(0);
		increaseBallotsResultList();
	}
	
	private void increaseBallotsResultList() {
		for(Ballot<? extends Votable> b : this.ballots) {
			b.ballotResults.add(0);
		}
		
	}
	public boolean addCandidateToParty(Party party, Politician candidate, int ballotId) throws InvalidInputException {
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
			addCitizen(candidate, ballotId);
			party.addCandidate(candidate);
		}
		return b;
	}

	public String showAllBallots() {
		StringBuffer str = new StringBuffer();

		for(Ballot<? extends Votable> ballot: this.ballots) {
			str.append(ballot.toString()+ "\n");
		}
		return str.toString();
	}
	
	public String showFilteredBallots(Object type) {
		StringBuffer str = new StringBuffer();
		
		for(Ballot<? extends Votable> ballot : this.ballots) {
			if(ballot.getType() == type)
				str.append(ballot.toString() +"\n");
		}

		return str.toString();
	}

	public String showAllVoters() {

		StringBuffer str = new StringBuffer();
		for(int i = 0; i < this.ballots.size(); i++) {
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

	/// Private ////
	
	/// Public ///

	/// Variables ///
	
	
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
//	//to avoid creating the same citizen
//	private boolean isCitizenExists(Citizen voter) {
//		for(int i = 0; i < this.ballotIdCounter-1; i++) {
//			if(this.ballots.get(i).isExists(voter))
//				return true;
//		}
//		return false;
//	}

	public ArrayList<Party> getParties() {
		return this.parties;
	}
	
	
//	// If we have one list for all ballots 
//	public List<Ballot<? extends Votable>> getRightBallot(Votable voter) {		
//		
//		List<Ballot<? extends Votable>> foundBallots = new ArrayList<>();		
//		
//		for (Ballot<? extends Votable> avilableBallot : ballots) {
//			if (voter.getClass() == avilableBallot.getType()) {
//				foundBallots.add(avilableBallot);
//			}
//		}
//		
//		return foundBallots;
//	}
//	
//	// If we have one list for each ballot type 
//	public ArrayList<Ballot<? extends Votable>> getRightBallot2(Votable voter) {		
//
//		if (voter.getClass() == Soldier.class) {
//			return soldierBallots;
//		} else if (voter.getClass() == SickSoldier.class) {
//			return sickSoldierBallots;
//		} else if (voter.getClass() == Citizen.class) {
//			return citizenBallots;
//		} else if (voter.getClass() == SickCitizen.class) {
//			return sickCitizenBallots;
//		}
//	}


		
	
	
}
