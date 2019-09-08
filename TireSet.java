package project3;

public class TireSet {

	private final int ENDURANCE = 37500;
	private double odometer;
	
	public TireSet(double odometer) {
		this.odometer = odometer;
	}
	
	public String toString() {
		return ""+odometer;
	}
	
	public double usage() {
		return (odometer/ENDURANCE)*100;
	}
	
	public boolean canRun(double miles) {
		return ((miles + odometer) <= ENDURANCE);
	}
	
	public double run(double miles) {
		double realmiles = (miles + odometer) > ENDURANCE ? ENDURANCE - odometer : miles;
		if (realmiles < miles)
		{
			System.out.println("Bald tires!");
		}
		odometer += realmiles;
		return realmiles;	
	}
	
	public double getOdometer()
	{
		return odometer;
	}
	
	
}
