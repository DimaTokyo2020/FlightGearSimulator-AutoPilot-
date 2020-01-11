package model;

public class MyModel implements Model {

	@Override
	public void setThrottle(double v) {
		System.out.println("throttle "+v);

	}

	@Override
	public void setRudder(double v) {
		// TODO Auto-generated method stub
		System.out.println("rudder "+v);
	}

	@Override
	public void setAileron(double v) {
		// TODO Auto-generated method stub
		System.out.println("aileron "+v);
	}

	@Override
	public void setElevator(double v) {
		// TODO Auto-generated method stub
		System.out.println("elevator "+v);
	}

}
