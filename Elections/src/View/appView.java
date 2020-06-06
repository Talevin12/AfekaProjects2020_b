package View;

import javax.swing.JSplitPane;

import Model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class appView {
	private Group root;
	private SplitPane splitPane;
	private JSplitPane jSplitPane;
	
	//
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
	//
	
	private GridPane platform;
	//
	
	
	
	public appView(Stage stage) {
		this.root = new Group();
		//this.splitPane = new JSplitPane();
		
		
		
		this.naviMenu = new VBox();
		this.naviMenu.setPadding(new Insets(10));
		this.naviMenu.setSpacing(20);
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
			this.platform = new GridPane();
		
		//
		
		this.splitPane = new SplitPane(this.naviMenu, this.platform);
		Scene scene = new Scene(splitPane, 1000, 530);
		stage.setTitle("Elections");
		stage.setScene(scene);
		stage.show();
	}
}
