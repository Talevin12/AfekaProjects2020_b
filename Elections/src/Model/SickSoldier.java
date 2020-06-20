package Model;

import java.time.LocalDate;

public class SickSoldier extends Soldier implements Sickable{

	public SickSoldier(String name, String id, int birthYear, LocalDate infectedDate) {
		super(name, id, birthYear);
		super.sickDate = infectedDate;
	}

	@Override
	public int sicknessPeriod() {
		return super.sickDate.until(LocalDate.now()).getDays();
	}
}
