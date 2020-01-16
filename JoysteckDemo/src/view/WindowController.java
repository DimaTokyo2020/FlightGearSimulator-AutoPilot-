package view;

import java.awt.MouseInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import view_model.ViewModel;

public class WindowController implements Initializable{
	
	ViewModel vm;
	
	@FXML
	Slider rudder;
	@FXML
	Slider throttle;
	@FXML
	JoystickDisplayer joystickDisplayer; 
	//...
	
	public void setViewModel(ViewModel vm) {
		this.vm=vm;
		vm.throttle.bind(throttle.valueProperty());
		vm.rudder.bind(rudder.valueProperty());
		vm.elevator.bind(joystickDisplayer.yBin);
		vm.aileron.bind(joystickDisplayer.xBin);
		
		//...
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		joystickDisplayer.redraw();
		
		//joystickDisplayer.addEventFilter(MouseEvent.MOUSE_DRAGGED, (e)->joystickDisplayer.move());
		joystickDisplayer.setOnMouseDragged(new EventHandler <MouseEvent>() {
	            public void handle(MouseEvent event) {
	                /* drag was detected, start drag-and-drop gesture*/
	                //System.out.println("onDragDetected");
	                
	                
	                	//System.out.println("x"+event.getSceneX());
	                	//System.out.println("y"+event.getSceneY());
	                
		                joystickDisplayer.moveJoysticTo(event.getSceneX()-160,event.getSceneY()-150);
		             
		                joystickDisplayer.redraw();
	                
	            }
	        });

		joystickDisplayer.setOnMouseReleased(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragReleesd");
                joystickDisplayer.setJoystickToDefultPosition();
               
            }
        });
	}
	
}
