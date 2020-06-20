package Model;

import java.time.LocalDate;

public class SickPolitician extends Politician implements Sickable{
	
	public SickPolitician(Party party, String name, String id, int birthYear, LocalDate infectedDate) {
		super(party, name, id, birthYear);
		super.sickDate = infectedDate;
	}

	@Override
	public int sicknessPeriod() {
		return super.sickDate.until(LocalDate.now()).getDays();
	}
}
