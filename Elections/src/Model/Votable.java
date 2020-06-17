package Model;

import java.util.Scanner;

public interface Votable {
	
	public int vote(Scanner scn, String parties, int numOfParties);	
	
	String getId();

	public String getName();
}
