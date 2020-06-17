package id314920505_id316013804;
import Controller.Controller;
import Model.InvalidInputException;
import Model.Model;
import View.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application{
	public static void main(String[] args) throws InvalidInputException {
		//		ProgramHandle.startMain();
				launch(args);
	}

		@Override
		public void start(Stage primaryStage) throws Exception {
			Model theModel = new Model();
			View theView = new View(primaryStage);
			Controller TheController = new Controller(theModel, theView);
		}
}
