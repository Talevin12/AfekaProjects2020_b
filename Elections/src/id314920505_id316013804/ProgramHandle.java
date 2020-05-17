package id314920505_id316013804;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import id314920505_id316013804.Party.eFaction;

public class ProgramHandle {
	static int choice = 0;

	public static boolean performAction(Elections election, Scanner scan) 
			throws InvalidInputException {
		
		switch (choice) {
		case 1:
			return addBallot(election, scan);
		case 2:
			return addCitizen(election, scan);
		case 3:
			return addParty(election, scan);
		case 4:
			return addCandidateToParty(election, scan);
		case 5:
			showAllBallots(election, scan);
			break;
		case 6:
			showAllVoters(election, scan);
			break;
		case 7:
			showAllParties(election, scan);
			break;
		case 8:
			return startElections(election, scan);
		case 9:
			showElectionsResults(election, scan);
			break;
		case 10:
			System.out.println("Have a good day!");
			return false;
		default:
			System.out.println("Try again wrong input");
			return false;

		}
		return true;

	}

	public static void showElectionsResults(Elections election, Scanner scan) {
		if (!election.hasStarted)
			System.out.println("Elections hasn't started yet \n");
		else
			System.out.println(election.showElectionsResults());
		// showMenu(election, scan);

	}

	public static boolean startElections(Elections election, Scanner scan) {
		if (election.hasStarted) {
			System.out.println("Elctions already started \n");
			return false;
		}
		election.startElections();

		// showMenu(election, scan);
		return true;
	}

	public static void showAllParties(Elections election, Scanner scan) {
		System.out.println(election.showAllParties());

		if (election.hasStarted)
			System.out.println("***The parties's list of candidates are listed by primeries");
		else
			System.out.println("**Elections hadn't started yet. The parties's list of candidates are listed randomly");
	}

	public static void showAllVoters(Elections election, Scanner scan) {
		System.out.println(election.showAllVoters());
	}

	public static void showAllBallots(Elections election, Scanner scan) {
		System.out.println(election.showAllBallots());
	}

	public static boolean addCandidateToParty(Elections election, Scanner scan) throws InvalidInputException {
		if (election.hasStarted) {
			System.out.println("Elections ended, to see results type 9.");
			return false;
		}
		boolean b = false;
		String id = "";

		System.out.println("Please enter the citizen name (first name and last name): ");
		String name = scan.next() + " " + scan.next();

		while (!b) {
			System.out.println("Please enter the citizen id: ");
			id = scan.next();

			if (checkId(id))
				b = true;
		}
		int yearBirth = LocalDate.now().getYear();
		int age = LocalDate.now().getYear() - yearBirth;
		boolean isSoldier = false;
		System.out.println("Please enter the citizen year of birth: ");
		yearBirth = scan.nextInt();
		try {
			// if(yearBirth > election.year)
			// System.out.println("Dude, you were wont (??) born in the future");

			if (age < 18)
				throw new InvalidInputException("This age is not eligble to vote");
			else if(age <= 21)
				isSoldier = true;
				
		} catch (InvalidInputException e) {
			System.out.println(e.getMsg());
			return false;
		}

		System.out.println("Please enter if the citizen Quarintined (true,false): ");
		boolean isQuarintined = scan.nextBoolean();
		
		Object type = Citizen.class;
		
		if(isQuarintined && isSoldier)
			type = SickSoldier.class;
		else if(isQuarintined)
			type = SickCitizen.class;
		else if(isSoldier)
			type = Soldier.class;

		System.out.println("Please choose a ballot by id: ");
		System.out.println(election.showFilteredBallots(type));
		int idChoice = scan.nextInt();

		System.out.println("Please choose a party: ");
		System.out.println(election.showAllParties());
		int choice = scan.nextInt();
		ArrayList<Party> parties = election.getParties();
		Party party = parties.get(choice - 1);

		Politician candidate = new Politician(party, name, id, yearBirth);
		
		boolean isAddCitizenSuccessful = false;
		
		try {
			 isAddCitizenSuccessful = election.addCandidateToParty(party, candidate, idChoice);
		} catch (InvalidInputException e) {
			System.out.println("A citizen with same ID already exists");
		}
		
		 return isAddCitizenSuccessful;
	}

