package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MazeDisplayer extends Canvas{

	int[][] mazeDate;
	private StringProperty wallFileName;
	int cCol,cRow;
	
	
	public MazeDisplayer() {
		wallFileName=new SimpleStringProperty();
		cCol=1;
		cRow =1;
		
		}
	
	public void setCharecterPosition(int row, int col) {
		
		this.cCol=col;
		this.cRow=row;
		redraw();
	}
	
	public String getWallFileName() {
		return wallFileName.get();
	}
	
	public void setcCol(int cCol) {
		this.cCol = cCol;
	}

	public void setcRow(int cRow) {
		this.cRow = cRow;
	}

	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}
	public void setMazeDate(int[][] mazeDate) {
		this.mazeDate = mazeDate;
		redraw();
	}
	
	public void redraw() {
		
		if(mazeDate!=null) {
			
			double w=(int)getWidth()/mazeDate[0].length;
			double h=(int)getHeight()/mazeDate.length;
			
			Image wall=null;
			GraphicsContext gc = getGraphicsContext2D();
			
			try {
				wall= new Image(new FileInputStream(wallFileName.get()));
			} catch (FileNotFoundException e) {e.printStackTrace();}
			
			gc.clearRect(0, 0, getWidth(), getHeight());
			
			for(int i=0;i<mazeDate.length;i++)
				for(int j=0;j<mazeDate[i].length;j++) {
					if(mazeDate[i][j]==1)
						if(wall==null)
							gc.fillRect(j*w, i*h, w, h);
						else 
							gc.drawImage(wall,j*w, i*h, w, h);
					}
			gc.setFill(javafx.scene.paint.Color.RED);
			gc.fillOval(cCol*w, cRow*h, w, h);
			
		}
		
		
		
	}

	public int[][] getMazeDate() {
		return mazeDate;
	}

	public int getcCol() {
		return cCol;
	}

	public int getcRow() {
		return cRow;
	}
		
	
	

}
