package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JoystickDisplayer extends Canvas {

	
	private StringProperty joystickFileName;
	
	
	public JoystickDisplayer() {
		joystickFileName=new SimpleStringProperty();
	}
	
	public String getJoystickFileName() {
		return joystickFileName.get();
	}




	public void setJoystickFileName(String joystickFileName) {
		this.joystickFileName.set(joystickFileName);
	}




	public void redraw() {
		
		double W=getWidth();
		double H=getHeight();
		GraphicsContext gc = getGraphicsContext2D();
		Image joystick=null;
		
		
		try {
			joystick=new Image(new FileInputStream(joystickFileName.get()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(joystick==null)
			System.out.println("problem");
		gc.drawImage(joystick,W/2, H/2, W, H);
		
		
	}
	
	
	
}
