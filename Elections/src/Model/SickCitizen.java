package Model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SickCitizen extends Citizen implements Sickable {
//	protected LocalDate sickDate;
	
	public SickCitizen(String name, String id, int birthYear, LocalDate sickDate) {
		super(name, id, birthYear);
		super.sickDate = sickDate;	
	}

//	@Override
//	public VBox vote() {
//		VBox temp = super.vote();
//		VBox voteScene = new VBox();
//		Label helloLbl = new Label("Hello, "+ super.name);
//		Label questionLbl = new Label("Are you protected?");
//		Button yes = new Button("Yes");
//		Button no = new Button("No");
//		
//		yes.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				voteScene.getChildren().clear();
//				voteScene.getChildren().addAll(temp);
//			}
//		});
//		
//		no.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				Stage stage = (Stage)no.getScene().getWindow();
//				stage.close();
//			}
//		});
//		
//		return voteScene;
//	}

	@Override
	public int sicknessPeriod() {
		return this.sickDate.until(LocalDate.now()).getDays();
	}
	
	@Override
	public String toString() {
		String str ="";
		Period period = super.sickDate.until(LocalDate.now());
		str += "Name: "+ this.name +" | Id: "+ this.id +" | Birth Year: "+ this.birthYear +" | Sick for: "+ period.getYears() +" years, "+ period.getMonths() +" months and "+ period.getDays() +" days to this date ("+ LocalDate.now().toString() +")";

		return str;
	}
}
