package Model;
import id314920505_id316013804.Elections;
	
import javafx.scene.Group;

public class Model {
	private Elections elections;
	
	public Model() {
		this.elections = new Elections(0, 0);
	}
	
	
	public void update(int month, int year) {
		this.elections = new Elections(month, year);
	}
	
	
}
