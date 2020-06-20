package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Party {
	public static enum eFaction {Left, Center, Right};

	protected String name;
	protected eFaction faction;
	protected LocalDate establishDate;
	protected ArrayList<Politician> candidates;

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

		str.append(this.name +" | "+ this.faction.toString() +" faction | was established at: "+ this.establishDate +"\n");
//		str.append("Our candidates: ");

//		for(int i = 0; i < this.candidates.size(); i++) {
//			str.append(""+ this.candidates.get(i).toString() +"\n");
//		}

		return str.toString();
	}

	public String getName() {
		return this.name;
	}
	
	public eFaction getFaction() {
		return this.faction;
	}
	
	public LocalDate getEstablishDate() {
		return this.establishDate;
	}

	public ArrayList<Politician> getCandidatesInParty() {
		return this.candidates;
	}
	//to check if the candidate exists
	public boolean isExists(Politician candidate) {
		for(int i = 0; i < this.candidates.size(); i++) {
			if(this.candidates.get(i).equals(candidate))
				return true;
		}
		return false;
	}

//	public void PrimeriesVote(Scanner scn) {
//		for(int i = 0; i < this.candidates.size(); i++) {
//			this.candidates.get(this.candidates.get(i).voteForPrimeries(scn, showAllCandidateInParty(), this.candidates.size())-1).voted();
//		}
//	}

	public void sortByPrimeriesVotes() {
		Collections.sort(this.candidates);
	}
}
