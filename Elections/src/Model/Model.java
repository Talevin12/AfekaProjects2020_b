package Model;
import java.time.LocalDate;

import javafx.scene.Group;

public class Model {
	private Elections elections;
	public Class<? extends Votable> ballotType;
	
	public Model() {
		this.elections = new Elections(0, 0);
	}
	
	
	public void update(int month, int year) {
		this.elections = new Elections(month, year);
	}
	
	public boolean checkElectionsDate(int year, int month) {
		int currentYear = LocalDate.now().getYear();
		int currentMonth = LocalDate.now().getMonthValue();

		if (month < 1 || month > 12)
			return false;
		if (year < currentYear)
			return false;
		if (year == currentYear && month < currentMonth)
			return false;
		return true;
	}
	
	public void updateBallotType(Class<? extends Votable> type) {
		this.ballotType = type;
	}
	
	public void addBallot(Address address) {
		if(ballotType == Citizen.class)
			elections.addBallot(new Ballot<Citizen>(address, Citizen.class));
		else if(ballotType == SickCitizen.class)
			elections.addBallot(new Ballot<SickCitizen>(address, SickCitizen.class));
		else if(ballotType == Soldier.class)
			elections.addBallot(new Ballot<Soldier>(address, Soldier.class));
		else
			elections.addBallot(new Ballot<SickSoldier>(address, SickSoldier.class));
	}
}