	public static boolean addParty(Elections election, Scanner scan) {
		if (election.hasStarted) {
			System.out.println("Elections ended, to see results type 9.");
			return false;
		}

		LocalDate establishDate = LocalDate.now();
		boolean b = false;
		int choice = 0;
		eFaction faction = eFaction.Center;

		System.out.println("Please enter the party name: ");
		String name = scan.next();

		while (!b) {
			System.out.println("Please enter the faction of the party: ");
			System.out.println("1 - " + eFaction.Left.toString() + "\n" + "2 - " + eFaction.Center.toString() + "\n"
					+ "3 - " + eFaction.Right.toString() + "\n");

			choice = scan.nextInt();
			switch (choice) {
			case 1:
				faction = eFaction.Left;
				b = true;
				break;
			case 2:
				faction = eFaction.Center;
				b = true;
				break;
			case 3:
				faction = eFaction.Right;
				b = true;
				break;
			default:
				System.out.println("Wrong input! try again");
			}
		}

		b = false;

		while (!b) {
			System.out.println("Please enter the establish Date of the party: ");
			System.out.println("year: ");
			int year = scan.nextInt();
			System.out.println("month: ");
			int month = scan.nextInt();
			System.out.println("day: ");
			int day = scan.nextInt();

			if (!checkDate(year, month)) {
				establishDate = LocalDate.of(year, month, day);
				b = true;
			} else
				System.out.println("Wrong input. The date is invalid or it isn't accured yet.");
		}

		Party party = new Party(name, faction, establishDate);

		election.addParty(party);
		return true;
	}

	public static boolean addCitizen(Elections election, Scanner scan) 
			 {
		
		if (election.hasStarted) {
			System.out.println("Elections ended, to see results type 9.");
			return false;
		}

		boolean b = false;

		System.out.println("Please enter the citizen name (first name and last name): ");
		String name = scan.next() + " " + scan.next();

		String id = "";

		while (!b) {
			System.out.println("Please enter the citizen id: ");
			id = scan.next();
			if (checkId(id))
				b = true;
		}

		int yearBirth = LocalDate.now().getYear();
		System.out.println("Please enter the citizen year of birth: ");
		yearBirth = scan.nextInt();
		boolean isSoldier = false;
		try {
			// if(yearBirth > election.year)
			// System.out.println("Dude, you were wont (??) born in the future");
			
			int age = (LocalDate.now().getYear() - yearBirth);
			if (age < 18)
				throw new InvalidInputException("This age is not eligble to vote");
			else if(age <= 21) {
				isSoldier = true;
			}
		} catch (InvalidInputException e) {
			System.out.println(e.getMsg());
			return false;
		}

		System.out.println("Please enter if the citizen Quarintined (true,false): ");
		boolean isQuarintined = scan.nextBoolean();

		Object type = Citizen.class;
		
		if(isQuarintined && isSoldier)
			type = SickSoldier.class;
		else if(isQuarintined)
			type = SickCitizen.class;
		else if(isSoldier)
			type = Soldier.class;
		
		System.out.println("Please choose a ballot by id: ");
		System.out.println(election.showFilteredBallots(type));
		int idChoice = scan.nextInt();

		Citizen citizen = new Citizen(name, id, yearBirth);
		
		boolean isAddCitizenSuccessful = false;
		
		try {
			 isAddCitizenSuccessful = election.addCitizen(citizen, idChoice);
		} catch (InvalidInputException e) {
			System.out.println("A citizen with same ID already exists");
		}
		
		 return isAddCitizenSuccessful;
	}
	

	private static boolean checkId(String id) {
		try {
			if (id.length() == 9) {
				for (char c : id.toCharArray()) {
					if (!Character.isDigit(c)) {
						throw new InvalidInputException("EXCEPTION: id must be digits only");
					}
				}
			} else {
				throw new InvalidInputException("EXCEPTION: Id length must be 9 digits");
			}
			return true;
		} catch (InvalidInputException e) {
			System.out.println(e.getMsg());
			return false;
		}
	}

	public static boolean addBallot(Elections election, Scanner scan) {
		if(election.hasStarted) {
			System.out.println("Elections ended, to see results type 9.");
			return false;
		}

		System.out.println("What kind of a ballot would you like to make?");
		System.out.println("0: Back / Cancel");
		System.out.println("1: Regular ballot");  
		System.out.println("2: Corona ballot");
		System.out.println("3: Army ballot");
		System.out.println("4: Corona Army ballot");

		choice = scan.nextInt();

		switch (choice) {
		case 0:
			return false;
		case 1:
			election.addBallot(new Ballot<Citizen>(getLocationFromUser(scan), Citizen.class));
			addBallot(election, scan);
			break;
		case 2:
			election.addBallot(new Ballot<SickCitizen>(getLocationFromUser(scan), SickCitizen.class));
			addBallot(election, scan);
			break;
		case 3:
//			Ballot<Votable> temp = new Ballot<Soldier>();
			election.addBallot(new Ballot<Soldier>(getLocationFromUser(scan), Soldier.class));
			addBallot(election, scan);
			break;
		case 4:
			election.addBallot(new Ballot<SickSoldier>(getLocationFromUser(scan), SickSoldier.class));
			addBallot(election, scan);
			break;
		default:
			addBallot(election, scan);
		}		
		return true;
	}

	public static Address getLocationFromUser(Scanner scan) {
		System.out.println("Enter location details: (Ciy, street, house number)");
		Address location = new Address(scan.next(), scan.next(), scan.nextInt());
		return location;
	}

