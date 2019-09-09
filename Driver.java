package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
	private static Scanner myscan;


	public static void main(String[] args) {
		myscan = new Scanner(System.in);
		
		System.out.println("Welcome to the FLEET Manager APP! Please tell us your company name:");
		String company = myscan.nextLine();
		
		Fleet myfleet = new Fleet(company);
		
		readFromFile(myfleet, company);
		
		String plate, model, make;
		int op;
		double miles,price;
		int boxes;
		do
		{
			
			printMenu();
			op = myscan.nextInt();
			myscan.nextLine();
			
			switch (op)
			{
				case 1:
					myfleet.printGeneralInfo();
					break;
				case 2:
					myfleet.printMaintenanceInfo();
					break;
				case 3:
					myfleet.printFinancialInfo();
					break;
				case 4:
					price = readPrice("fuel");
					myfleet.fillUp(price);
					break;
				case 5:
					price = readPrice("maintenance (per mile)");
					myfleet.doMaintenance(price);
					break;
				case 6:
					price = readPrice("tire set");
					myfleet.changeTires(price);
					break;
				case 7:
					miles = readMiles();
					boxes = readPositive("Please inform number of boxes to be delivered:");
					myfleet.deliver(miles,boxes);
					break;
				case 8:
					System.out.println("Please enter car information:");
					System.out.print("Car make: ");
					make = myscan.next();
					System.out.print("Car model: ");
					model = myscan.next();
					System.out.print("Car plate: ");
					plate = myscan.next();
					System.out.print("Car price: ");
					price = myscan.nextDouble();
					boxes = readPositive("Box capacity:");
					myfleet.add(make, model, plate, 2018, boxes, price, 0, 0, 0, 0, 0, 0, 0);
					break;
				case 9:
					System.out.print("Inform the car plate of the car you want to remove: ");
					plate = myscan.next();
					myfleet.remove(plate);
					break;
				case 10:
					printToFile(myfleet);
					System.out.println("Good Bye!");
					break;
				default:
					System.out.println("INVALID OPTION!");
					break;
			}
			shouldContinue();
		} while (op!=10);
		
		
	}
	
	static public void shouldContinue() {
		String tmp;
		do
		{
			System.out.print("Press C to continue: ");
			tmp = myscan.nextLine();
		} while (!tmp.toUpperCase().equals("C"));
		System.out.print("\n\n\n\n\n\n\n\n\n\n");
	}
	
	static public double readMiles() {
		double miles;
		do
		{
			System.out.println("Please inform the distance (in miles) for your trip:");
			miles = myscan.nextDouble();
			if (miles<0)
				System.out.println("Distance can't be negative!");
		} while (miles<0);
		myscan.nextLine();
		return miles;
	}
	
	static public int readPositive(String msg) {
		int boxes;
		do
		{
			System.out.println(msg);
			boxes = myscan.nextInt();
			if (boxes<0)
				System.out.println("Number can't be negative!");
		} while (boxes<0);
		myscan.nextLine();
		return boxes;
	}
	
	static public void readFromFile(Fleet myfleet, String company)
	{
		Scanner inFile;
		try {
			inFile = new Scanner(new File("fleet.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File fleet.txt not found.");
			return;
			//e.printStackTrace();
		}
		
		String line;
		Scanner lineS;
		String make, model, plate;
		int year, boxes;
		double price, odometer, lastMaintenance;
		double fuelCost, tireCost, maintenanceCost, fuelTank, tireodometer;
		while (inFile.hasNext())
		{
			line = inFile.nextLine();
			//System.out.println(line);
			lineS = new Scanner(line);
			lineS.useDelimiter(",");
			//discard company
			lineS.next();
			make = lineS.next();
			model = lineS.next();
			plate = lineS.next();
			year = lineS.nextInt();
			boxes = lineS.nextInt();
			price = lineS.nextDouble();
			odometer = lineS.nextDouble();
			lastMaintenance = lineS.nextDouble();
			fuelCost = lineS.nextDouble();
			tireCost = lineS.nextDouble();
			maintenanceCost = lineS.nextDouble();
			fuelTank = lineS.nextDouble();
			tireodometer = lineS.nextDouble();
			/*System.out.println(company +" "+ make+" "+ model+" "+ plate+" "+ year+" "+
					boxes+" "+ price+" "+ odometer+" "+ lastMaintenance+" "+ 
					fuelCost+" "+ tireCost+" "+ maintenanceCost+" "+ 
					fuelTank+" "+ tireodometer);*/
			myfleet.add(make, model, plate, year,
					boxes, price, odometer, lastMaintenance, 
					fuelCost, tireCost, maintenanceCost, 
					fuelTank, tireodometer);
		}
		inFile.close();
	}
	
	static public void printToFile(Fleet myfleet) {	
		
		FileWriter fw;
		try {
			fw = new FileWriter("fleet.txt");
		} catch (IOException e) {
			System.out.println("File fleet.txt not found.");
			e.printStackTrace();
			return;
		}
		try {
			fw.write(myfleet.toString());
		} catch (IOException e) {
			System.out.println("Could not write to file fleet.txt.");
			e.printStackTrace();
		}
		try {
			fw.close();
		} catch (IOException e) {
			System.out.println("Could not close file fleet.txt.");
			e.printStackTrace();
			return;
		}
	}
	
	static public double readPrice(String item) {
		double price;
		do
		{
			System.out.println("Enter "+ item +" price:");
			price = myscan.nextDouble();
			if (price<0)
				System.out.println("Price can't be negative!");
		} while (price<0);
		myscan.nextLine();
		return price;
	}
	
	static public void printMenu() {
		System.out.println("=====================================================================================");
		System.out.println("Menu:");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println(" 1 - Print general information.");
		System.out.println(" 2 - Print maintenance information.");
		System.out.println(" 3 - Print financial information.");;
		System.out.println(" 4 - Fill up all car tanks.");
		System.out.println(" 5 - Do maintenance.");
		System.out.println(" 6 - Change tires.");
		System.out.println(" 7 - Make a delivery.");
		System.out.println(" 8 - Add a car.");
		System.out.println(" 9 - Remove a car.");
		System.out.println("10 - Quit.");
		System.out.println("=====================================================================================");
		System.out.print("Type in the number corresponding to your choice: ");
	}

	}


