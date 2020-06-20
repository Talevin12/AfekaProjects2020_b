package View;

import java.time.LocalDate;
import java.util.ArrayList;
import Model.Address;
import Model.Ballot;
import Model.Citizen;
import Model.Party;
import Model.Politician;
import Model.Votable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class View {

	private Group root;
	private Scene startMenu;
	private Scene app;
	public Popup popup = new Popup();

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
	private Button showCitizensBtn;
	private Button showPartiesBtn;
	private Button startElectionsBtn;
	private Button resultsBtn;
	private Button exitBtn;

	private VBox platform;

	private Button citizenBallotBtn = new Button("Regular Ballot");///////Need to initialize all button who needs event handles
	private Button coronaBallotBtn = new Button("Corona Ballot");
	private Button armyBallotBtn = new Button("Army Ballot");
	private Button armyCoronaBallotBtn = new Button("Army-Corona Ballot");

	private Text cityTxt;
	private Text streetTxt;
	private Text houseNumTxt;
	private TextField cityField; 
	private TextField streetField;
	private TextField houseNumField;
	private Button ballotSubmit = new Button("Submit");

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
	private ChoiceBox<Ballot<? extends Votable>> ballotChoiceBox;
	private RadioButton isQuarintinedYESRadioBtn;
	private RadioButton isQuarintinedNORadioBtn;
	private DatePicker infectionDatePicker;
	private Button checkCitizenTypeBtn = new Button("**please press before selecting ballot**");
	private Button addCitizenSubmitBtn = new Button("Submit");

	private Text partyNameTxt;
	private Text factionTxt;
	private Text establishDateTxt;
	private TextField partyNameField;
	private RadioButton centerRadioBtn;
	private RadioButton leftRadioBtn;
	private RadioButton rightRadioBtn;
	private DatePicker establishDatePicker;
	private Button partySubmitBtn = new Button("Submit");

	private Button checkCandidateTypeBtn = new Button("**please press before selecting ballot**");
	private Text partyTxt;
	private ChoiceBox<Party> partyChoiceBox;
	private Button addCandidateSubmitBtn = new Button("Submit");


	private Text showBallotsTitle;
	private TableView<Ballot<? extends Votable>> ballotTable;

	private Text showCitizensTitle;
	private TableView<Citizen> citizenTable;

	private Text showPartyTitle;
	private TableView<Party> partyTable;

	private Label helloLbl = new Label();
	
	private ChoiceBox<Politician> poliChoiceBox;
	private Button primeriesSubmitBtn = new Button("Submit"); 
	
	private Button abstainBtn = new Button("Abstain");
	private ChoiceBox<Party> partiesCB;
	private Button voteSubmitBtn = new Button("Submit");


	private Button coronaYesBtn = new Button("Yes");
	private Button coronaNoBtn = new Button("No");

	private Button armyYesBtn = new Button("Yes");
	private Button armyNoBtn = new Button("No");

	private Label resultsTitle;
	private PieChart resultPieChart;

	public View(Stage stage) {
		this.root = new Group();
		stage.setTitle("Elections");

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

		this.showCitizensBtn = new Button("Show all Voters");

		this.showPartiesBtn = new Button("Show Parties");

		this.startElectionsBtn = new Button("Start Elections");

		this.resultsBtn = new Button("Elections Results");

		this.exitBtn = new Button("EXIT");

		this.naviMenu.getChildren().addAll(addBallotBtn, addCitizenBtn, addPartyBtn, addCandidateBtn, showBallotsBtn, showCitizensBtn, showPartiesBtn, startElectionsBtn, resultsBtn, exitBtn);
		this.naviMenu.setMaxWidth(150);
		this.naviMenu.setMinWidth(150);

		this.platform = new VBox();

		this.splitPane = new SplitPane(this.naviMenu, this.platform);
		this.app = new Scene(splitPane, 1000, 600);

		stage.setTitle("Elections");
		stage.setScene(this.startMenu);
		stage.show();
	}

	public VBox chooseBallot() {
		this.citizenBallotBtn.setMinSize(200, 80);

		this.coronaBallotBtn.setMinSize(200, 80);
		
		this.armyBallotBtn.setMinSize(200, 80);

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

		this.checkCitizenTypeBtn.setMinSize(150, 60);

		this.ballotTxt = new Text("Choose Ballot: ");
		this.ballotChoiceBox = new ChoiceBox<Ballot<? extends Votable>>();
		HBox hBoxBallot = new HBox(ballotTxt, ballotChoiceBox);

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

		this.partySubmitBtn.setMinSize(150, 60);

		root.getChildren().addAll(hBoxPartyName, hBoxFaction, hBoxEstablishDate, partySubmitBtn);
		root.setPadding(new Insets(20));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(25);
		return root;
	}

	public VBox addCandidate() {
		VBox root = addCitizen();
		root.getChildren().remove(this.checkCitizenTypeBtn);

		root.getChildren().add(root.getChildren().size()-2, this.checkCandidateTypeBtn);

		this.partyTxt = new Text("Party: ");
		this.partyChoiceBox = new ChoiceBox<Party>();
		HBox hBoxParty = new HBox(partyTxt, partyChoiceBox);

		root.getChildren().add(root.getChildren().size()-1, hBoxParty);
		root.getChildren().remove(this.addCitizenSubmitBtn);
		root.getChildren().add(this.addCandidateSubmitBtn);
		return root;
	}

	@SuppressWarnings("unchecked")
	public VBox showBallots() {
		VBox root = new VBox();

		this.showBallotsTitle = new Text("Show All Ballots");
		this.showBallotsTitle.setStyle("-fx-font: 45 arial;");

		this.ballotTable = new TableView<>();
		ballotTable.setPlaceholder(new Label("No rows to display"));

		TableColumn typeC = new TableColumn<>("Type");
		typeC.setMinWidth(170);
		typeC.setMaxWidth(170);
		typeC.setCellValueFactory(new PropertyValueFactory<Ballot<? extends Votable>, String>("type"));

		TableColumn idC = new TableColumn<>("Id");
		idC.setMinWidth(170);
		idC.setMaxWidth(170);
		idC.setCellValueFactory(new PropertyValueFactory<Ballot<? extends Votable>, Integer>("id"));

		TableColumn addressC = new TableColumn<>("Address");
		addressC.setCellValueFactory(new PropertyValueFactory<Ballot<? extends Votable>, Address>("address"));
		TableColumn cityC = new TableColumn<>("City");
		cityC.setCellValueFactory(new PropertyValueFactory<Address, String>("city"));
		TableColumn streetC = new TableColumn<>("Street");
		streetC.setCellValueFactory(new PropertyValueFactory<Address, String>("street"));
		TableColumn houseNoC = new TableColumn<>("House Number");
		houseNoC.setCellValueFactory(new PropertyValueFactory<Address, Integer>("houseNo"));
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
		TableColumn nameC = new TableColumn<>("Name");
		nameC.setCellValueFactory(new PropertyValueFactory<Citizen, String>("Name"));
		nameC.setMinWidth(425/2);
		nameC.setMaxWidth(425/2);
		TableColumn idC = new TableColumn<>("Id");
		idC.setCellValueFactory(new PropertyValueFactory<Citizen, String>("id"));
		idC.setMinWidth(425/2);
		idC.setMaxWidth(425/2);
		TableColumn birthYearC = new TableColumn<>("Birth Year");
		birthYearC.setCellValueFactory(new PropertyValueFactory<Citizen, Integer>("birthYear"));
		birthYearC.setMinWidth(425/2);
		birthYearC.setMaxWidth(425/2);
		TableColumn sickDateC = new TableColumn<>("Sickness Period (In days)");
		sickDateC.setCellValueFactory(new PropertyValueFactory<Citizen, Integer>("sickDate"));
		sickDateC.setMinWidth(425/2);
		sickDateC.setMaxWidth(425/2);
		this.citizenTable.getColumns().addAll(nameC, idC, birthYearC, sickDateC);

		root.getChildren().addAll(showCitizensTitle, citizenTable);
		return root;
	}

	public VBox showParties() {
		VBox root = new VBox();

		this.showPartyTitle = new Text("Show All Parties");
		this.showPartyTitle.setStyle("-fx-font: 45 arial;");

		this.partyTable = new TableView<>();
		TableColumn<Party, String> nameC = new TableColumn<>("Name");
		nameC.setCellValueFactory(new PropertyValueFactory<Party, String>("name"));
		nameC.setMinWidth(850/4);
		nameC.setMaxWidth(850/4);
		TableColumn<Party, String> factionC = new TableColumn<>("Faction");
		factionC.setCellValueFactory(new PropertyValueFactory<Party, String>("faction"));
		factionC.setMinWidth(850/4);
		factionC.setMaxWidth(850/4);
		TableColumn<Party, LocalDate> establishDateC = new TableColumn<>("Establish Date");
		establishDateC.setCellValueFactory(new PropertyValueFactory<Party, LocalDate>("establishDate"));
		establishDateC.setMinWidth(850/2);
		establishDateC.setMaxWidth(850/2);
		this.partyTable.getColumns().addAll(nameC, factionC, establishDateC);

		root.getChildren().addAll(showPartyTitle, partyTable);
		return root;
	}

	public VBox primeriesVote() {
		VBox primeriesScene = new VBox();
		
		this.helloLbl = new Label();
		this.helloLbl.setStyle("-fx-font: 60 arial;");

		this.poliChoiceBox = new ChoiceBox<Politician>();
		primeriesSubmitBtn.setMinSize(150, 60);
		
		primeriesScene.getChildren().addAll(helloLbl, poliChoiceBox, primeriesSubmitBtn);
		primeriesScene.setSpacing(40);
		primeriesScene.setAlignment(Pos.CENTER);
		
		return primeriesScene;
	}
	
	public VBox vote() {
		VBox voteScene = new VBox();

		this.helloLbl = new Label();
		this.helloLbl.setStyle("-fx-font: 60 arial;");

		abstainBtn.setMinSize(150, 60);

		this.partiesCB = new ChoiceBox<Party>();

		voteSubmitBtn.setMinSize(150, 60);
		voteScene.getChildren().addAll(helloLbl, abstainBtn, partiesCB, voteSubmitBtn);
		voteScene.setSpacing(40);

		voteScene.setAlignment(Pos.CENTER);
		return voteScene;
	}

	public VBox coronaQs() {
		VBox coronaQsScene = new VBox();
		this.helloLbl.setStyle("-fx-font: 60 arial;");
		Label questionLbl = new Label("Are you protected?");
		questionLbl.setStyle("-fx-font: 60 arial;");
		this.coronaYesBtn.setMinSize(150, 60);
		this.coronaNoBtn.setMinSize(150, 60);

		coronaQsScene.getChildren().addAll(helloLbl, questionLbl, coronaYesBtn, coronaNoBtn);
		coronaQsScene.setAlignment(Pos.CENTER);
		coronaQsScene.setSpacing(40);

		return coronaQsScene;
	}

	public VBox ArmyQs() {
		VBox armyQsScene = new VBox();

		this.helloLbl = new Label();
		this.helloLbl.setStyle("-fx-font: 60 arial;");
		Label questionLbl = new Label("Are you carrying your weapon?");
		questionLbl.setStyle("-fx-font: 60 arial;");
		this.armyYesBtn.setMinSize(150, 60);
		this.armyNoBtn.setMinSize(150, 60);

		armyQsScene.getChildren().addAll(helloLbl, questionLbl, armyYesBtn, armyNoBtn);
		armyQsScene.setAlignment(Pos.CENTER);
		armyQsScene.setSpacing(40);

		return armyQsScene;
	}

	public VBox electionsResults() {
		VBox results = new VBox();

		this.resultsTitle = new Label("Elctions Results");
		this.resultsTitle.setStyle("-fx-font: 70 arial;");
		
		Label ballotsLeaders = new Label("Ballots Leaders(Party Leader):");
		ballotsLeaders.setStyle("-fx-font: 40 arial;");
		
		this.resultPieChart = new PieChart();
		
		results.getChildren().addAll(resultsTitle, ballotsLeaders, resultPieChart);
		results.setSpacing(15);

		return results;
	}

	//// event handles

	public void EventHandlerToStartMenuSubmitButton(EventHandler<ActionEvent> event) {
		this.startMenuSubmit.setOnAction(event);
	}

	public void EventHandlerToAddBallotButton(EventHandler<ActionEvent> event) {
		this.addBallotBtn.setOnAction(event);
	}

	public void EventHandlerToChooseCitizenBallotButton(EventHandler<ActionEvent> event) {
		this.citizenBallotBtn.setOnAction(event);
	}

	public void EventHandlerToChooseCoronaBallotButton(EventHandler<ActionEvent> event) {
		this.coronaBallotBtn.setOnAction(event);
	}

	public void EventHandlerToChooseArmyBallotButton(EventHandler<ActionEvent> event) {
		this.armyBallotBtn.setOnAction(event);
	}

	public void EventHandlerToChooseArmyCoronaBallotButton(EventHandler<ActionEvent> event) {
		this.armyCoronaBallotBtn.setOnAction(event);
	}

	public void EventHandlerToBallotSubmitButton(EventHandler<ActionEvent> event) {
		this.ballotSubmit.setOnAction(event);
	}

	public void EventHandlerToAddCitizenButton(EventHandler<ActionEvent> event) {
		this.addCitizenBtn.setOnAction(event);
	}

	public void EventHandlerToCitizenCheckButton(EventHandler<ActionEvent> event) {
		this.checkCitizenTypeBtn.setOnAction(event);
	}

	public void EventHandlerToCitizenSubmitButton(EventHandler<ActionEvent> event) {
		this.addCitizenSubmitBtn.setOnAction(event);
	}

	public void EventHandlerToAddPartyButton(EventHandler<ActionEvent> event) {
		this.addPartyBtn.setOnAction(event);
	}

	public void EventHandlerToPartySubmitButton(EventHandler<ActionEvent> event) {
		this.partySubmitBtn.setOnAction(event);
	}

	public void EventHandlerToAddCandidateButton(EventHandler<ActionEvent> event) {
		this.addCandidateBtn.setOnAction(event);
	}

	public void EventHandlerToCandidateCheckButton(EventHandler<ActionEvent> event) {
		this.checkCandidateTypeBtn.setOnAction(event);
	}

	public void EventHandlerToCandidateSubmitButton(EventHandler<ActionEvent> event) {
		this.addCandidateSubmitBtn.setOnAction(event);
	}

	public void EventHandlerToShowBallotsButton(EventHandler<ActionEvent> event) {
		this.showBallotsBtn.setOnAction(event);
	}

	public void EventHandlerToShowCitizensButton(EventHandler<ActionEvent> event) {
		this.showCitizensBtn.setOnAction(event);
	}

	public void EventHandlerToShowPartiesButton(EventHandler<ActionEvent> event) {
		this.showPartiesBtn.setOnAction(event);
	}

	public void EventHandlerToStartElectionsButton(EventHandler<ActionEvent> event) {
		this.startElectionsBtn.setOnAction(event);
	}
	
	public void EventHandlerToPrimeriesSubmitButton(EventHandler<ActionEvent> event) {
		this.primeriesSubmitBtn.setOnAction(event);
	}

	public void EventHandlerToAbstainedButton(EventHandler<ActionEvent> event) {
		this.abstainBtn.setOnAction(event);
	}

	public void EventHandlerToVoteSubmitButton(EventHandler<ActionEvent> event) {
		this.voteSubmitBtn.setOnAction(event);
	}

	public void EventHandlerToCoronaYesButton(EventHandler<ActionEvent> event) {
		this.coronaYesBtn.setOnAction(event);
	}
	public void EventHandlerToCoronaNoButton(EventHandler<ActionEvent> event) {
		this.coronaNoBtn.setOnAction(event);
	}

	public void EventHandlerToArmyYesButton(EventHandler<ActionEvent> event) {
		this.armyYesBtn.setOnAction(event);
	}
	public void EventHandlerToArmyNoButton(EventHandler<ActionEvent> event) {
		this.armyNoBtn.setOnAction(event);
	}

	public void EventHandlerToElectionsResultsButton(EventHandler<ActionEvent> event) {
		this.resultsBtn.setOnAction(event);
	}

	public void EventHandlerToExitButton(EventHandler<ActionEvent> event) {
		this.exitBtn.setOnAction(event);
	}

	////

	/// get/set

	public void setScene() {
		Stage stage = new Stage();
		stage.setTitle("Elections");
		stage.setScene(this.app);
		stage.show();
		Stage startStage = (Stage)this.startMenuSubmit.getScene().getWindow();
		startStage.close();
	}

	public void setPlatform(VBox root) {
		this.platform.getChildren().clear();
		this.platform.getChildren().add(root);
	}

	public String getElectionsYear() {
		return this.yearTextF.getText();
	}
	public String getElectionsMonth() {
		return this.monthTextF.getText();
	}

	public ArrayList<Node> getAddBallotFields() {
		ArrayList<Node> page = new ArrayList<>();
		page.add(this.cityField);
		page.add(this.streetField);
		page.add(this.houseNumField);
		return page;
	}

	public ArrayList<Node> getAddCitizenFields() {
		ArrayList<Node> page = new ArrayList<>();
		page.add(this.fNameField);
		page.add(this.lNameField);
		page.add(this.idField);
		page.add(this.birthYearComboBox);
		page.add(this.isQuarintinedYESRadioBtn);
		page.add(this.isQuarintinedNORadioBtn);
		page.add(this.infectionDatePicker);
		return page;
	}

	public ChoiceBox getBallotsChoiceBox() {
		return this.ballotChoiceBox;
	}

	public ArrayList<Node> getAddPartyFields() {
		ArrayList<Node> page = new ArrayList<>();
		page.add(this.partyNameField);
		page.add(this.centerRadioBtn);
		page.add(this.leftRadioBtn);
		page.add(this.rightRadioBtn);
		page.add(this.establishDatePicker);
		return page;
	}

	public ChoiceBox<Party> getPartiesChoiceBox() {
		return this.partyChoiceBox;
	}

	public TableView<Ballot<? extends Votable>> getBallotsTable() {
		return this.ballotTable;
	}

	public TableView<Citizen> getCitizensTable() {
		return this.citizenTable;
	}

	public TableView<Party> getPartiesTable() {
		return this.partyTable;
	}

	public void setHelloLbl(String name) {
		this.helloLbl.setText(name);
	}
	
	public ChoiceBox<Politician> getPoliCB() {
		return this.poliChoiceBox;
	}

	public ChoiceBox<Party> getPartiesCB() {
		return this.partiesCB;
	}

	public Button getExitBtn() {
		return this.exitBtn;
	}
	
	public PieChart getResultPieChart() {
		return this.resultPieChart;
	}

	////clear pages

	public void clear(ArrayList<Node> page) {
		for(int i = 0; i < page.size(); i++) {
			Class clazz = page.get(i).getClass();
			if(clazz == TextField.class) {
				TextField b = (TextField)page.get(i);
				b.setText("");
			}
			else if(clazz == RadioButton.class) {
				RadioButton b = (RadioButton)page.get(i);
				b.setSelected(false);
			}
			else if(clazz == ComboBox.class) {
				ComboBox b = (ComboBox)page.get(i);
				b.getSelectionModel().clearSelection();
			}
			else if(clazz == ChoiceBox.class) {
				ChoiceBox b = (ChoiceBox)page.get(i);
				b.getSelectionModel().clearSelection();
			}
			else if(clazz == DatePicker.class) {
				DatePicker b = (DatePicker)page.get(i);
				b.setValue(null);
			}

		}
	}

}
