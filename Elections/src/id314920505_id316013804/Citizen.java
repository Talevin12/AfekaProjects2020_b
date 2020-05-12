package id314920505_id316013804;

import java.util.Scanner;

public class Citizen implements Votable	{
	protected String name;
	protected String id;
	protected int birthYear;
	protected Ballot<? extends Citizen> ballot;
//	protected boolean isQuarintined;

	public Citizen(String name, String id, int birthYear, Ballot<? extends Citizen> ballot) {
		this.name = name; 
		this.id = id;
		this.birthYear = birthYear;
		this.ballot = ballot;
	}
//	public boolean setId(String id){
//		
//	}
	public Citizen(String name, String id, int birthYear) {
		this(name, id, birthYear, null);
	}

	public Citizen() throws InvalidInputException {
		this("Jesus Christ", "1", 0, null);
	}

	public Ballot<? extends Citizen> getBallot() {
		return this.ballot;
	}

	public boolean setBallot(Ballot<Citizen> ballot) {
		this.ballot = ballot;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("Name: "+ this.name +" | Id: "+ this.id +" | Birth Year: "+ this.birthYear +" | Is Quarentined: ");

		return str.toString();
	}

	@Override
	public int vote(Scanner scn, String parties, int numOfParties) {
		boolean b = false;
		int choice = 0;
		System.out.println("\nHello, "+ this.name);

		while(!b) {
			System.out.println("Choose party to vote for:");
			System.out.println("0 - Do not want to vote");
			System.out.println(parties);
			choice = scn.nextInt();

			if(choice >= 0 && choice <= numOfParties)
				b = true;
			else
				System.out.println("Wrong Input! try again");
		}
		return choice;
	}

	public int getAge(int electionYear) {
		return (electionYear-this.birthYear);
	}
	//citizens are equals if they have the same name, id and year of birth
	public boolean equals(Citizen citizen) {
		if(!this.name.equals(citizen.name))
			return false;
		else if(!this.id.equals(citizen.id))
			return false;
		else if(this.birthYear != citizen.birthYear)
			return false;
		return true;
	}
	public String getName() {
		return this.name;
	}
}
