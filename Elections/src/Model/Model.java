package Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Party.eFaction;
import javafx.scene.control.Alert;

public class Model {
	private Elections elections;
	public Class<? extends Votable> ballotType;
	public Citizen citizen;
	public Ballot<? extends Votable> ballot;

	public Model() {
		this.elections = new Elections(0, 0);
	}


	public void update(int month, int year) throws InvalidInputException {
		this.elections = new Elections(month, year);
		hardCode();
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

	public <C extends Votable> boolean addCitizen(C citizen, int ballotId) throws InvalidInputException {
		return elections.addCitizen(citizen, ballotId);
	}

	public Class checkCitizenType(int birthYear, boolean isQuarintined, boolean isPolitician) {
		Class type = Citizen.class;

		int age = elections.year - birthYear;
		if(age < 18) {
			Alert alert = new Alert(Alert.AlertType.ERROR);//ERROR);//INFORMATION);
			alert.setContentText("*** This age is not eligble to vote");
			alert.show();
			return null;
		}
		boolean isSoldier = age <= 21;

		if(isPolitician && isSoldier) {
			Alert alert = new Alert(Alert.AlertType.ERROR);//ERROR);//INFORMATION);
			alert.setContentText("*** This age is not eligble to be a politician");
			alert.show();
			return null;
		}

		if(isQuarintined && isSoldier)
			type = SickSoldier.class;
		else if(isQuarintined)
			type = SickCitizen.class;
		else if(isSoldier)
			type = Soldier.class;

		return type;
	}

	public boolean checkId(String id) throws InvalidInputException {
		if (id.length() == 9) {
			for (char c : id.toCharArray()) {
				if (!Character.isDigit(c)) {
					throw new InvalidInputException("EXCEPTION: id must be digits only");
				}
			}
		}
		else {
			throw new InvalidInputException("EXCEPTION: Id length must be 9 digits");
		}
		return true;
	}

	public <C extends Votable> ArrayList<Ballot<C>> getFilteredBallots(Object type) {
		return elections.<C>getFilteredBallots(type);
	}

	public void addParty(String partyName, eFaction faction, LocalDate establishDate) {
		elections.addParty(new Party(partyName, faction, establishDate));
	}

	public <C extends Votable> void addCandidate(Politician citizen, int ballotId) throws InvalidInputException {
		elections.addCandidateToParty(citizen, ballotId);
	}

	public ArrayList<Party> getParties() {
		return elections.getParties();
	}

	public ArrayList<Ballot<? extends Votable>> getBallots() {
		return this.elections.getBallots();
	}

	public ArrayList<Citizen> getCitizens() {
		return (ArrayList<Citizen>)this.elections.getCitizens();
	}

	public boolean isElectionsStarted() {
		return this.elections.hasStarted;
	}

	public void setElectionsStarted() {
		this.elections.hasStarted = true;
	}

	public ArrayList<Integer> getResultsList() {
		elections.countVotes();
		return elections.results;
	}

	private void hardCode() throws InvalidInputException {
		Address a = new Address("Eilat", "Mosh", 69);
		Address a1 = new Address("Tel Aviv", "Shenkin", 12);
		Address a2 = new Address("Yafo", "Ha'gana", 44);
		Address a3 = new Address("Ashdod", "The City", 90);
		Address a4 = new Address("Bat Yam", "Ha'oren", 85);

		Ballot<Citizen> b1 = new Ballot<>(a1, Citizen.class);
		Ballot<Citizen> b2 = new Ballot<>(a, Citizen.class);
		Ballot<SickCitizen> sc = new Ballot<>(a2, SickCitizen.class);
		Ballot<Soldier> s = new Ballot<>(a3, Soldier.class);
		Ballot<SickSoldier> ss = new Ballot<>(a4, SickSoldier.class);

		Citizen c = new Citizen("Tal Benita", "123456789", 1996);
		Citizen c1 = new Citizen("Efrat Apacy", "676767676", 1976);
		Citizen c2 = new SickCitizen("Gabi Guetta", "111111111", 1950, LocalDate.of(2019, 12, 25));
		Citizen c3 = new Soldier("Avner Levi", "234253545", 2000);
		Citizen c4 = new Soldier("Dor Adam", "919191919", 2001);
		Citizen c5 = new SickSoldier("Shalom Koriyat", "987654321", 2000, LocalDate.of(2020, 4, 7));


		Party p = new Party("Likud", eFaction.Right, LocalDate.of(1960, 4, 24));
		Party p1 = new Party("Kahol Lavan", eFaction.Center, LocalDate.of(2019, 7, 6));
		Party p2 = new Party("Merech", eFaction.Left, LocalDate.of(1987, 9, 12));

		Politician sPoli = new SickPolitician(p, "Benjamin Netanyahu", "528369183", 1960, LocalDate.of(2017, 5, 24));
		Politician poli1 = new Politician(p1, "Benny Gantz", "638162298", 1967);
		Politician poli2 = new Politician(p2, "Nitzan Horowitz", "711426037", 1977);
		Politician poli3 = new Politician(p, "Miri Regev", "821394203", 1974);
		Politician poli4 = new Politician(p1, "Yair Lapid", "485936112", 1968);
		Politician sPoli5 = new SickPolitician(p2, "Tamar Zandberg", "639048392", 1986, LocalDate.of(2020, 5, 12));

		elections.addBallot(b1); // id = 1
		elections.addBallot(b2); // id = 2
		elections.addBallot(sc); // id = 3
		elections.addBallot(s); // id = 4
		elections.addBallot(ss); // id = 5


		elections.addCitizen(c, 1);
		elections.addCitizen(c1, 2);
		elections.addCitizen(c2, 3);
		elections.addCitizen(c3, 4);
		elections.addCitizen(c4, 4);
		elections.addCitizen(c5, 5);

		elections.addParty(p);
		elections.addParty(p1);
		elections.addParty(p2);

		elections.addCandidateToParty(sPoli, 3);
		elections.addCandidateToParty(poli1, 1);
		elections.addCandidateToParty(poli2, 2);
		elections.addCandidateToParty(poli3, 1);
		elections.addCandidateToParty(poli4, 2);
		elections.addCandidateToParty(sPoli5, 3);
	}
}
