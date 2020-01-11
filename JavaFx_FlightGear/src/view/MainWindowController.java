package view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowController implements Initializable //extends Observable implements view 
{
	
	int[][] mazeData= {
			{1,1,1,1,1,1,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,1,1},
			{1,0,1,1,1,1,1,1,1,0,1,1},
			{0,0,0,0,0,0,1,1,1,0,1,1},
			{1,1,1,1,0,0,0,0,0,1,1,1}};
			
	
	@FXML
	MazeDisplayer mazeDisplayer; 
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mazeDisplayer.setMazeDate(mazeData);
		
		mazeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->mazeDisplayer.requestFocus());
	
		mazeDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			public void handle(KeyEvent event) {
				int r=mazeDisplayer.getcRow();
				int c=mazeDisplayer.getcCol();
				
				if(event.getCode()== KeyCode.UP)
					mazeDisplayer.setCharecterPosition(r-1, c);
				else if(event.getCode()== KeyCode.DOWN)
					mazeDisplayer.setCharecterPosition(r+1, c);
				else if(event.getCode()== KeyCode.LEFT)
					mazeDisplayer.setCharecterPosition(r, c-1);
				else if(event.getCode()== KeyCode.RIGHT)
					mazeDisplayer.setCharecterPosition(r, c+1);
			};
			
		} );
	
	
	}
	
	
	public void start() {
		
		System.out.println("start");
	}

	public void openFile() {
		
		FileChooser fc=new FileChooser();
		fc.setTitle("open your code");
		fc.setInitialDirectory(new File ("./resources"));
		//maybe this line wrong
		fc.setSelectedExtensionFilter(new ExtensionFilter("Xml Files", "*.xml"));
		/*//need to be our screen
		File chosen =fc.showOpenDialog(null);
		if(chosen !=null)
		{System.err.println(chosen.getName());}
		*/
	}




	
}
