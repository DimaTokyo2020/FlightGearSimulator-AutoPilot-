package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class JoystickDisplayer extends Canvas {

	

	private StringProperty joystickFileName;
	double x=1;
	double y=1;
	double xPosD=0;
	double yPosD=0;
	double xPos;
	double yPos;
	double radius;
	String str="helloWorld";
	
	
	
	public JoystickDisplayer() {
		joystickFileName=new SimpleStringProperty();

		xPos=getWidth()/4;
		yPos=getHeight()/4;
	
	}
	
	public String getJoystickFileName() {
		return joystickFileName.get();
	}




	public void setJoystickFileName(String joystickFileName) {
		this.joystickFileName.set(joystickFileName);
	}




	public void redraw() {
		GraphicsContext gc = getGraphicsContext2D();
		Image joystick=null;
		double W=getWidth();
		double H=getHeight();
		
		this.xPosD=W/4;
		this.yPosD=H/4;
		this.radius=H/2-H/4;
		
		
		
		
		try {
			joystick=new Image(new FileInputStream(joystickFileName.get()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gc.clearRect(0, 0, W, H);
		
		if(joystick==null)
			System.out.println("problem");
		
		//need only for first time to put joystick in the center
		if(x==1) {
			x++;
			xPos=xPosD;
			yPos=yPosD;
		}
		
		gc.setFill(javafx.scene.paint.Color.BLACK);
		gc.fillOval(0,0, W, H);
		gc.setFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
		gc.fillOval(W*0.05,H*0.05, W*0.9, H*0.9);
		gc.setFill(javafx.scene.paint.Color.CHARTREUSE);
		gc.fillOval(xPos,yPos, W/2, H/2);
		gc.drawImage(joystick,xPos,yPos, W/2, H/2);
	}
	public void moveJoysticTo(double x, double y) {
		
		/*System.out.println("///////");
		System.out.println((Math.pow((x-xPosD),2)));
		System.out.println(Math.pow((y-yPosD),2));
		System.out.println(Math.pow(radius,2));*/
		if((Math.pow((x-xPosD),2)+Math.pow((y-yPosD),2))<Math.pow(radius,2)) {
			setxPos(x);
			setyPos(y);
		}
		else {
			double dist=Math.sqrt((Math.pow((x-xPosD),2)+Math.pow((y-yPosD),2)));
			double xA=(x-xPosD)/dist;
			double yA=(y-yPosD)/dist;
			setxPos(xA*radius+xPosD);
			setyPos(yA*radius+yPosD);
			
		}
	}
	
	
	public void setJoystickToDefultPosition() {
		
		this.xPos=this.xPosD;
		this.yPos=this.yPosD;
		redraw();
	}


	

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	
	
	
}
