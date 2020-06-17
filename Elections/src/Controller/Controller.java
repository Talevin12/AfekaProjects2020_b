package Controller;

import Model.Citizen;
import Model.Model;
import Model.SickCitizen;
import Model.SickSoldier;
import Model.Soldier;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


public class Controller {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		EventHandler<ActionEvent> EventHandlerToStartMenuSubmitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(model.checkElectionsDate(Integer.parseInt(view.getElectionsYear()), Integer.parseInt(view.getElectionsMonth()))) {
					model.update(Integer.parseInt(view.getElectionsMonth()), Integer.parseInt(view.getElectionsYear()));
					view.setScene();
				}
				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);//ERROR);//INFORMATION);
					alert.setContentText("Wrong input. The date is invalid or it isn't accured yet.");
					alert.show();
				}	
			}
		};
		view.EventHandlerToStartMenuSubmitButton(EventHandlerToStartMenuSubmitButton);

		EventHandler<ActionEvent> EventHandlerToAddBallotButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.setPlatform(view.chooseBallot());
			}
		};
		view.EventHandlerToAddBallotButton(EventHandlerToAddBallotButton);

		EventHandler<ActionEvent> EventHandlerToAddCitizenButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.setPlatform(view.addCitizen());
			}
		};
		view.EventHandlerToAddCitizenButton(EventHandlerToAddCitizenButton);

		EventHandler<ActionEvent> EventHandlerToAddPartyButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.setPlatform(view.addParty());
			}
		};
		view.EventHandlerToAddPartyButton(EventHandlerToAddPartyButton);

		EventHandler<ActionEvent> EventHandlerToAddCandidateButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.setPlatform(view.addCandidate());
			}
		};
		view.EventHandlerToAddCandidateButton(EventHandlerToAddCandidateButton);

		EventHandler<ActionEvent> EventHandlerToShowBallotsButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.setPlatform(view.showBallots());
			}
		};
		view.EventHandlerToShowBallotsButton(EventHandlerToShowBallotsButton);

		EventHandler<ActionEvent> EventHandlerToShowCitizensButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.setPlatform(view.showCitizens());
			}
		};
		view.EventHandlerToShowCitizensButton(EventHandlerToShowCitizensButton);

		EventHandler<ActionEvent> EventHandlerToShowPartiesButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.setPlatform(view.showParties());
			}
		};
		view.EventHandlerToShowPartiesButton(EventHandlerToShowPartiesButton);

		//		EventHandler<ActionEvent> EventHandlerToStartElectionsButton = new EventHandler<ActionEvent>() {
		//			@Override
		//			public void handle(ActionEvent event) {
		//				view.setPlatform(view.addBallot());
		//			}
		//		};
		//		view.EventHandlerToStartElectionsButton(EventHandlerToStartElectionsButton);
		//
		//		EventHandler<ActionEvent> EventHandlerToShowResultsButton = new EventHandler<ActionEvent>() {
		//			@Override
		//			public void handle(ActionEvent event) {
		//				view.setPlatform(view.addBallot());
		//			}
		//		};
		//		view.EventHandlerToShowResultsButton(EventHandlerToShowResultsButton);

		EventHandler<ActionEvent> EventHandlerToChooseCitizenBallotButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.updateBallotType(Citizen.class);
				view.setPlatform(view.addBallot());
			}
		};
		view.EventHandlerToChooseCitizenBallotButton(EventHandlerToChooseCitizenBallotButton);

		EventHandler<ActionEvent> EventHandlerToChooseCoronaBallotButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.updateBallotType(SickCitizen.class);
				view.setPlatform(view.addBallot());
			}
		};
		view.EventHandlerToChooseCoronaBallotButton(EventHandlerToChooseCoronaBallotButton);

		EventHandler<ActionEvent> EventHandlerToChooseArmyBallotButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.updateBallotType(Soldier.class);
				view.setPlatform(view.addBallot());
			}
		};
		view.EventHandlerToChooseArmyBallotButton(EventHandlerToChooseArmyBallotButton);

		EventHandler<ActionEvent> EventHandlerToChooseArmyCoronaBallotButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.updateBallotType(SickSoldier.class);
				view.setPlatform(view.addBallot());
			}
		};
		view.EventHandlerToChooseArmyCoronaBallotButton(EventHandlerToChooseArmyCoronaBallotButton);
		
		EventHandler<ActionEvent> EventHandlerToBallotSubmitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.addBallot(view.getBallotAddress());
			}
		};
		view.EventHandlerToBallotSubmitButton(EventHandlerToBallotSubmitButton);
	}


}
