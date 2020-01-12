package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import view_model.ViewModel;

public class WindowController implements Initializable{
	
	ViewModel vm;
	
	@FXML
	Slider aileron;
	@FXML
	Slider rudder;
	@FXML
	JoystickDisplayer joystickDisplayer; 
	//...
	
	public void setViewModel(ViewModel vm) {
		this.vm=vm;
		vm.aileron.bind(aileron.valueProperty());
		vm.rudder.bind(rudder.valueProperty());
		
		//...
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		joystickDisplayer.redraw();
	}
	
}