	public static void showMenu(Elections election, Scanner scan) throws InvalidInputException {
		System.out.println("\nMENU: please enter the number of the desired action:");
		System.out.println("1: Add a Ballot");
		System.out.println("2: Add a Citizen");
		System.out.println("3: Add a Party");
		System.out.println("4: Add a Candidate to a party (create a new Voter that is a Candidate)");
		System.out.println("5: Show all Ballot");
		System.out.println("6: Show all Citizen");
		System.out.println("7: Show all Parties");
		System.out.println("8: START THE ELECTION");
		System.out.println("9: Show the results");
		System.out.println("10: EXIT");

		choice = scan.nextInt();
		if (choice <= 10 && choice >= 1) {
			if (performAction(election, scan))
				System.out.println("Action performed successfuly!!");

			else
				System.out.println("No action performed");

		}
	}

	public static void startMain() throws InvalidInputException {
		Scanner scan = new Scanner(System.in);
		int year = 0;
		int month = 0;

		System.out.println("--------------------HELLO AND WELCOME--------------------");
		System.out.println("--------------You Enter To ElectionsApp 2.0--------------");
		System.out.println("Lets get started!");
		boolean b = false;
		while (!b) {
			System.out.println("First, enter the year of the up coming election: ");
			year = scan.nextInt();
			System.out.println("And now, enter the month of the up coming election: ");
			month = scan.nextInt();
			if (checkDate(year, month))
				b = true;
			else
				System.out.println("Wrong input. The date is invalid or it is passed.");
		}
		Elections election = new Elections(month, year);
		// hard coded
		Address a = new Address("Eilat", "Mosh", 69);
		Address a1 = new Address("Tel Aviv", "Shenkin", 12);
		Address a2 = new Address("Yafo", "Hagana", 44);
		Address a3 = new Address("Ashdod", "The City", 90);

		Ballot<Citizen> b1 = new Ballot<>(a1, Citizen.class);
		Ballot<Citizen> b2 = new Ballot<>(a, Citizen.class);
		Ballot<SickCitizen> cb = new Ballot<>(a2, SickCitizen.class);
		Ballot<Soldier> ab = new Ballot<>(a3, Soldier.class);

		Citizen c = null;
		Citizen c1 = null;
		Citizen c2 = null;
		Citizen c3 = null;
		Citizen c4 = null;
		Citizen c5 = null;
		c = new Soldier("Avner Levi", "234253545", 2000);
		c1 = new Citizen("Tal Benita", "123456789", 1996);
		c2 = new Citizen("Shalom Koriyat", "987654321", 2000);
		c3 = new Citizen("Efrat Apacy", "676767676", 1976);
		c4 = new Citizen("Gabi Guetta", "111111111", 1950);
		c5 = new Citizen("Dor Adam", "919191919", 1987);

		Party p = new Party("Likud", eFaction.Right, LocalDate.of(1960, 4, 24));
		Party p1 = new Party("Kahol Lavan", eFaction.Center, LocalDate.of(2019, 7, 6));
		Party p2 = new Party("Merech", eFaction.Left, LocalDate.of(1987, 9, 12));

		Politician poli = null;
		Politician poli1 = null;
		Politician poli2 = null;
		Politician poli3 = null;
		Politician poli4 = null;
		Politician poli5 = null;
		poli = new Politician(p, "Benjamin Netanyahu", "528369183", 1960);
		poli1 = new Politician(p1, "Benny Gantz", "638162298", 1967);
		poli2 = new Politician(p2, "Nitzan Horowitz", "711426037", 1977);
		poli3 = new Politician(p, "Miri Regev", "821394203", 1974);
		poli4 = new Politician(p1, "Yair Lapid", "485936112", 1968);
		poli5 = new Politician(p2, "Tamar Zandberg", "639048392", 1986);

		election.addBallot(b1);
		election.addBallot(b2);
		election.addBallot(cb);
		election.addBallot(ab);

//		election.addCitizen(c);
//		election.addCitizen(c1);
//		election.addCitizen(c2);
//		election.addCitizen(c3);
//		election.addCitizen(c4);
//		election.addCitizen(c5);
//
//		election.addParty(p);
//		election.addParty(p1);
//		election.addParty(p2);
//
//		election.addCandidateToParty(p, poli);
//		election.addCandidateToParty(p1, poli1);
//		election.addCandidateToParty(p2, poli2);
//		election.addCandidateToParty(p, poli3);
//		election.addCandidateToParty(p1, poli4);
//		election.addCandidateToParty(p2, poli5);

		while (choice != 10) {
			showMenu(election, scan);
		}

		scan.close();
	}

	public static boolean checkDate(int year, int month) { // check if date is from now on
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

	public static boolean checkDate(int year, int month, int day) { // check if date is from now on
		int currentYear = LocalDate.now().getYear();
		int currentMonth = LocalDate.now().getMonthValue();
		int currentDay = LocalDate.now().getDayOfMonth();

		if (day < 1 || day > 31)
			return false;
		if (month < 1 || month > 12)
			return false;
		if (year > currentYear)
			return false;
		if (year == currentYear && month > currentMonth)
			return false;
		if (year == currentYear && month == currentMonth && day > currentDay)
			return false;
		return true;
	}
}
