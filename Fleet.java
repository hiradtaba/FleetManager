
package project3;

import java.util.ArrayList;
public class Fleet {
	
	ArrayList<Car> cars = new ArrayList<Car>();
	
	private String company;
	

	public Fleet(String owner) {
		company = owner;
	}
	
	public void printGeneralInfo() {
		
		System.out.println("|------------------------------------------------|");
		System.out.println("| Plate | Maker | Model | Year | Boxes | Odometer |");
		for (int i = 0; i<cars.size(); i++) {
			System.out.format("| %7s | %6s | %7s | %4d | %05d | %08.2f |\n",cut(cars.get(i).getPlate(), 7), cut(cars.get(i).getMake(),6), cut(cars.get(i).getModel(),7), cars.get(i).getYear(), cars.get(i).getBoxCapacity(), cars.get(i).getOdometer());
		}
		System.out.println("|------------------------------------------------|");
		
	}
	
	public String cut(String word, int size) {
		if(word.length()<=size) {
			return word;
		}
		else {
		String wordcut = word.substring(0, size);
		return wordcut;
		}
	}
	

	public void printMaintenanceInfo() {
		
		System.out.println("|-------------------------------------------------------------------|");
		System.out.println("| Plate | Maker | Model | Year | Fuel(%) | Maintenance(%) | Tires(%) |");
		for(int i=0; i<cars.size(); i++) {
			System.out.format("| %7s | %6s | %7s | %4d | %05.2f | %05.2f | %05.2f |\n",cut(cars.get(i).getPlate(), 7), cut(cars.get(i).getMake(),6), cut(cars.get(i).getModel(),7), cars.get(i).getYear(), cars.get(i).getTankStatus(), cars.get(i).getMaintenanceStatus(), cars.get(i).getTiresStatus());

		}
		System.out.println("|-------------------------------------------------------------------|");
	}
	 
	public void printFinancialInfo() {
		
		System.out.println("|---------------------------------------------------------------------------------------------|");
		System.out.println("| Plate | Maker | Model | Year | Total Cost | Car Price | Maintenance | Tires Cost | Fuel Cost |");
		for(int i=0; i<cars.size(); i++) {
			System.out.format("| %7s | %6s | %7s | %4d | %08.2f | %08.2f | %08.2f | %08.2f| %08.2f |\n",cut(cars.get(i).getPlate(), 7), cut(cars.get(i).getMake(),6), cut(cars.get(i).getModel(),7), cars.get(i).getYear(), cars.get(i).getTotalCost(), cars.get(i).getPrice(), cars.get(i).getMaintenanceCost(), cars.get(i).getTireCost(), cars.get(i).getFuelCost());

		}
		System.out.println("|---------------------------------------------------------------------------------------------|");
	}
	
	
	public void changeTires(double price) {
		
		for(int i=0; i<cars.size(); i++ ) {
			if (cars.get(i).getTiresStatus()<10) {
				cars.get(i).changeTires(price);
				System.out.println("Changing tires of "+cars.get(i).getMake()+" "+cars.get(i).getModel()+" ("+cars.get(i).getPlate()+") ");
				System.out.println("Cost: "+price+" USD");
		}
		}
		
	}
	
	public void doMaintenance(double money) {
		
		for (int i=0; i<cars.size(); i++) {
			if(cars.get(i).getMaintenanceStatus()<100) {
				double cost = cars.get(i).getMaintenanceSpan()*money;
				cars.get(i).doMaintenance(cost);
				System.out.println("Doing maintenance on "+cars.get(i).getMake()+" "+cars.get(i).getModel()+" ("+cars.get(i).getPlate()+") ");
				System.out.println("Cost: "+cost+" USD");
			}
		}
		
	}
	

	public void fillUp(double amount) {
		for (int i=0; i<cars.size(); i++) {
			if (cars.get(i).getTankStatus()<100) {
				double value = cars.get(i).getGalonsMissingInTank()*amount;
				cars.get(i).fillUp(amount);
				System.out.println("Filling up the tank of "+cars.get(i).getMake()+" "+cars.get(i).getModel()+" ("+cars.get(i).getPlate()+") ");
				System.out.println("Cost: "+value+" USD");
			}
			
		}
	}
	

	public void deliver(double miles, int boxes) {
		int min = Integer.MAX_VALUE ;
		int index =-1;
		double total = 2*miles;
		for(int i=0; i<cars.size(); i++) {
			if (cars.get(i).getBoxCapacity() < min && cars.get(i).isAvailable(total, boxes)) {
				min = cars.get(i).getBoxCapacity();
				index = i;
			}
		}
		
		if (index >= 0) {
			cars.get(index).drive(total);
			System.out.println("Delivering with "+cars.get(index).getMake()+" "+cars.get(index).getModel()+" ("+cars.get(index).getPlate()+")");
		}
		
		else {
			System.out.println("No cars are available!");
		}
	}
	
	
	// This method is for adding a new car to the fleet
	public void add(String make, String model, String plate, int year, int capacity, double price, double odometer,
			double lastMaintenance, double fuelCost, double tireCost, double maintenanceCost, double fuelTank, double tireodometer) {
		
		cars.add(new Car(company,make,model,plate,year, capacity, price,odometer,lastMaintenance,fuelCost,tireCost,maintenanceCost,fuelTank,tireodometer));
	}
	
	
	public void remove(String plate) {
		for (int i = 0; i<cars.size(); i++) {
			if(cars.get(i).hasPlate(plate)) {
				cars.remove(i);
			}
		}
	}
	
	
	public String toString() {
		String value = "";
		for(int i =0; i<cars.size(); i++) {
			value +=  cars.get(i).toString()+"\n";
		}
		
		return value;
	}
	
}
	
	
	

