package Controller;

import java.util.ArrayList;
import Model.Address;
import Model.Ballot;
import Model.Citizen;
import Model.InvalidInputException;
import Model.Model;
import Model.Party;
import Model.Party.eFaction;
import Model.Politician;
import Model.SickCitizen;
import Model.SickPolitician;
import Model.SickSoldier;
import Model.Soldier;
import Model.Votable;
import View.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
	private Model model;
	private View view;
	static int ballotIndex = 0;
	static int citizenIndex = 0;
	private boolean endOfVoting = false;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		EventHandler<ActionEvent> EventHandlerToStartMenuSubmitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(model.checkElectionsDate(Integer.parseInt(view.getElectionsYear()), Integer.parseInt(view.getElectionsMonth()))) {
					try{model.update(Integer.parseInt(view.getElectionsMonth()), Integer.parseInt(view.getElectionsYear()));}
					catch(InvalidInputException e) {

					}

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
				VBox temp = view.addCandidate();
				view.getPartiesChoiceBox().getItems().addAll(model.getParties());
				view.setPlatform(temp);
			}
		};
		view.EventHandlerToAddCandidateButton(EventHandlerToAddCandidateButton);

		EventHandler<ActionEvent> EventHandlerToShowBallotsButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				VBox temp = view.showBallots();
				TableView<Ballot<? extends Votable>> table = view.getBallotsTable();
				ArrayList<Ballot<? extends Votable>> ballots = model.getBallots();
				for(int i = 0; i < ballots.size(); i++) {
					table.getItems().add(ballots.get(i));
				}

				view.setPlatform(temp);
			}
		};
		view.EventHandlerToShowBallotsButton(EventHandlerToShowBallotsButton);

		EventHandler<ActionEvent> EventHandlerToShowCitizensButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				VBox temp = view.showCitizens();
				TableView<Citizen> table = view.getCitizensTable();
				ArrayList<Citizen> citizens = (ArrayList<Citizen>)model.getCitizens();
				for(int i = 0; i < citizens.size(); i++) {
					table.getItems().add(citizens.get(i));
				}
				view.setPlatform(temp);
			}
		};
		view.EventHandlerToShowCitizensButton(EventHandlerToShowCitizensButton);

		EventHandler<ActionEvent> EventHandlerToShowPartiesButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				VBox temp = view.showParties();
				TableView<Party> table = view.getPartiesTable();
				ArrayList<Party> parties = model.getParties();
				for(int i = 0; i < parties.size(); i++) {
					table.getItems().add(parties.get(i));
				}
				view.setPlatform(temp);
			}
		};
		view.EventHandlerToShowPartiesButton(EventHandlerToShowPartiesButton);

		EventHandler<ActionEvent> EventHandlerToStartElectionsButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(model.isElectionsStarted()) {
					Alert alert = new Alert(Alert.AlertType.ERROR);//ERROR);//INFORMATION);
					alert.setContentText("*** Elections already started! ***");
					alert.show();
				}
				else {
					Citizen currentCitizen = model.getBallots().get(ballotIndex).getVoters().get(citizenIndex);
					if(currentCitizen.getClass() == Politician.class || currentCitizen.getClass() == SickPolitician.class)
						primeriesVote();
					else
						checkSickCitizen();
				}
			}
		};
		view.EventHandlerToStartElectionsButton(EventHandlerToStartElectionsButton);
		
		EventHandler<ActionEvent> EventHandlerToPrimeriesSubmitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int choice = view.getPoliCB().getSelectionModel().getSelectedIndex();

				if(choice >= 0) {
					view.getPoliCB().getValue().voted();
					
					checkSickCitizen();
				}
				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("* No candidate was selected");
					alert.show();
				}
			}
		};
		view.EventHandlerToPrimeriesSubmitButton(EventHandlerToPrimeriesSubmitButton);

		EventHandler<ActionEvent> EventHandlerToCoronaYesButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				checkSoldier();
			}
		};
		view.EventHandlerToCoronaYesButton(EventHandlerToCoronaYesButton);

		EventHandler<ActionEvent> EventHandlerToCoronaNoButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Ballot<? extends Votable> currentBallot = model.getBallots().get(ballotIndex);
				currentBallot.getBallotResultsList().set(0, currentBallot.getBallotResultsList().get(0) + 1);

				forwardVoter();
				if(!endOfVoting) {
					Citizen currentCitizen = currentBallot.getVoters().get(citizenIndex);
					if(currentCitizen.getClass() == Politician.class || currentCitizen.getClass() == SickPolitician.class)
						primeriesVote();
					else
						checkSickCitizen();
				}
			}
		};
		view.EventHandlerToCoronaNoButton(EventHandlerToCoronaNoButton);

		EventHandler<ActionEvent> EventHandlerToArmyYesButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				VoterScene();
			}
		};
		view.EventHandlerToArmyYesButton(EventHandlerToArmyYesButton);

		EventHandler<ActionEvent> EventHandlerToArmyNoButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Ballot<? extends Votable> currentBallot = model.getBallots().get(ballotIndex);
				currentBallot.getBallotResultsList().set(0, currentBallot.getBallotResultsList().get(0) + 1);
				forwardVoter();
				if(!endOfVoting) {
					Citizen currentCitizen = currentBallot.getVoters().get(citizenIndex);
					if(currentCitizen.getClass() == Politician.class || currentCitizen.getClass() == SickPolitician.class)
						primeriesVote();
					else
						checkSickCitizen();
				}
			}
		};
		view.EventHandlerToArmyNoButton(EventHandlerToArmyNoButton);

		EventHandler<ActionEvent> EventHandlerToAbstainedButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Ballot<? extends Votable> currentBallot = model.getBallots().get(ballotIndex);
				currentBallot.getBallotResultsList().set(0, currentBallot.getBallotResultsList().get(0) + 1);

				forwardVoter();
				if(!endOfVoting) {
					Citizen currentCitizen = currentBallot.getVoters().get(citizenIndex);
					if(currentCitizen.getClass() == Politician.class || currentCitizen.getClass() == SickPolitician.class)
						primeriesVote();
					else
						checkSickCitizen();
				}
					
			}
		};
		view.EventHandlerToAbstainedButton(EventHandlerToAbstainedButton);

		EventHandler<ActionEvent> EventHandlerToVoteSubmitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int choice = view.getPartiesCB().getSelectionModel().getSelectedIndex();

				if(choice >= 0) {
					Ballot<? extends Votable> currentBallot = model.getBallots().get(ballotIndex);
					currentBallot.getBallotResultsList().set(choice+1, currentBallot.getBallotResultsList().get(choice+1) + 1);

					forwardVoter();
					if(!endOfVoting) {
						Citizen currentCitizen = currentBallot.getVoters().get(citizenIndex);
						if(currentCitizen.getClass() == Politician.class || currentCitizen.getClass() == SickPolitician.class)
							primeriesVote();
						else
							checkSickCitizen();
					}
						
				}
				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("* No party was selected");
					alert.show();
				}
			}
		};
		view.EventHandlerToVoteSubmitButton(EventHandlerToVoteSubmitButton);

		EventHandler<ActionEvent> EventHandlerToElectionsResultsButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(model.isElectionsStarted())
					showResults();
				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("*** Elections did not start yet!");
					alert.show();
				}
			}
		};
		view.EventHandlerToElectionsResultsButton(EventHandlerToElectionsResultsButton);

		EventHandler<ActionEvent> EventHandlerToExitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = (Stage)view.getExitBtn().getScene().getWindow();
				stage.close();
			}
		};
		view.EventHandlerToExitButton(EventHandlerToExitButton);

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
				ArrayList<Node> page = view.getAddBallotFields();
				TextField cityTF = (TextField)page.get(0);
				TextField streetTF = (TextField)page.get(1);
				TextField houseNumTF = (TextField)page.get(2);
				Address address = new Address(cityTF.getText(), streetTF.getText(), Integer.parseInt(houseNumTF.getText()));
				model.addBallot(address);
				view.clear(page);
			}
		};
		view.EventHandlerToBallotSubmitButton(EventHandlerToBallotSubmitButton);

		EventHandler<ActionEvent> EventHandlerToCitizenCheckButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				checkCitizenType(false);
			}
		};
		view.EventHandlerToCitizenCheckButton(EventHandlerToCitizenCheckButton);

		EventHandler<ActionEvent> EventHandlerToCitizenSubmitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ChoiceBox<Ballot> ballotsCB = view.getBallotsChoiceBox();
				Ballot ballot = ballotsCB.getValue();
				try {
					model.addCitizen(model.citizen, ballot.getId());
					ArrayList<Node> page = view.getAddCitizenFields();
					page.add(ballotsCB);
					view.clear(page);

				} catch (InvalidInputException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);//ERROR);//INFORMATION);
					alert.setContentText(e.getMsg());
					alert.show();
				}
			}
		};
		view.EventHandlerToCitizenSubmitButton(EventHandlerToCitizenSubmitButton);

		EventHandler<ActionEvent> EventHandlerToPartySubmitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(Alert.AlertType.ERROR);//ERROR);//INFORMATION);

				ArrayList<Node> page = view.getAddPartyFields();

				TextField nameTF = (TextField)page.get(0);
				if(nameTF.getText().isEmpty())
					alert.setContentText(alert.getContentText() + "* No party name was entered \n");

				eFaction faction = null;
				RadioButton centerR = (RadioButton)page.get(1);
				RadioButton leftR = (RadioButton)page.get(2);
				RadioButton rightR = (RadioButton)page.get(3);
				if(centerR.isSelected())
					faction = eFaction.Center;
				else if(leftR.isSelected())
					faction = eFaction.Left;
				else if(rightR.isSelected())
					faction = eFaction.Right;
				else
					alert.setContentText(alert.getContentText() + "* No faction was selected \n");

				DatePicker establishDP = (DatePicker)page.get(4);
				if(establishDP.getValue() == null)
					alert.setContentText(alert.getContentText() + "* No establishment date was selected \n");


				if(alert.getContentText().isEmpty()) {
					model.addParty(nameTF.getText(), faction, establishDP.getValue());
					view.clear(page);
				}
				else
					alert.show();
			}
		};
		view.EventHandlerToPartySubmitButton(EventHandlerToPartySubmitButton);

		EventHandler<ActionEvent> EventHandlerToCandidateCheckButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				checkCitizenType(true);
			}
		};
		view.EventHandlerToCandidateCheckButton(EventHandlerToCandidateCheckButton);

		EventHandler<ActionEvent> EventHandlerToCandidateSubmitButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(Alert.AlertType.ERROR);//ERROR);//INFORMATION);

				ChoiceBox<Ballot> ballotsCB = view.getBallotsChoiceBox();
				Ballot<? extends Votable> b = ballotsCB.getValue(); 
				if(ballotsCB.getValue() == null)
					alert.setContentText(alert.getContentText() + "* No ballot was selected");

				ChoiceBox<Party> partiesCB = view.getPartiesChoiceBox();
				if(partiesCB.getValue() == null)
					alert.setContentText(alert.getContentText() + "* No party was selected");



				if(alert.getContentText().isEmpty()) {
					Politician politician;
					if(model.citizen.getClass() == Citizen.class || model.citizen.getClass() == Soldier.class)
						politician = new Politician(partiesCB.getValue(), model.citizen.getName(), model.citizen.getId(), model.citizen.getBirthYear());
					else
						politician = new SickPolitician(partiesCB.getValue(), model.citizen.getName(), model.citizen.getId(), model.citizen.getBirthYear(), model.citizen.getSickDate1());
					try {
						model.addCandidate(politician, ballotsCB.getValue().getId());
						ArrayList<Node> page = view.getAddCitizenFields();
						page.add(ballotsCB);
						page.add(partiesCB);
						view.clear(page);

					} catch (InvalidInputException e) {
						alert.setContentText(e.getMsg());
						alert.show();
					}
				}
				else
					alert.show();
			}
		};
		view.EventHandlerToCandidateSubmitButton(EventHandlerToCandidateSubmitButton);
	}












	private void checkCitizenType(boolean isPolitician) {
		Alert alert = new Alert(Alert.AlertType.ERROR);//ERROR);//INFORMATION);
		ArrayList<Node> page = view.getAddCitizenFields();
		TextField fNameTF = (TextField)page.get(0);
		if(fNameTF.getText().isEmpty())
			alert.setContentText(alert.getContentText() + "* No first name was entered \n");

		TextField lNameTF = (TextField)page.get(1);
		if(lNameTF.getText().isEmpty())
			alert.setContentText(alert.getContentText() + "* No last name was entered  \n");

		TextField idTF = (TextField)page.get(2);
		try{model.checkId(idTF.getText());}
		catch(InvalidInputException e) {
			alert.setContentText(alert.getContentText() + e.getMsg());
		}

		ComboBox<Integer> birthYearCB = (ComboBox<Integer>)page.get(3);
		if(birthYearCB.getValue() == null)
			alert.setContentText(alert.getContentText() + "* No birth year was selected \n");

		boolean isQuarintined = false;
		RadioButton yesRadio = (RadioButton)page.get(4);
		RadioButton NoRadio = (RadioButton)page.get(5);
		if(yesRadio.isSelected())
			isQuarintined = true;
		else if(!NoRadio.isSelected())
			alert.setContentText(alert.getContentText() + "*  No selection for is Quarintined was chosen \n");

		DatePicker infectionDateDP = null;
		if(isQuarintined) {
			infectionDateDP = (DatePicker)page.get(6);
			if(infectionDateDP.getValue() == null)
				alert.setContentText(alert.getContentText() + "*  No infection date was selected \n");
		}


		if(alert.getContentText().isEmpty()) {
			ChoiceBox<Ballot> ballotsCB = view.getBallotsChoiceBox();
			ballotsCB.getItems().clear();
			Class type = model.checkCitizenType(birthYearCB.getValue(), isQuarintined, isPolitician);

			if(type != null) {
				Citizen citizen = null;
				String name = fNameTF.getText() +" "+ lNameTF.getText();
				String id = idTF.getText();
				int birthYear = birthYearCB.getValue();

				if(type == SickSoldier.class)
					citizen = new SickSoldier(name, id, birthYear, infectionDateDP.getValue());
				else if(type == Soldier.class)
					citizen = new Soldier(name, id, birthYear);
				else if(type == SickCitizen.class)
					citizen = new SickCitizen(name, id, birthYear, infectionDateDP.getValue());
				else
					citizen = new Citizen(name, id, birthYear);

				model.citizen = citizen;

				if(type == Citizen.class)
					ballotsCB.getItems().addAll(model.<Citizen>getFilteredBallots(type));
				else if(type == SickCitizen.class)
					ballotsCB.getItems().addAll(model.<SickCitizen>getFilteredBallots(type));
				else if(type == Soldier.class)
					ballotsCB.getItems().addAll(model.<Soldier>getFilteredBallots(type));
				else if(type == SickSoldier.class)
					ballotsCB.getItems().addAll(model.<SickSoldier>getFilteredBallots(type));
			}
			else
				alert.show();
		}
	}
	
	public void primeriesVote() {
		Politician currentCitizen = (Politician)model.getBallots().get(ballotIndex).getVoters().get(citizenIndex);
		Party poliParty = currentCitizen.getParty();
		
		VBox temp = view.primeriesVote();
		view.getPoliCB().getItems().addAll(poliParty.getCandidatesInParty());
		
		view.setHelloLbl("Hello, "+ currentCitizen.getName());
		
		Label primeriesLbl = new Label("Select a candidate for party leader:");
		primeriesLbl.setStyle("-fx-font: 40 arial;");
		
		temp.getChildren().add(1, primeriesLbl);
		
		view.setPlatform(temp);
	}

	public void checkSickCitizen() {
		Ballot<? extends Votable> ballot = model.getBallots().get(ballotIndex);
		Citizen citizen = ballot.getVoters().get(citizenIndex);

		if(citizen.getClass() == SickCitizen.class || citizen.getClass() == SickSoldier.class || citizen.getClass() == SickPolitician.class) {
			VBox temp = view.coronaQs();
			view.setHelloLbl("Hello, "+ citizen.getName());
			view.setPlatform(temp);
		}
		else
			checkSoldier();
	}

	public void checkSoldier() {
		Ballot<? extends Votable> ballot = model.getBallots().get(ballotIndex);
		Citizen citizen = ballot.getVoters().get(citizenIndex);

		if(citizen.getClass() == Soldier.class || citizen.getClass() == SickSoldier.class){
			VBox temp = view.ArmyQs();
			view.setHelloLbl("Hello, "+ citizen.getName());
			view.setPlatform(temp);
		}
		else
			VoterScene();
	}

	public void VoterScene() {
		Ballot<? extends Votable> ballot = model.getBallots().get(ballotIndex);
		Citizen citizen = ballot.getVoters().get(citizenIndex);

		VBox temp = view.vote();
		view.getPartiesCB().getItems().addAll(model.getParties());

		view.setHelloLbl("Hello, "+ citizen.getName());
		view.setPlatform(temp);

	}

	public void forwardVoter() {
		ArrayList<Ballot<? extends Votable>> ballots = model.getBallots();
		if((citizenIndex) < ballots.get(ballotIndex).getVoters().size()-1)
			citizenIndex++;
		else {
			if((ballotIndex) < ballots.size()-1) {
				ballotIndex++;
				citizenIndex = 0;
			}
			else {
				showResults();
				endOfVoting = true;
				model.setElectionsStarted();
			}
		}
	}

	public void showResults() {
		VBox temp = view.electionsResults();

		Label ballotLeader;
		ArrayList<Party> parties = model.getParties();
		ArrayList<Ballot<? extends Votable>> ballots = model.getBallots();
		for(int i = 0; i < ballots.size(); i++) {
			ballotLeader = new Label("Ballot #"+ (i+1) +": "+ ballots.get(i).findLeadingPartyName(parties));
			ballotLeader.setStyle("-fx-font: 20 arial;");
			temp.getChildren().add(temp.getChildren().size()-1, ballotLeader);
		}

		ArrayList<Integer> resultsList = model.getResultsList();				
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		pieChartData.add(new PieChart.Data("Abstained from voting", resultsList.get(0)));
		for(int i = 1; i < resultsList.size(); i++) {
			pieChartData.add(new PieChart.Data(parties.get(i-1).getName(), resultsList.get(i)));
		}


		PieChart resultPC = view.getResultPieChart();
		resultPC.setData(pieChartData);
		resultPC.setTitle("Elections result summarry:");
		resultPC.setLegendSide(Side.LEFT);

		final Label caption = new Label("");
		caption.setTextFill(Color.DARKORANGE);
		caption.setStyle("-fx-font: 24 arial;");

		for (final PieChart.Data data : resultPC.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
					new EventHandler<MouseEvent>() {
				@Override public void handle(MouseEvent e) {
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());
					caption.setText(String.valueOf(data.getPieValue()) + "%");
				}
			});
		}

		view.setPlatform(temp);
	}
}
