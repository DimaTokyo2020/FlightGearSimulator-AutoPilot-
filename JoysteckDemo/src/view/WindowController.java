package view;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import view_model.ViewModel;

public class WindowController {
	
	ViewModel vm;
	
	@FXML
	Slider aileron;
	@FXML
	Slider rudder;
	//...

	public void setViewModel(ViewModel vm) {
		this.vm=vm;
		vm.aileron.bind(aileron.valueProperty());
		vm.rudder.bind(rudder.valueProperty());
		
		//...
	}
	
}
