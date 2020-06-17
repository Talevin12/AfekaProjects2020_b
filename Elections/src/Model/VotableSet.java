package Model;

import java.util.ArrayList;
import java.util.List;

public class VotableSet<T extends Votable> {
	
	private Class<T> type;
	private List<T> voters;
	
	VotableSet(List<T> voters, Class<T> type) {
		this.voters = new ArrayList<T>();
		this.type = type;
	}	
	
	public Object getType() {
		return type;
	}
	
	public List<T> getVoters() {
		return voters;
	}
	
	public void addVoter(T newVoter) throws InvalidInputException {
			
		for (Votable currentVoter: voters) {
			if (currentVoter.getId().equals(newVoter.getId())) {
				throw new InvalidInputException("Voter already exists"); 
			}
		}
		
		voters.add(newVoter);
	}
	
	public int getSize() {
		return this.voters.size();
	}
	public T get(int index) {
		return this.voters.get(index);
	}
}
