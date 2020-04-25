package id314920505_id316013804;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Party {
	public static enum eFaction {Left, Center, Right};

	protected String name;
	protected eFaction faction;
	protected LocalDate establishDate;
	protected ArrayList<Politician> candidates;
//	protected int numOfCandidates = 0;
//	protected ArrayList<Integer> primeriesResults;

	public Party(String name, eFaction faction, LocalDate establishDate) {
		this.name = name;
		this.faction = faction;
		this.establishDate = establishDate;
		this.candidates = new ArrayList<>();
	}

	public Party() {
		this("Council of Scholars", eFaction.Center, LocalDate.of(0, 1, 1));
	}

	public void addCandidate(Politician candidate) {
		this.candidates.add(candidate);
	}
	
	public boolean equals(Party party) {
		if ((this.name.equals(party.name))&&(this.establishDate.equals(party.establishDate))&&(this.faction.equals(party.faction))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();

		str.append("Party name: "+ this.name +", of faction: "+ this.faction.toString() +", was established at: "+ this.establishDate +"\n");
		str.append("Our candidates: ");

		for(int i = 0; i < this.candidates.size(); i++) {
			str.append(""+ this.candidates.get(i).toString() +"\n");
		}

		return str.toString();
	}

	public String getName() {
		return this.name;
	}

	public String showAllCandidateInParty() {
		
		StringBuffer str = new StringBuffer("Our Candidates: \n");
		for (int i = 0; i < candidates.size() ; i++) {
			str.append("\t"+ (i+1) +"- "+ this.candidates.get(i).getName() +"\n");
		}
		return str.toString();
	}
	//to check if the candidate exists
	public boolean isExists(Politician candidate) {
		for(int i = 0; i < this.candidates.size(); i++) {
			if(this.candidates.get(i).equals(candidate))
				return true;
		}
		return false;
	}

	public void PrimeriesVote(Scanner scn) {
		for(int i = 0; i < this.candidates.size(); i++) {
			this.candidates.get(this.candidates.get(i).voteForPrimeries(scn, showAllCandidateInParty(), this.candidates.size())-1).voted();
		}
	}

	public void sortByPrimeriesVotes() {
		Politician temp;
		for (int i = 0; i < this.candidates.size()-1; i++) 
			for (int j = 0; j < this.candidates.size()-i-1; j++) 
				if (this.candidates.get(j).getPrimeriesVotes() > this.candidates.get(j+1).getPrimeriesVotes()) 
				{
					temp = this.candidates.get(j); 
					this.candidates.set(j, this.candidates.get(j+1)); 
					this.candidates.set(j+1, temp); 
				} 
	}
}
