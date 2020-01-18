package view;
	
import java.io.IOException;

import Singlton.Lexer;
import Singlton.Parset;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Model;
import model.MyModel;
import view.WindowController;
import view_model.ViewModel;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Model m=new MyModel(); // Model
		
		ViewModel vm=new ViewModel(m); // ViewModel

		FXMLLoader fxl=new FXMLLoader();
		try {
			BorderPane root = fxl.load(getClass().getResource("Window8.fxml").openStream());
			
			WindowController wc=fxl.getController(); // View
			wc.setViewModel(vm);
			
			Scene scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
		
	

	
	public static String getCode1() {
		
		return (" openDataServer 5400 10\r\n" + 
				" connect 127.0.0.1 5402\r\n" + 
				" var breaks = bind \"/controls/flight/speedbrake\"\r\n" + 
				" var throttle = bind \" /controls/engines/current-engine/throttle\"\r\n" + 
				" var heading = bind \"/instrumentation/heading-indicator/heading-bug-error-deg\"\"\r\n" + 
				" var airspeed = bind \"/instrumentation/airspeed-indicator/indicated-speed-kt\"\r\n" + 
				" var roll = bind \"/instrumentation/attitude-indicator/indicated-roll-deg\"\r\n" + 
				" var pitch = bind \"/instrumentation/attitude-indicator/internal-pitch-deg\"\r\n" + 
				" var rudder = bind \"/controls/flight/rudder\"\r\n" + 
				" var aileron = bind \"/controls/flight/aileron\"\r\n" + 
				" var elevator = bind \"/controls/flight/elevator\"\r\n" + 
				" var alt = bind \"/instrumentation/altimeter/indicated-altitude-ft\"\r\n") + 
				" throttle = 1\r\n"+ 
				" breaks = 0\r\n" + 
				" var h0 = heading\r\n" + 
				" while alt < 1000 {\r\n" + 
				" rudder = (h0 – heading)/60\r\n" + 
				" aileron = - roll / 70\r\n" + 
				" elevator = pitch / 50\r\n" + 
				" print alt\r\n" + 
				" sleep 250\r\n" + 
				" }\r\n" + 
				" print \"done\"";
	};

	
	public static void main(String[] args) {
		launch(args);
	}
}
