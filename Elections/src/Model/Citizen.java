package Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Citizen implements Votable	{
	protected String name;
	protected String id;
	protected int birthYear;
	protected LocalDate sickDate;

	public Citizen(String name, String id, int birthYear) {
		this.name = name; 
		this.id = id;
		this.birthYear = birthYear;
		this.sickDate = null;
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

//	@Override
//	public VBox vote() {
//		VBox voteScene = new VBox();
//		
//		Label helloLbl = new Label("Hello, "+ this.name);
//		helloLbl.setStyle("-fx-font: 60 arial;");
//		
//		ChoiceBox<String> partiesCB = new ChoiceBox<String>();
//		
//		Button submitBtn = new Button("Submit");
//		submitBtn.setMinSize(150, 60);
//		voteScene.getChildren().addAll(helloLbl, partiesCB, submitBtn);
//		voteScene.setSpacing(20);
//		
//		
//		return voteScene;
//	}

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
	
	public int getBirthYear() {
		return this.birthYear;
	}
	
	public LocalDate getSickDate1() {
			return this.sickDate;
	}
	
	public int getSickDate() {
		if(this.sickDate == null)
			return 0;
		else
			return (int)Duration.between(LocalDateTime.of(sickDate, LocalTime.of(0,0)), LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0))).toDays();
	}
}
