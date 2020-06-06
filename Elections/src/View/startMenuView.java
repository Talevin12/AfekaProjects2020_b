package View;

import Model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class startMenuView {
	private Group root;
	private Text monthTxt;
	private Text yearTxt;
	private TextField monthTextF;
	private TextField yearTextF;
	private Button startMenuSubmit;
	
	public startMenuView(Stage stage)
	{
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
		
			
		Scene scene = new Scene(vb,1000,530);
		stage.setTitle("Elections");
		stage.setScene(scene);
		stage.show();
	}
	
	public int getYear() {
		return Integer.parseInt(yearTextF.getText());
	}
	public int getMonth() {
		return Integer.parseInt(monthTextF.getText());
	}
	
	public void addEventHandlerToSubmitButton(EventHandler<ActionEvent> event) {
		this.startMenuSubmit.setOnAction(event);
	}
	
	public void changeScene() {
//		appView appView = new appView(stage);
	}
}
