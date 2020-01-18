package model;

import Client.SetParametersRequest;
import Singlton.Lexer;
import Singlton.Parset;
import view.Main;

public class MyModel implements Model {

	public String code=null;
	
	@Override
	public void setThrottle(double v) {
		System.out.println("throttel "+v);
		sendToTheServer("/controls/engines/current-engine/throttle",v);
	}

	@Override
	public void setRudder(double v) {
		System.out.println("Rudder "+v);
		sendToTheServer("/controls/flight/rudder",v);
	}

	@Override
	public void setAileron(double v) {
		System.out.println("eaileron "+v);
		sendToTheServer("/controls/flight/aileron",v);
	}

	@Override
	public void setElevator(double v) {
		System.out.println("elevator "+v);
		sendToTheServer("/controls/flight/elevator",v);
	}
	
	@Override
	public void setCode(String str) {
		this.code=str;
	}
	
	@Override
	public void doParset(Boolean b) {
		 
		new Thread(){
			    public void run(){
			    	String[] lexedStr=new Lexer().lexer(code);
			    
			    	try {
						new Parset().parse(lexedStr,0,lexedStr.length);
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
			    }
			    }
		 }.start();
			  

	}

	
	public void  sendToTheServer (String path, double value) {
		
		SetParametersRequest.getHelper().setMassage("set "+path+" "+value);
		while(SetParametersRequest.getHelper().massageToServer!=null&&SetParametersRequest.getHelper().turnOn) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void interpeterView(Boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joysticView(Boolean b) {
		// TODO Auto-generated method stub
		
	}

		
	

}