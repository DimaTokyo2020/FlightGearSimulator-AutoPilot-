package model;

import Client.SetParametersRequest;

public class MyModel implements Model {

	
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
	
	

}