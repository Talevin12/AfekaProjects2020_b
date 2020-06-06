package View;

import java.time.LocalDate;

import id314920505_id316013804.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class View {

	private Group root;
	private Scene startMenu;
	private Scene app;

	private Text monthTxt;
	private Text yearTxt;
	private TextField monthTextF;
	private TextField yearTextF;
	private Button startMenuSubmit;


	private SplitPane splitPane;

	private VBox naviMenu;
	private Button addBallotBtn;
	private Button addCitizenBtn;
	private Button addPartyBtn;
	private Button addCandidateBtn;
	private Button showBallotsBtn;
	private Button showVotersBtn;
	private Button showPartiesBtn;
	private Button startElectionsBtn;
	private Button resultsBtn;
	private Button exitBtn;

	private VBox platform;

	private Button citizenBallotBtn;
	private Button coronaBallotBtn;
	private Button armyBallotBtn;
	private Button armyCoronaBallotBtn;

	private Text cityTxt;
	private Text streetTxt;
	private Text houseNumTxt;
	private TextField cityField; 
	private TextField streetField;
	private TextField houseNumField;
	private Button ballotSubmit;

	private Text fNameTxt;
	private Text lNameTxt;
	private Text idTxt;
	private Text birthYearTxt;
	private Text isQuarintinedTxt;
	private Text infectionDateTxt;
	private Text ballotTxt;
	private TextField fNameField;
	private TextField lNameField;
	private TextField idField;
	private ComboBox<Integer> birthYearComboBox;
	private ChoiceBox<Ballot> ballotChoiceBox;
	private RadioButton isQuarintinedYESRadioBtn;
	private RadioButton isQuarintinedNORadioBtn;
	private DatePicker infectionDatePicker;
	private Button checkCitizenTypeBtn;
	private Button addCitizenSubmitBtn;
	
	private Text partyNameTxt;
	private Text factionTxt;
	private Text establishDateTxt;
	private TextField partyNameField;
	private RadioButton centerRadioBtn;
	private RadioButton leftRadioBtn;
	private RadioButton rightRadioBtn;
	private DatePicker establishDatePicker;
	private Button partySubmitBtn;
	
	private Text partyTxt;
	private ChoiceBox<Party> partyChoiceBox;
	
	private Text showBallotsTitle;
	private TableView ballotTable;
	
	private Text showCitizensTitle;
	private TableView citizenTable;
	
	private Text showPartyTitle;
	private TableView partyTable;
	

	public View(Stage stage) {
		this.root = new Group();

		Text txt = new Text("Elections");
		txt.setFill(Color.ROYALBLUE);
		txt.setStyle("-fx-font: 90 arial;");
		txt.setTextAlignment(TextAlignment.CENTER);

		this.yearTxt = new Text("Year: ");
		this.yearTextF = new TextField();
		HBox hBoxYear = new HBox();
		hBoxYear.getChildren().addAll(yearTxt, yearTextF);
		hBoxYear.setAlignment(Pos.CENTER);
		HBox.setMargin(yearTxt, new Insets(20,20,20,20)); 
		HBox.setMargin(yearTextF, new Insets(20,20,20,20));
		root.getChildren().add(hBoxYear);

		this.monthTxt = new Text("Month: ");
		this.monthTextF = new TextField();
		HBox hBoxMonth = new HBox();
		hBoxMonth.getChildren().addAll(monthTxt, monthTextF);
		hBoxMonth.setAlignment(Pos.CENTER);
		HBox.setMargin(monthTxt, new Insets(20,20,20,20)); 
		HBox.setMargin(monthTextF, new Insets(20,20,20,20));
		root.getChildren().add(hBoxMonth);

		startMenuSubmit = new Button("SUBMIT");
		VBox vb = new VBox();
		vb.getChildren().addAll(txt, hBoxYear, hBoxMonth ,startMenuSubmit);
		vb.setAlignment(Pos.CENTER);


		this.startMenu = new Scene(vb,1000,600);
		stage.setTitle("Elections");
		stage.setScene(this.startMenu);
		stage.show();

		startMenuSubmit.setOnAction(e ->  stage.setScene(this.app));



		///////////////////////////////////////////////////////////



		this.naviMenu = new VBox();
		this.naviMenu.setPadding(new Insets(10));
		this.naviMenu.setSpacing(30);
		this.naviMenu.setAlignment(Pos.BASELINE_LEFT);

		this.addBallotBtn = new Button("Add Ballot");

		this.addCitizenBtn = new Button("Add Citizen");

		this.addPartyBtn = new Button("Add Party");

		this.addCandidateBtn = new Button("Add Candidate");

		this.showBallotsBtn = new Button("Show Balllots");

		this.showVotersBtn = new Button("Show all Voters");

		this.showPartiesBtn = new Button("Show Parties");

		this.startElectionsBtn = new Button("Start Elections");

		this.resultsBtn = new Button("Elections Results");

		this.exitBtn = new Button("EXIT");

		this.naviMenu.getChildren().addAll(addBallotBtn, addCitizenBtn, addPartyBtn, addCandidateBtn, showBallotsBtn, showVotersBtn, showPartiesBtn, startElectionsBtn, resultsBtn, exitBtn);
		this.naviMenu.setMaxWidth(150);
		this.naviMenu.setMinWidth(150);

		//
		this.platform = showParties();

		//

		this.splitPane = new SplitPane(this.naviMenu, this.platform);
		this.app = new Scene(splitPane, 1000, 600);
		stage.setTitle("Elections");
	}

	private VBox chooseBallot() {
		this.citizenBallotBtn = new Button("Regular Ballot");
		this.citizenBallotBtn.setMinSize(200, 80);

		this.coronaBallotBtn = new Button("Corona Ballot");
		this.coronaBallotBtn.setMinSize(200, 80);

		this.armyBallotBtn = new Button("Army Ballot");
		this.armyBallotBtn.setMinSize(200, 80);

		this.armyCoronaBallotBtn = new Button("Army-Corona Ballot");
		this.armyCoronaBallotBtn.setMinSize(200, 80);

		VBox buttons = new VBox(citizenBallotBtn, coronaBallotBtn, armyBallotBtn, armyCoronaBallotBtn);
		buttons.setSpacing(20);
		buttons.setAlignment(Pos.CENTER);

		return buttons;
	}

	public VBox addBallot() {	
		this.cityTxt = new Text("City: ");
		this.cityField = new TextField();
		HBox hBoxCity = new HBox();
		hBoxCity.getChildren().addAll(cityTxt, cityField);
		HBox.setMargin(cityTxt, new Insets(20,20,20,20)); 
		HBox.setMargin(cityField, new Insets(20,20,20,20));

		this.streetTxt = new Text("Street: ");
		this.streetField = new TextField();
		HBox hBoxStreet = new HBox();
		hBoxStreet.getChildren().addAll(streetTxt, streetField);
		HBox.setMargin(streetTxt, new Insets(20,20,20,20)); 
		HBox.setMargin(streetField, new Insets(20,20,20,20));

		this.houseNumTxt = new Text("Number: ");
		this.houseNumField = new TextField();
		HBox hBoxStNum = new HBox();
		hBoxStNum.getChildren().addAll(houseNumTxt, houseNumField);
		HBox.setMargin(houseNumTxt, new Insets(20,20,20,20)); 
		HBox.setMargin(houseNumField, new Insets(20,20,20,20));

		this.ballotSubmit = new Button("Submit");
		this.ballotSubmit.setMinSize(150, 60);

		VBox root = new VBox();
		root.getChildren().addAll(hBoxCity, hBoxStreet, hBoxStNum, ballotSubmit);
		root.setAlignment(Pos.CENTER);
		return root;
	}

	public VBox addCitizen() {
		VBox root = new VBox();

		this.fNameTxt = new Text("First Name: ");
		this.fNameField = new TextField();
		HBox hBoxFName = new HBox();
		hBoxFName.getChildren().addAll(fNameTxt, fNameField);
		HBox.setMargin(fNameTxt, new Insets(20,20,3,20)); 
		HBox.setMargin(fNameField, new Insets(20,20,3,20));

		this.lNameTxt = new Text("Last Name: ");
		this.lNameField = new TextField();
		HBox hBoxLName = new HBox();
		hBoxLName.getChildren().addAll(lNameTxt, lNameField);
		HBox.setMargin(lNameTxt, new Insets(3,20,3,20)); 
		HBox.setMargin(lNameField, new Insets(3,20,3,20));

		this.idTxt = new Text("Id: ");
		this.idField = new TextField();
		HBox hBoxId = new HBox();
		hBoxId.getChildren().addAll(idTxt, idField);
		HBox.setMargin(idTxt, new Insets(3,20,3,20)); 
		HBox.setMargin(idField, new Insets(3,20,3,20));

		this.birthYearTxt = new Text("Birth Year");
		this.birthYearComboBox = new ComboBox<>();
		int currentYear = LocalDate.now().getYear();
		for(int i = currentYear; i >= currentYear-150; i--)
			this.birthYearComboBox.getItems().add(i);
		HBox hBoxBirthYear = new HBox();
		hBoxBirthYear.getChildren().addAll(birthYearTxt, birthYearComboBox);

		this.isQuarintinedTxt = new Text("Are you Quarintined?: ");
		ToggleGroup tg = new ToggleGroup();
		this.isQuarintinedYESRadioBtn = new RadioButton("Yes	");
		this.isQuarintinedYESRadioBtn.setToggleGroup(tg);
		this.isQuarintinedNORadioBtn = new RadioButton("No");
		this.isQuarintinedNORadioBtn.setToggleGroup(tg);
		HBox hBoxIsQuarintined = new HBox(isQuarintinedTxt, isQuarintinedYESRadioBtn, isQuarintinedNORadioBtn);

		this.infectionDateTxt = new Text("Infection Date: ");
		this.infectionDatePicker = new DatePicker();
		infectionDatePicker.setDayCellFactory(d -> new DateCell() {
			@Override 
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				setDisable(item.isAfter(LocalDate.now()));
			}
		});
		HBox hBoxInfectedDate = new HBox();
		hBoxInfectedDate.getChildren().addAll(infectionDateTxt, infectionDatePicker);
		
		this.checkCitizenTypeBtn = new Button("**Check**");
		this.checkCitizenTypeBtn.setMinSize(150, 60);
		
		this.ballotTxt = new Text("Choose Ballot: ");
		this.ballotChoiceBox = new ChoiceBox<Ballot>();
		HBox hBoxBallot = new HBox(ballotTxt, ballotChoiceBox);
		
		this.addCitizenSubmitBtn = new Button("Submit");
		this.addCitizenSubmitBtn.setMinSize(150, 60);
		
		root.getChildren().addAll(hBoxFName, hBoxLName, hBoxId, hBoxBirthYear, hBoxIsQuarintined, hBoxInfectedDate, checkCitizenTypeBtn, hBoxBallot, addCitizenSubmitBtn);
		root.setSpacing(20);
		root.setAlignment(Pos.TOP_CENTER);
		return root;
	}
	
	public VBox addParty() {
		VBox root = new VBox();
		
		this.partyNameTxt = new Text("Party Name: ");
		this.partyNameField = new TextField();
		HBox hBoxPartyName = new HBox(partyNameTxt, partyNameField);
		
		this.factionTxt = new Text("Faction: ");
		ToggleGroup tg = new ToggleGroup();
		this.leftRadioBtn = new RadioButton("Left	");
		this.leftRadioBtn.setToggleGroup(tg);
		this.centerRadioBtn = new RadioButton("Center	");
		this.centerRadioBtn.setToggleGroup(tg);
		this.rightRadioBtn = new RadioButton("Right");
		this.rightRadioBtn.setToggleGroup(tg);
		HBox hBoxFaction = new HBox(factionTxt, leftRadioBtn, centerRadioBtn, rightRadioBtn);
		
		this.establishDateTxt = new Text("Date of Establishment: ");
		this.establishDatePicker = new DatePicker();
		establishDatePicker.setDayCellFactory(d -> new DateCell() {
			@Override 
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);
				setDisable(item.isAfter(LocalDate.now()));
			}
		});
		HBox hBoxEstablishDate = new HBox(establishDateTxt, establishDatePicker);
		
		this.partySubmitBtn = new Button("Submit");
		this.partySubmitBtn.setMinSize(150, 60);
		
		root.getChildren().addAll(hBoxPartyName, hBoxFaction, hBoxEstablishDate, partySubmitBtn);
		root.setPadding(new Insets(20));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(25);
		return root;
	}
	
	public VBox addCandidate() {
		VBox root = addCitizen();
		
		this.partyTxt = new Text("Party: ");
		this.partyChoiceBox = new ChoiceBox<Party>();
		HBox hBoxParty = new HBox(partyTxt, partyChoiceBox);
		
		root.getChildren().add(root.getChildren().size()-1, hBoxParty);
		return root;
	}
	
	public VBox showBallots() {
		VBox root = new VBox();
		
		this.showBallotsTitle = new Text("Show All Ballots");
		this.showBallotsTitle.setStyle("-fx-font: 45 arial;");
		
		this.ballotTable = new TableView<>();
		TableColumn<String, Ballot> typeC = new TableColumn<>("Type");
		typeC.setMinWidth(170);
		typeC.setMaxWidth(170);
		TableColumn<String, Ballot> idC = new TableColumn<>("Id");
		idC.setMinWidth(170);
		idC.setMaxWidth(170);
		TableColumn<String, Ballot> addressC = new TableColumn<>("Address");
		TableColumn<String, Ballot> cityC = new TableColumn<>("City");
		TableColumn<String, Ballot> streetC = new TableColumn<>("Street");
		TableColumn<String, Ballot> houseNoC = new TableColumn<>("House Number");
		cityC.setMinWidth(170);
		cityC.setMaxWidth(170);
		streetC.setMinWidth(170);
		streetC.setMaxWidth(170);
		houseNoC.setMinWidth(170);
		houseNoC.setMinWidth(170);	
		addressC.getColumns().addAll(cityC, streetC, houseNoC);
		this.ballotTable.getColumns().addAll(typeC, idC, addressC);
		
		root.getChildren().addAll(showBallotsTitle, ballotTable);
		return root;
	}
	
	public VBox showCitizens() {
		VBox root = new VBox();
		
		this.showCitizensTitle = new Text("Show All Citizens");
		this.showCitizensTitle.setStyle("-fx-font: 45 arial;");
		
		this.citizenTable = new TableView<>();
		TableColumn<String, Ballot> nameC = new TableColumn<>("Name");
		nameC.setMinWidth(140);
		nameC.setMaxWidth(140);
		TableColumn<String, Ballot> idC = new TableColumn<>("Id");
		idC.setMinWidth(142);
		idC.setMaxWidth(142);
		TableColumn<String, Ballot> birthYearC = new TableColumn<>("Birth Year");
		birthYearC.setMinWidth(141);
		birthYearC.setMaxWidth(141);
		TableColumn<String, Ballot> sickPeriodC = new TableColumn<>("Sick Peroid");
		TableColumn<String, Ballot> yearsC = new TableColumn<>("Years");
		TableColumn<String, Ballot> monthsC = new TableColumn<>("Months");
		TableColumn<String, Ballot> daysC = new TableColumn<>("Days");
		yearsC.setMinWidth(425/3);
		yearsC.setMaxWidth(425/3);
		monthsC.setMinWidth(425/3);
		monthsC.setMaxWidth(425/3);
		daysC.setMinWidth(425/3);
		daysC.setMaxWidth(425/3);		
		sickPeriodC.getColumns().addAll(yearsC, monthsC, daysC);
		this.citizenTable.getColumns().addAll(nameC, idC, birthYearC, sickPeriodC);
		
		root.getChildren().addAll(showCitizensTitle, citizenTable);
		return root;
	}
	
	public VBox showParties() {
		VBox root = new VBox();
		
		this.showPartyTitle = new Text("Show All Parties");
		this.showPartyTitle.setStyle("-fx-font: 45 arial;");
		
		this.partyTable = new TableView<>();
		TableColumn<String, Ballot> nameC = new TableColumn<>("Name");
		nameC.setMinWidth(850/4);
		nameC.setMaxWidth(850/4);
		TableColumn<String, Ballot> factionC = new TableColumn<>("Faction");
		factionC.setMinWidth(850/4);
		factionC.setMaxWidth(850/4);
		TableColumn<String, Ballot> establishDateC = new TableColumn<>("Establish Date");
		establishDateC.setMinWidth(850/2);
		establishDateC.setMaxWidth(850/2);
		this.partyTable.getColumns().addAll(nameC, factionC, establishDateC);
		
		root.getChildren().addAll(showPartyTitle, partyTable);
		return root;
	}
}
