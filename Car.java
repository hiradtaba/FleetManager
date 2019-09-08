package project3;

public class Car {

	private final double FUEL_CONSUMPTION = 23.5;
	private final double TANK_CAPACITY = 12.5;
	private final double MAINTENANCE_INTERVAL = 10000;
	private String make;
	private String model;
	private String plate;
	private String owner;
	private int year;
	private int boxCapacity;
	private double odometer;
	private double lastMaintenance;
	private double price;
	private double fuelCost;
	private double tireCost;
	private double maintenanceCost;
	private double fuelTank;
	private TireSet tires;
	
	public Car(String owner, String make, String model, String plate, int year,
			int boxCapacity, double price, double odometer, double lastMaintenance, 
			double fuelCost, double tireCost, double maintenanceCost, 
			double fuelTank, double tireodometer) {
		this.owner = owner;
		this.make = make;
		this.model = model;
		this.plate = plate;
		this.price = price;
		this.year = year;
		this.boxCapacity = boxCapacity;
		this.odometer = odometer;
		this.lastMaintenance = lastMaintenance;
		this.fuelCost = fuelCost;
		this.tireCost = tireCost;
		this.maintenanceCost = maintenanceCost;
		this.fuelTank = fuelTank;
		tires = new TireSet(tireodometer);
	}
	
	public boolean hasPlate(String plate) {
		return this.plate.equals(plate);
	}
	
	public int getBoxCapacity() {
		return boxCapacity;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getYear() {
		return year;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getFuelCost() {
		return fuelCost;
	}
	
	public double getTireCost() {
		return tireCost;
	}
	
	public double getMaintenanceCost() {
		return maintenanceCost;
	}
	
	public double getTankStatus() {
		return (fuelTank/TANK_CAPACITY) * 100;
	}
	
	public double getOdometer() {
		return odometer;
	}
	
	public double getTiresOdometer() {
		return tires.getOdometer();
	}
	
	public double getTiresUsage() {
		return tires.usage();
	}
	
	public double getTiresStatus() {
		return 100-tires.usage();
	}
	
	public double getTotalCost() {
		return price + fuelCost + maintenanceCost + tireCost;
	}
	
	public void drive(double miles){
		if (canRun(miles))
		{
			odometer += miles;
			tires.run(miles);
			consumeFuel(miles);
		}
		else {
			System.out.println("Driving not allowed");
	}
		
	}
	
	
	private boolean canRun(double miles) {
		boolean r = true;
		if (!(maintenanceOK(miles)))
		{
			System.out.println("Should perform maintenance before driving (or drive smaller distance).");
			r = false;
		}
		if (!(tires.canRun(miles)))
		{
			System.out.println("Should change tires before driving (or drive smaller distance).");
			r = false;
		}
		if (!(availableFuel(miles)))
		{
			System.out.println("Should fill up the tank before driving (or drive smaller distance).");
			r = false;
		}
		return r;
	}
	
	public boolean isAvailable(double miles, int boxes) {
		return (maintenanceOK(miles) && tires.canRun(miles) && availableFuel(miles) && (boxCapacity >= boxes) );
	}
	
	private void consumeFuel(double miles) {
		fuelTank -= miles / FUEL_CONSUMPTION;
	}
	
	public boolean availableFuel(double miles) {
		return (miles/fuelTank <= FUEL_CONSUMPTION);
	}
	
	public boolean maintenanceOK(double miles) {
		return (odometer + miles) <= (lastMaintenance + MAINTENANCE_INTERVAL);
	}
	
	public double getMaintenanceSpan() {
		return odometer - lastMaintenance;
	}
	
	public double getMaintenanceStatus() {
		return (1 - ((odometer - lastMaintenance) / MAINTENANCE_INTERVAL)) * 100;
	}
	
	public void doMaintenance(double cost)
	{
		lastMaintenance = odometer;
		maintenanceCost += cost;
	}
	
	public void fillUp(double costPerGal)
	{
		double gals = TANK_CAPACITY - fuelTank;
		double cost = gals*costPerGal;
		fuelTank = TANK_CAPACITY;
		fuelCost+=cost;
	}
	
	public double getGalonsMissingInTank()
	{
		return TANK_CAPACITY - fuelTank;
	}
	
	public boolean tiresOK(double miles) {
		return tires.canRun(miles);
	}
	
	public void changeTires(double cost)
	{
		tires = new TireSet(0);
		tireCost += cost;
	}
	
	public String toString() {
		return owner+","+make +","+ model+","+ plate+","+year+","+boxCapacity+","+price+","+odometer+","+
				lastMaintenance+","+fuelCost+","+tireCost+","+maintenanceCost+","+fuelTank+","+tires.toString();
	}
}
