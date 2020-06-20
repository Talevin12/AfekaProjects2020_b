package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elections {
	protected int month;
	protected int year;
	//	protected Ballot[] ballots;

	// All citizens 
	protected VotableSet<Citizen> allVotables;

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


	protected ArrayList<Party> parties;
	protected int ballotIdCounter = 1;
	protected ArrayList<Integer> results;
	protected boolean hasStarted = false;


	public Elections(int month, int year) {
		this.year = year;
		this.month = month;
		this.allVotables = new VotableSet<>(new ArrayList<Citizen>(), Citizen.class);
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
		ballot.ballotResults = new ArrayList<>(Collections.nCopies(this.results.size(), 0));
		this.ballots.add(ballot);
		this.ballotIdCounter++;
	}

	@SuppressWarnings("unchecked")
	private <C extends Votable>  List<Ballot<C>> getBallotsByType(Class<C> clazz) {
		List<Ballot<C>> ballotsOfTypeCResults = new ArrayList<Ballot<C>>();

		for(Ballot<? extends Votable> ballot : this.ballots) {
			if (ballot.getType1() == clazz)
				ballotsOfTypeCResults.add((Ballot<C>)ballot);
		}

		return ballotsOfTypeCResults;
	}

	@SuppressWarnings("unchecked")
	public <C extends Votable> boolean addCitizen(C citizen, int ballotId) throws InvalidInputException {
		Class<C> type = (Class<C>) citizen.getClass();
		if(type == Politician.class)
			type = (Class<C>) Citizen.class;
		else if(type == SickPolitician.class)
			type = (Class<C>) SickCitizen.class;
		Ballot<C> tempBallot = this.<C>findBallotById(ballotId, type);

		if(tempBallot != null) {
			this.allVotables.addVoter((Citizen) citizen);
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
	public boolean addCandidateToParty(Politician candidate, int ballotId) throws InvalidInputException {
		boolean b;
		b = this.<Citizen>addCitizen(candidate, ballotId);
		if(b) {
			candidate.getParty().addCandidate(candidate);
		}
		return b;
	}

	public ArrayList<Ballot<? extends Votable>> getBallots() {
		return this.ballots;
	}

	@SuppressWarnings("unchecked")
	public <C extends Votable> ArrayList<Ballot<C>> getFilteredBallots(Object type) {
		ArrayList<Ballot<C>> ballots = new ArrayList<Ballot<C>>();

		for(Ballot<? extends Votable> ballot : this.ballots) {
			if(ballot.getType1() == type)
				ballots.add((Ballot<C>) ballot);
		}

		return ballots;
	}

	public boolean checkBallotInput(int ballotId, Object type) {
		if(ballotId > this.ballots.size() || ballotId <= 0)
			return false;
		if(this.ballots.get(ballotId-1).getType1() == type)
			return true;
		return false;
	}

	public void countVotes() {
		for(int i = 0; i < this.ballots.size(); i++) {
			ArrayList<Integer> ballotResult = this.ballots.get(i).getBallotResultsList();
			for(int j = 0; j < this.parties.size()+1; j++) {
				this.results.set(j,  this.results.get(j) + ballotResult.get(j));
			}
		}
	}

	public ArrayList<Party> getParties() {
		return this.parties;
	}

	public List<Citizen> getCitizens(){
		return this.allVotables.getVoters();
	}
}
