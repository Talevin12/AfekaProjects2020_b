package Model;

import java.util.Scanner;

public class Citizen implements Votable	{
	protected String name;
	protected String id;
	protected int birthYear;

	public Citizen(String name, String id, int birthYear) {
		this.name = name; 
		this.id = id;
		this.birthYear = birthYear;
	}

	public Citizen() throws InvalidInputException {
		this("Jesus Christ", "1", 0);
	}

	@Override
	public String toString() {
		String str = "";
		str += "Name: "+ this.name +" | Id: "+ this.id +" | Birth Year: "+ this.birthYear;

		return str;
	}

	@Override
	public int vote(Scanner scn, String parties, int numOfParties) {
		boolean b = false;
		int choice = 0;

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
	
	public String getId() {
		return this.id;
	}
}
