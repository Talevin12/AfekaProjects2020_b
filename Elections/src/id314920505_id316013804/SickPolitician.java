package id314920505_id316013804;

import java.time.LocalDate;
import java.time.Period;

public class SickPolitician extends Politician implements Sickable{
	
	private LocalDate infectedDate;
	
	public SickPolitician(Party party, String name, String id, int birthYear, LocalDate infectedDate) {
		super(party, name, id, birthYear);
		this.infectedDate = infectedDate;
	}

	@Override
	public Period sicknessPeriod(LocalDate electionsDate) {
		return this.infectedDate.until(electionsDate);
	}



}
