package Model;

import java.time.LocalDate;

public class SickCitizen extends Citizen implements Sickable {
	
	public SickCitizen(String name, String id, int birthYear, LocalDate sickDate) {
		super(name, id, birthYear);
		super.sickDate = sickDate;	
	}

	@Override
	public int sicknessPeriod() {
		return this.sickDate.until(LocalDate.now()).getDays();
	}
}
